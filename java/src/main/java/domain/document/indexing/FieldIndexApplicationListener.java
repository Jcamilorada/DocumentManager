/*
 * LuceneApplicationListener.java is part of Document Manager (c) 2015.
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
 */

/*
 * StorageNotAvailableException.java is part of Document Manager (c) 2015.
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
 */

package domain.document.indexing;

import com.google.common.base.Preconditions;
import domain.document.information.DocumentManagement;
import domain.document.information.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import persistence.git.exception.SourceControlUnspecifiedException;

import java.io.IOException;
import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * Spring application listener. The listener add the documents to the full serch text engine.
 *
 */
@Component
class FieldIndexApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private final DocumentManagement documentManagement;

    @Autowired
    FieldIndexApplicationListener(
        final DocumentManagement documentManagement)
    {
        this.documentManagement = Preconditions.checkNotNull(documentManagement);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        try
        {
            List<Document> documentList = documentManagement.getRepositoryDocuments();
            documentManagement.AddDocumentToSearchIndex(documentList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (SourceControlUnspecifiedException e)
        {
            e.printStackTrace();
        }
    }
}
