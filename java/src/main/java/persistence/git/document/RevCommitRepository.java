/*
 * RevCommitRepository.java is part of Document Manager (c) 2015.
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

import com.google.common.collect.Lists;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Component;
import persistence.git.exception.SourceControlUnspecifiedException;

import java.util.Collections;
import java.util.Optional;

/**
 * @author Juan Camilo Rada
 *
 * Commit Repository. Provides git commit operations.
 */
@Component
class RevCommitRepository
{
    Optional<RevCommit> getLastRevCommit(final Git git) throws SourceControlUnspecifiedException
    {
        Optional<RevCommit> lastCommit;
        try
        {
            lastCommit = Lists.newArrayList(git.log().call()).stream().sorted(Collections.reverseOrder((RevCommit u1, RevCommit u2) -> Integer.compare(u1.getCommitTime(), u2.getCommitTime())))
                .findFirst();
        }
        catch (GitAPIException e)
        {
           throw new SourceControlUnspecifiedException(e);
        }

        return lastCommit;
    }
}
