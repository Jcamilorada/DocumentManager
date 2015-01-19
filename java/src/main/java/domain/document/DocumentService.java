/*
 * DocumentService.java is part of Document Manager (c) 2015.
 *
 * Document Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Document Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Document Manager.  If not, see <http://www.gnu.org/licenses/>.
 */

package domain.document;

import com.google.common.base.Preconditions;
import configuration.RepositoryProperties;
import domain.exception.StorageNotAvailableException;
import domain.exception.UnspecifiedDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.git.document.DocumentRepository;
import persistence.git.exception.SourceControlUnspecifiedException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Juan Camilo Rada
 */
@Component
public class DocumentService
{
    private final DocumentRepository documentRepository;
    private final DocumentBeanMapper documentBeanMapper;
    private final RepositoryProperties repositoryProperties;

    @Autowired
    DocumentService(
        final DocumentRepository documentRepository,
        final DocumentBeanMapper documentBeanMapper,
        final RepositoryProperties repositoryProperties)
    {
        this.documentRepository = Preconditions.checkNotNull(documentRepository, "documentRepository cannot be null");
        this.documentBeanMapper = Preconditions.checkNotNull(documentBeanMapper, "documentMapper cannot be null");
        this.repositoryProperties = Preconditions.checkNotNull(repositoryProperties, "repositoryProperties cannot be null");
    }

    public List<Document> getDocuments() throws StorageNotAvailableException, UnspecifiedDomainException
    {
        String repositoryPath = repositoryProperties.getPath();
        List<Document> documents = Collections.<Document>emptyList();
        try
        {
            documents =  documentBeanMapper.newBusinessObjectList(documentRepository.getDocuments(repositoryPath));
        }
        catch (IOException e)
        {
            throw new StorageNotAvailableException(e);
        }
        catch (SourceControlUnspecifiedException e)
        {
            throw new UnspecifiedDomainException(e);
        }
        return  documents;
    }
}
