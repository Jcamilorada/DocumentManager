/*
 * GitObjectRepository.java is part of Document Manager (c) 2015.
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

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Juan Camilo Rada
 *
 * Git Object Repository. Provides git objects operations.
 */
@Component
class GitObjectRepository
{
    private static final String CONTENT_FORMAT = "UTF-8";

    String getObjectContent(final ObjectId objectId, final Repository repository) throws IOException
    {
        ObjectLoader loader = repository.open(objectId);
        return new String(loader.getBytes(), CONTENT_FORMAT);
    }
}
