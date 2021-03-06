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

package persistence.git.document;

import persistence.git.common.AbstractBusinessObjectBeanMapper;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.stereotype.Component;

/**
 * @author Juan Camilo Rada
 *
 * {@code TreeWalk} - {@code DocumentBean} mapper. Map beetween TreeWalk jgit object and DocumentBean mapper.
 */
@Component
class TreeWalkDocumentBeanMapper extends AbstractBusinessObjectBeanMapper<TreeWalk, DocumentBean>
{
    @Override
    public TreeWalk newBusinessObject(DocumentBean businessObjectBean)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentBean newBusinessObjectDTO(TreeWalk businessObject)
    {
        DocumentBean documentBean = new DocumentBean();
        documentBean.setName(businessObject.getNameString());
        documentBean.setPath(businessObject.getPathString());
        documentBean.setId(businessObject.getObjectId(0).getName());

        return documentBean;
    }
}
