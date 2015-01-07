package restservices.document;

import domain.document.Document;
import org.springframework.stereotype.Component;
import restservices.common.AbstractBusinessObjectMapper;

/**
 * @author Juan Camilo Rada
 */
@Component
public class DocumentMapper extends AbstractBusinessObjectMapper<Document, DocumentDTO>
{
    @Override
    public Document newBusinessObject(final DocumentDTO businessObjectDTO)
    {
        Document document = new Document();
        document.setName(businessObjectDTO.getName());
        document.setId(businessObjectDTO.getId());

        return document;
    }

    @Override
    public DocumentDTO newBusinessObjectDTO(final Document businessObject)
    {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setName(businessObject.getName());
        documentDTO.setId(businessObject.getId());

        return documentDTO;
    }
}
