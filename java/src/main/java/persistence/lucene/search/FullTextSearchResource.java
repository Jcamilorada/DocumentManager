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

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.git.document.DocumentBean;
import persistence.lucene.configuration.IndexWriterConfigFactory;
import persistence.lucene.exception.InvalidAPIUsageException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juan Camilo Rada
 */
@Component
public class FullTextSearchResource
{
    public static final String DOCUMENT_NAME_FIELD = "NAME";
    public static final String DOCUMENT_PATH_FIELD = "PATH";
    public static final String DOCUMENT_CONTENT_FIELD = "CONTENT";
    public static final String DOCUMENT_OWNER = "DOCUMENT_OWNER";

    private final QueryExecutorHelper queryExecutorHelper;
    private final DocumentSearchResultBeanMapper searchResultBeanMapper;
    private final IndexWriter indexWriter;

    @Autowired
    FullTextSearchResource(
        final QueryExecutorHelper queryExecutorHelper,
        final DocumentSearchResultBeanMapper searchResultBeanMapper,
        final IndexWriter indexWriter)
    {
        this.queryExecutorHelper = Preconditions.checkNotNull(queryExecutorHelper);
        this.searchResultBeanMapper = Preconditions.checkNotNull(searchResultBeanMapper);
        this.indexWriter = Preconditions.checkNotNull(indexWriter);
    }

    /**
     *
     *
     * @param query the string representation of user query.
     * @param user the user document.
     *
     * @return the list of search result with document and match information.
     *
     * @throws InvalidAPIUsageException
     * @throws IOException
     */
    public List<DocumentSearchResultBean> getDocuments(
        final String query, final String user, final String[] fields) throws InvalidAPIUsageException, IOException
    {
        assert !Strings.isNullOrEmpty(user);
        assert !Strings.isNullOrEmpty(query);

        try
        {
            List<QueryResult> queryResultList = queryExecutorHelper.queryDocuments(query, fields, user);
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

    public void addDocumentsToIndex(
        final List<DocumentIndexInformation> documentIndexInformationList) throws IOException
    {
        Preconditions.checkNotNull(documentIndexInformationList, "documentIndexInformationList cannot be null");

        for (DocumentIndexInformation indexInformation : documentIndexInformationList)
        {
            Document document = new Document();
            document.add(new TextField(DOCUMENT_OWNER, indexInformation.getOwner(), Field.Store.YES));
            document.add(new TextField(DOCUMENT_NAME_FIELD, indexInformation.getName(), Field.Store.YES));
            document.add(new TextField(DOCUMENT_PATH_FIELD, indexInformation.getPath(), Field.Store.YES));
            document.add(new TextField(DOCUMENT_CONTENT_FIELD, indexInformation.getContent(), Field.Store.YES));
            indexWriter.addDocument(document);
        }

        indexWriter.close();
    }
}
