/*
 * DocumentResource.java is part of Document Manager (c) 2015.
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
