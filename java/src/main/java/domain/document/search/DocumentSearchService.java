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
import com.google.common.base.Strings;
import domain.exception.ResourceNotAvailableException;
import domain.exception.UnspecifiedDomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.lucene.exception.InvalidAPIUsageException;
import persistence.lucene.search.DocumentSearchResultBean;
import persistence.lucene.search.FullTextSearchResource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Juan Camilo Rada
 *
 * DocumentSearchService provides business operations for document search.
 */
@Component
public class DocumentSearchService
{
    private final FullTextSearchResource fullTextSearchResource;
    private final DocumentSearchResultMapper documentSearchResultMapper;

    private static final String [] SEARCH_FIELD_LIST =
    {
        FullTextSearchResource.DOCUMENT_NAME_FIELD,
        FullTextSearchResource.DOCUMENT_PATH_FIELD,
        FullTextSearchResource.DOCUMENT_CONTENT_FIELD
    };

    @Autowired
    DocumentSearchService(final FullTextSearchResource fullTextSearchResource,
                          final DocumentSearchResultMapper documentSearchResultMapper)
    {
        this.fullTextSearchResource = Preconditions.checkNotNull(fullTextSearchResource);
        this.documentSearchResultMapper = Preconditions.checkNotNull(documentSearchResultMapper);
    }

    public List<DocumentSearchResult> searchDocuments(
        final String query, final String username) throws ResourceNotAvailableException, UnspecifiedDomainException
    {
        assert !Strings.isNullOrEmpty(query);
        assert !Strings.isNullOrEmpty(username);

        try
        {
            List<DocumentSearchResultBean> searchResultBeans =
                fullTextSearchResource.getDocuments(query, username, SEARCH_FIELD_LIST);

            return documentSearchResultMapper.newBusinessObjectList(searchResultBeans);
        }

        catch (InvalidAPIUsageException e)
        {
            throw new UnspecifiedDomainException(e);
        }

        catch (IOException e)
        {
            throw new ResourceNotAvailableException(e);
        }
    }
}
