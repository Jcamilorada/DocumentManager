package domain.document;

import com.google.common.base.Preconditions;
import configuration.RepositoryProperties;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.git.document.DocumentRepository;

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

    public List<Document> getDocuments()
    {
        String repositoryPath = repositoryProperties.getPath();
        List<Document> documents = Collections.EMPTY_LIST;
        try
        {
            documents =  documentBeanMapper.newBusinessObjectList(documentRepository.getDocuments(repositoryPath));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (GitAPIException e)
        {
            e.printStackTrace();
        }
        return  documents;
    }
}
