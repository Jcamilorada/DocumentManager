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
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.TermsFilter;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.FieldCacheTermsFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Camilo Rada
 */
@Component
class QueryExecutorHelper
{
    private RAMDirectory directory;
    private StandardAnalyzer standardAnalyzer;

    @Autowired
    QueryExecutorHelper(final RAMDirectory directory, final StandardAnalyzer standardAnalyzer)
    {
        this.directory =Preconditions.checkNotNull(directory, "IndexReader cannot be null.");
        this.standardAnalyzer = Preconditions.checkNotNull(standardAnalyzer, "StandardAnalyzer cannot be null.");
    }

    List<QueryResult> queryDocuments(
        final String query, final String[] fields, final String user) throws ParseException, IOException, InvalidTokenOffsetsException
    {
        int hitsPerPage = 10;
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields, new StandardAnalyzer());

        Query luceneQuery = queryParser.parse(query);
        Highlighter highlighter = new Highlighter(new QueryScorer(luceneQuery));

        DirectoryReader indexReader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
        TermsFilter filter = new TermsFilter(new Term(FullTextSearchResource.DOCUMENT_OWNER, user.toLowerCase()));
        searcher.search(luceneQuery, filter, collector);

        ScoreDoc[] docs = collector.topDocs().scoreDocs;
        List<QueryResult> resultBeans = new ArrayList<>(docs.length);
        for (ScoreDoc doc : docs)
        {
            Document document =  searcher.doc(doc.doc);
            String text = document.get(FullTextSearchResource.DOCUMENT_CONTENT_FIELD);
            TokenStream tokenStream = TokenSources.getAnyTokenStream(
                searcher.getIndexReader(), doc.doc, FullTextSearchResource.DOCUMENT_CONTENT_FIELD, standardAnalyzer);
            TextFragment[] fragments = highlighter.getBestTextFragments(tokenStream, text, false, 10);

            resultBeans.add(new QueryResult(doc.doc, doc.score, document, fragments));
        }

        indexReader.close();
        return resultBeans;
    }
}
