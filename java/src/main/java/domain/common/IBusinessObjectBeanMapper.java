/*
 * IBusinessObjectBeanMapper.java is part of Document Manager (c) 2015.
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

/**
 *
 * @author Juan Camilo Rada
 *
 * IBusinessObjectBeanMapper interface. Represent the default functionally for mapping between Domain and Bean business
 * objects.
 *
 * @param <Bean> Bean object from persistence layer.
 * @param <BO> Business object used in domain operation.
 */
public interface IBusinessObjectBeanMapper<Bean, BO>
{
    public Bean newBusinessObjectBean(final BO businessObject);

    public BO newBusinessObject(final Bean businessObjectBean);
}
