/*
 * AbstractBusinessObjectMapper.java is part of Document Manager (c) 2015.
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

package restservices.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * AbstractBusinessObjectMapper provider default implemtations for {@code IBusinessObjectMapper}.
 */
public abstract class AbstractBusinessObjectMapper<T, DTO> implements IBusinessObjectMapper<T, DTO>
{
    public List<T> newBusinessObjectList(final List<DTO> businessObjectDTO)
    {
        List<T> businessObjectList = new ArrayList<>(businessObjectDTO.size());
        for (DTO dto : businessObjectDTO)
        {
            businessObjectList.add(this.newBusinessObject(dto));
        }

        return businessObjectList;
    }

    public List<DTO> newBusinessObjectDTOList(final List<T> businessObjectList)
    {
        List<DTO> businessObjectDTOList = new ArrayList<>(businessObjectList.size());
        for (T businessObject : businessObjectList)
        {
            businessObjectDTOList.add(this.newBusinessObjectDTO(businessObject));
        }

        return businessObjectDTOList;
    }
}
