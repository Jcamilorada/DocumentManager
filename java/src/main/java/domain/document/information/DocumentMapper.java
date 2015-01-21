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

package domain.document.information;

import domain.common.AbstractBusinessObjectBeanMapper;
import org.springframework.stereotype.Component;
import persistence.git.document.DocumentBean;

/**
 * @author Juan Camilo Rada
 */
@Component
class DocumentMapper extends AbstractBusinessObjectBeanMapper<DocumentBean, Document>
{
    @Override
    public DocumentBean newBusinessObjectBean(Document businessObject)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Document newBusinessObject(DocumentBean businessObjectBean)
    {
        Document document = new Document();
        document.setContent(businessObjectBean.getContent());
        document.setId(businessObjectBean.getId());
        document.setPath(businessObjectBean.getPath());
        document.setName(businessObjectBean.getName());

        return document;
    }
}
