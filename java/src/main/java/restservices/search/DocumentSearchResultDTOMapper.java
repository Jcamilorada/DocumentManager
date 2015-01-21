/*
 * DocumentMapper.java is part of Document Manager (c) 2015.
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
 * DocumentMapper.java is part of Document Manager (c) 2015.
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

import domain.document.search.DocumentSearchResult;
import org.springframework.stereotype.Component;
import restservices.common.AbstractBusinessObjectMapper;

/**
 * @author Juan Camilo Rada
 *
 * {@code DocumentSearchResult} and {@code DocumentSearchResultDTO} mapper.
 */
@Component
public class DocumentSearchResultDTOMapper extends AbstractBusinessObjectMapper<DocumentSearchResult, DocumentSearchResultDTO>
{
    @Override
    public DocumentSearchResult newBusinessObject(final DocumentSearchResultDTO businessObjectDTO)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentSearchResultDTO newBusinessObjectDTO(final DocumentSearchResult businessObject)
    {
        DocumentSearchResultDTO documentSearchResultDTO = new DocumentSearchResultDTO();
        documentSearchResultDTO.setName(businessObject.getName());
        documentSearchResultDTO.setPath(businessObject.getPath());
        documentSearchResultDTO.setScore(businessObject.getScore());
        documentSearchResultDTO.setFragments(businessObject.getFragments());

        return documentSearchResultDTO;
    }
}
