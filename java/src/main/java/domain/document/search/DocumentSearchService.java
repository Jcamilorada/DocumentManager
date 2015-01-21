/*
 * DocumentSearchService.java is part of Document Manager (c) 2015.
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

package domain.document.search;

import com.google.common.base.Preconditions;
import domain.exception.ResourceNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.lucene.exception.InvalidAPIUsageException;
import persistence.lucene.search.DocumentSearchResultBean;
import persistence.lucene.search.LuceneSearchResource;

import java.io.IOException;
import java.util.List;

/**
 * @author Juan Camilo Rada
 */
@Component
public class DocumentSearchService
{
    private final LuceneSearchResource luceneSearchResource;
    private final DocumentSearchResultMapper documentSearchResultMapper;

    @Autowired
    DocumentSearchService(final LuceneSearchResource luceneSearchResource,
                          final DocumentSearchResultMapper documentSearchResultMapper)
    {
        this.luceneSearchResource = Preconditions.checkNotNull(luceneSearchResource);
        this.documentSearchResultMapper = Preconditions.checkNotNull(documentSearchResultMapper);
    }

    public List<DocumentSearchResult> searchDocuments(String query) throws ResourceNotAvailableException, domain.exception.InvalidAPIUsageException
    {
        List<DocumentSearchResultBean> searchResultBeans = null;
        try
        {
            searchResultBeans = luceneSearchResource.getDocuments(query);
        }
        catch (InvalidAPIUsageException e)
        {
            throw new domain.exception.InvalidAPIUsageException(e);
        }
        catch (IOException e)
        {
            throw new ResourceNotAvailableException(e);
        }

        return documentSearchResultMapper.newBusinessObjectList(searchResultBeans);
    }
}
