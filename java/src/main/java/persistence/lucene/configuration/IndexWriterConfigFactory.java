/*
 * IndexWriterConfigFactory.java is part of Document Manager (c) 2015.
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

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jrada on 1/27/2015.
 */
@Component
public class IndexWriterConfigFactory
{
    private final StandardAnalyzer standardAnalyzer;
    @Autowired
    private IndexWriter indexWriter;

    @Autowired
    IndexWriterConfigFactory(final StandardAnalyzer standardAnalyzer)
    {
        this.standardAnalyzer = standardAnalyzer;
    }

    public IndexWriterConfig getInstance()
    {
        return new IndexWriterConfig(Version.LUCENE_4_10_3, standardAnalyzer);
    }

    public IndexWriter getInstance2()
    {
        return indexWriter;
    }
}
