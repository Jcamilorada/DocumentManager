package persistence.git.document;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitObjectRepository
{
    private static final String CONTENT_FORMAT = "UTF-8";

    public String getObjectContent(final ObjectId objectId, final Repository repository) throws IOException
    {
        ObjectLoader loader = repository.open(objectId);
        return new String(loader.getBytes(), CONTENT_FORMAT);
    }
}
