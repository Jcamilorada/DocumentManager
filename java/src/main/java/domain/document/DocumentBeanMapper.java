package domain.document;

import domain.common.AbstractBusinessObjectBeanMapper;
import org.springframework.stereotype.Component;
import persistence.git.document.DocumentBean;

/**
 * @author Juan Camilo Rada
 */
@Component
class DocumentBeanMapper extends AbstractBusinessObjectBeanMapper<DocumentBean, Document>
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
