package persistence.git.document;


import com.google.common.base.Preconditions;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DocumentRepository
{
    private final FileRepositoryBuilder fileRepositoryBuilder = new FileRepositoryBuilder();

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

    public List<DocumentBean> getDocuments(final String repositoryPath) throws IOException, GitAPIException
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
            treeWalk.setFilter(PathFilter.create("test_repository"));
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
