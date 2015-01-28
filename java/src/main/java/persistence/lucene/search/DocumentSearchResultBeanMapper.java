/*
 * TreeWalkDocumentBeanMapper.java is part of Document Manager (c) 2015.
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

package persistence.lucene.search;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.highlight.TextFragment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * Created a {@code DocumentSearchResultBean} based on input {@code Document} and information.
 *
 */
@Component
class DocumentSearchResultBeanMapper
{
    public DocumentSearchResultBean newBusinessObjectBean(
        final Document document, final float score, final TextFragment[] fragments)
    {
        DocumentSearchResultBean searchResultBean = new DocumentSearchResultBean();
        searchResultBean.setPath(document.getField(FullTextSearchResource.DOCUMENT_PATH_FIELD).stringValue());
        searchResultBean.setName(document.getField(FullTextSearchResource.DOCUMENT_NAME_FIELD).stringValue());
        searchResultBean.setScore(score);
        searchResultBean.setFragments(getFragmentsString(fragments));

        return searchResultBean;
    }

    private List<String> getFragmentsString(final TextFragment[] fragments)
    {
        List<String> fragmentList = new ArrayList<>(fragments.length);
        for (TextFragment textFragment : fragments)
        {
            if (textFragment.getScore() > 0)
                fragmentList.add(textFragment.toString());
        }

        return fragmentList;
    }
}
