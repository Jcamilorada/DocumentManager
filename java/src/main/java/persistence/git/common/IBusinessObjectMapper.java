package persistence.git.common;

import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * Map beetween Business Object Bean and Jgit Business Object.
 */
public interface IBusinessObjectMapper<T, BEAN>
{
    public T newBusinessObject(final BEAN businessObjectBean);

    public BEAN newBusinessObjectDTO(final T businessObject);

    public List<T> newBusinessObjectList(final List<BEAN> businessObjectBean);

    public List<BEAN> newBusinessObjectDTOList(final List<T> businessObjectList);
}
