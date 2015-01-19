/*
 * DocumentResource.java is part of Document Manager (c) 2015.
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

package restservices.document;

import com.google.common.base.Preconditions;
import domain.document.DocumentService;
import domain.exception.StorageNotAvailableException;
import domain.exception.UnspecifiedDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * {@code DocumentDTO} rest service to comunicate and send information to the ui side.
 */
@Controller
@RequestMapping("/documents")
public class DocumentResource
{
    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @Autowired
    DocumentResource(final DocumentService documentService, final  DocumentMapper documentMapper)
    {
        this.documentService = Preconditions.checkNotNull(documentService, "documentService cannot be null");
        this.documentMapper = Preconditions.checkNotNull(documentMapper, "documentMapper cannot be null");
    }

    @RequestMapping
    public
    @ResponseBody
    List<DocumentDTO> getDocuments() throws UnspecifiedDomainException, StorageNotAvailableException
    {
        return documentMapper.newBusinessObjectDTOList(documentService.getDocuments());
    }
}
