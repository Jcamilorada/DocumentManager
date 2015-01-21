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

package persistence.lucene.search;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.lucene.exception.InvalidAPIUsageException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juan Camilo Rada
 */
@Component
public class LuceneSearchResource
{
    private static final String [] SEARCH_FIELD_LIST = {"name", "path", "content"};

    QueryExecutorHelper queryExecutorHelper;
    DocumentSearchResultBeanMapper searchResultBeanMapper;

    @Autowired
    public LuceneSearchResource(
        final QueryExecutorHelper queryExecutorHelper,
        final DocumentSearchResultBeanMapper searchResultBeanMapper)
    {
        this.queryExecutorHelper = queryExecutorHelper;
        this.searchResultBeanMapper = searchResultBeanMapper;
    }

    public List<DocumentSearchResultBean> getDocuments(final String query) throws InvalidAPIUsageException, IOException
    {
        try
        {
            List<QueryResult> queryResultList = queryExecutorHelper.executeQuery(query, SEARCH_FIELD_LIST);
            List<DocumentSearchResultBean> documentSearchResultBeans = new ArrayList<>(queryResultList.size());

            documentSearchResultBeans.addAll(
                queryResultList.stream().map(queryResult -> searchResultBeanMapper.newBusinessObjectBean(
                    queryResult.getDocument(),
                    queryResult.getScore(),
                    queryResult.getFragments())).collect(Collectors.toList()));

            return documentSearchResultBeans;
        }

        catch (InvalidTokenOffsetsException | ParseException e)
        {
            throw new InvalidAPIUsageException(e);
        }
    }
}
