/*
 * IBusinessObjectMapper.java is part of Document Manager (c) 2015.
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

import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * Map beetween Business Object and DTO Business Object.
 */
public interface IBusinessObjectMapper<T, DTO>
{
    public T newBusinessObject(final DTO businessObjectDTO);

    public DTO newBusinessObjectDTO(final T businessObject);

    public List<T> newBusinessObjectList(final List<DTO> businessObjectDTO);

    public List<DTO> newBusinessObjectDTOList(final List<T> businessObjectList);
}
