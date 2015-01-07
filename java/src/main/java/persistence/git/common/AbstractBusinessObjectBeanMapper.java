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
