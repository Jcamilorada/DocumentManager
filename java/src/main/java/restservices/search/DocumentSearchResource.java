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
 */

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

package restservices.search;

import com.google.common.base.Preconditions;
import domain.document.search.DocumentSearchService;
import domain.exception.InvalidAPIUsageException;
import domain.exception.ResourceNotAvailableException;
import domain.exception.UnspecifiedDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * {@code DocumentSearchResultDTO} rest service to comunicate and send information to the ui side.
 */
@Controller
@RequestMapping("/search")
public class DocumentSearchResource
{
    private final DocumentSearchService documentSearchService;
    private final DocumentSearchResultDTOMapper documentSearchResultDTOMapper;

    @Autowired
    DocumentSearchResource(final DocumentSearchService documentSearchService, final DocumentSearchResultDTOMapper documentSearchResultDTOMapper)
    {
        this.documentSearchService =
            Preconditions.checkNotNull(documentSearchService, "documentSearchService cannot be null");
        this.documentSearchResultDTOMapper =
            Preconditions.checkNotNull(documentSearchResultDTOMapper, "documentSearchResultMapper cannot be null");
    }

    @RequestMapping("/{query}/user/{user}")
    public
    @ResponseBody
    List<DocumentSearchResultDTO> getAllDocuments(final @PathVariable String query, final @PathVariable String user)
        throws UnspecifiedDomainException, ResourceNotAvailableException, InvalidAPIUsageException
    {
        return documentSearchResultDTOMapper.newBusinessObjectDTOList(documentSearchService.searchDocuments(query, user));
    }
}
