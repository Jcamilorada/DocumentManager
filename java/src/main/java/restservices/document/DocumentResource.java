package restservices.document;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import domain.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.git.document.DocumentBean;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Camilo Rada
 */
@Controller
@RequestMapping("/documents")
public class DocumentResource
{
    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    @Autowired
    DocumentResource(final DocumentService documentService, final  DocumentMapper documentMapper)
    {
        this.documentService = Preconditions.checkNotNull(documentService, "documentService cannot be null");
        this.documentMapper = Preconditions.checkNotNull(documentMapper, "documentMapper cannot be null");
    }

    @RequestMapping
    public
    @ResponseBody
    List<DocumentDTO> getDocuments() throws IOException, GitAPIException
    {
        return documentMapper.newBusinessObjectDTOList(documentService.getDocuments());
    }
}
