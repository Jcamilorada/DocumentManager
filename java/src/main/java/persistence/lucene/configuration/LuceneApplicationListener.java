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

package persistence.lucene.configuration;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import configuration.ApplicationProperties;
import configuration.RepositoryProperties;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Juan Camilo Rada
 *
 * Lucene Spring application listener. The listener of create to add the repository of the documents to the index.
 *
 */
@Component
class LuceneApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    private final Directory luceneDirectory;
    private final RepositoryProperties repositoryProperties;
    private final ApplicationProperties applicationProperties;

    @Autowired
    public LuceneApplicationListener(
        final Directory luceneDirectory,
        final RepositoryProperties repositoryProperties,
        ApplicationProperties applicationProperties)
    {
        this.luceneDirectory = Preconditions.checkNotNull(luceneDirectory);
        this.repositoryProperties = Preconditions.checkNotNull(repositoryProperties);
        this.applicationProperties = Preconditions.checkNotNull(applicationProperties);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);

        try
        {
            IndexWriter indexWriter = new IndexWriter(luceneDirectory, config);
            File documentsFolder = new File(repositoryProperties.getDocumentsPath());

            File[] files = documentsFolder.listFiles();
            for (File file : files)
            {
                addDocument(indexWriter, file.getName(), file.getPath(), Files.toString(file, Charset.forName("UTF-8")));
            }

            indexWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Add the document to lucene Index.
     *
     * @param indexWriter the index writer to add documents.
     * @param name the document name.
     * @param path the document path.
     * @param content the string representation of document content.
     *
     * @throws IOException if there is a problem updated lucene index.
     */
    private void addDocument(IndexWriter indexWriter, String name, String path, String content) throws IOException {
        Document document = new Document();
        document.add(new TextField(LuceneConfiguration.DOCUMENT_NAME_FIELD, name, Field.Store.YES));
        document.add(new TextField(LuceneConfiguration.DOCUMENT_PATH_FIELD, path, Field.Store.YES));
        document.add(new TextField(LuceneConfiguration.DOCUMENT_CONTENT_FIELD, content, Field.Store.YES));
        indexWriter.addDocument(document);
    }
}
