package persistence.git.document;

import persistence.git.common.AbstractBusinessObjectBeanMapper;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.stereotype.Component;

@Component
class TreeWalkDocumentBeanMapper extends AbstractBusinessObjectBeanMapper<TreeWalk, DocumentBean>
{
    @Override
    public TreeWalk newBusinessObject(DocumentBean businessObjectBean)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentBean newBusinessObjectDTO(TreeWalk businessObject)
    {
        DocumentBean documentBean = new DocumentBean();
        documentBean.setName(businessObject.getNameString());
        documentBean.setPath(businessObject.getPathString());
        documentBean.setId(businessObject.getObjectId(0).getName());

        return documentBean;
    }
}
