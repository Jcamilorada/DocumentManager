/*
 * DocumentRepository.java is part of Document Manager (c) 2015.
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

package persistence.git.document;


import com.google.common.base.Preconditions;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.git.exception.SourceControlUnspecifiedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Juan Camilo Rada
 *
 * Document repository. Implement document base operation like query, persist and update changes on documents.
 */
@Component
public class DocumentRepository
{
    private final FileRepositoryBuilder fileRepositoryBuilder = new FileRepositoryBuilder();
    private static final String PATH_REPOSITORY = "test_repository";

    private final RevCommitRepository revCommitRepository;
    private final GitObjectRepository gitObjectRepository;
    private final TreeWalkDocumentBeanMapper treeWalkDocumentBeanMapper;

    @Autowired
    DocumentRepository(
        final RevCommitRepository revCommitRepository,
        final GitObjectRepository gitObjectRepository,
        final TreeWalkDocumentBeanMapper treeWalkDocumentBeanMapper)
    {
        this.revCommitRepository=  Preconditions.checkNotNull(revCommitRepository, "revCommitRepository cannot be null");
        this.gitObjectRepository =  Preconditions.checkNotNull(gitObjectRepository, "gitObjectRepository cannot be null");
        this.treeWalkDocumentBeanMapper =  Preconditions.checkNotNull(treeWalkDocumentBeanMapper, "documentBeanMapper cannot be null");
    }

    /**
     * Return the list of document represented by a {@code DocumentBean} in th specified repository path.
     *
     * @param repositoryPath the repository path to search documents.
     *
     * @return the list of document beans.
     * @throws IOException if repository folder is not found.
     * @throws SourceControlUnspecifiedException if the repository operation read fail for any source control unspecified reason.
     */
    public List<DocumentBean> getDocuments(final String repositoryPath) throws IOException, SourceControlUnspecifiedException
    {
        File file = new File(repositoryPath);
        Repository repository = fileRepositoryBuilder.setGitDir(file).build();
        Git git = new Git(repository);

        Optional<RevCommit> lastCommit = revCommitRepository.getLastRevCommit(git);
        List<DocumentBean> documentBeanList = new ArrayList<>();

        if (lastCommit.isPresent())
        {
            TreeWalk treeWalk = new TreeWalk(repository);
            treeWalk.addTree(lastCommit.get().getTree());
            treeWalk.setFilter(PathFilter.create(PATH_REPOSITORY));
            treeWalk.setRecursive(true);

            while (treeWalk.next()) {
                DocumentBean documentBean = treeWalkDocumentBeanMapper.newBusinessObjectDTO(treeWalk);
                documentBean.setContent(gitObjectRepository.getObjectContent(treeWalk.getObjectId(0), repository));
                documentBeanList.add(documentBean);
            }
        }

        repository.close();
        return documentBeanList;
    }

}
