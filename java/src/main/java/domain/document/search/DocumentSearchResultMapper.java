/*
 * DocumentSearchResultMapper.java is part of Document Manager (c) 2015.
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
 * DocumentBeanMapper.java is part of Document Manager (c) 2015.
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

package domain.document.search;

import domain.common.AbstractBusinessObjectBeanMapper;
import org.springframework.stereotype.Component;
import persistence.lucene.search.DocumentSearchResultBean;

/**
 * @author Juan Camilo Rada
 */
@Component
class DocumentSearchResultMapper extends AbstractBusinessObjectBeanMapper<DocumentSearchResultBean, DocumentSearchResult>
{
    @Override
    public DocumentSearchResultBean newBusinessObjectBean(final DocumentSearchResult businessObject)
    {
        return null;
    }

    @Override
    public DocumentSearchResult newBusinessObject(final DocumentSearchResultBean businessObjectBean)
    {
        DocumentSearchResult searchResult = new DocumentSearchResult();
        searchResult.setDocument(businessObjectBean.getName());
        searchResult.setPath(businessObjectBean.getPath());
        searchResult.setScore(businessObjectBean.getScore());
        searchResult.setFragments(businessObjectBean.getFragments());

        return searchResult;
    }
}
