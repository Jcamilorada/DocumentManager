package persistence.git.document;

import com.google.common.collect.Lists;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
class RevCommitRepository
{
    Optional<RevCommit> getLastRevCommit(final Git git) throws GitAPIException
    {
        Optional<RevCommit> lastCommit = Lists.newArrayList(git.log().call()).stream().
                sorted(Collections.reverseOrder(
                    (RevCommit u1, RevCommit u2)->Integer.compare(u1.getCommitTime(), u2.getCommitTime()))).findFirst();

        return lastCommit;
    }
}
