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

package persistence.git.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * AbstractBusinessObjectMapper provider default implemtations for {@code IBusinessObjectMapper}.
 */
public abstract class AbstractBusinessObjectBeanMapper<T, BEAN> implements IBusinessObjectMapper<T, BEAN>
{
    public List<T> newBusinessObjectList(final List<BEAN> businessObjectBEAN)
    {
        List<T> businessObjectList = new ArrayList<>(businessObjectBEAN.size());
        for (BEAN BEAN : businessObjectBEAN)
        {
            businessObjectList.add(this.newBusinessObject(BEAN));
        }

        return businessObjectList;
    }

    public List<BEAN> newBusinessObjectDTOList(final List<T> businessObjectList)
    {
        List<BEAN> businessObjectBEANList = new ArrayList<>(businessObjectList.size());
        for (T businessObject : businessObjectList)
        {
            businessObjectBEANList.add(this.newBusinessObjectDTO(businessObject));
        }

        return businessObjectBEANList;
    }
}
