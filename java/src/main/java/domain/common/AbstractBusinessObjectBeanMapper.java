/*
 * AbstractBusinessObjectBeanMapper.java is part of Document Manager (c) 2015.
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

package domain.common;

import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * {@code IBusinessObjectBeanMapper} skeleton class abstract class. Provides default list mapping methods implementations.
 *
 * @param <Bean> Bean object from persistence layer.
 * @param <Bo> Business object used in domain operation.
 */
public abstract class AbstractBusinessObjectBeanMapper<Bean, Bo> implements IBusinessObjectBeanMapper<Bean, Bo>
{
    public List<Bean> newBusinessObjectBeanList(final Iterable<Bo> businessObjectList)
    {
        List<Bean> businessObjectBeanList = new ArrayList<>(Iterables.size(businessObjectList));

        for (Bo businessObject : businessObjectList)
            businessObjectBeanList.add(this.newBusinessObjectBean(businessObject));

        return businessObjectBeanList;
    }

    public List<Bo> newBusinessObjectList(final Iterable<Bean> businessObjectBeanList)
    {
        List<Bo> businessObjectDTOList = new ArrayList<>(Iterables.size(businessObjectBeanList));

        for (Bean businessObjectBean : businessObjectBeanList)
            businessObjectDTOList.add(this.newBusinessObject(businessObjectBean));

        return businessObjectDTOList;
    }
}
