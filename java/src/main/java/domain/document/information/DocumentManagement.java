/*
 * DocumentIndexResource.java is part of Document Manager (c) 2015.
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

package domain.document.information;

import com.google.common.base.Preconditions;
import configuration.RepositoryProperties;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.git.document.DocumentBean;
import persistence.git.document.SourceControlDocumentRepository;
import persistence.git.exception.SourceControlUnspecifiedException;
import persistence.lucene.search.DocumentIndexInformation;
import persistence.lucene.search.FullTextSearchResource;
import persistence.mongodb.security.DocumentAccessPermissionBean;
import persistence.mongodb.security.DocumentMetadataRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class DocumentManagement
{
    private final DocumentMetadataRepository metadataRepository;
    private final SourceControlDocumentRepository sourceControlDocumentRepository;
    private final RepositoryProperties repositoryProperties;
    private final DocumentMapper documentMapper;
    private final FullTextSearchResource fullTextSearchResource;

    @Autowired
    DocumentManagement(
        final DocumentMetadataRepository metadataRepository,
        final SourceControlDocumentRepository sourceControlDocumentRepository,
        final RepositoryProperties repositoryProperties,
        final FullTextSearchResource fullTextSearchResource,
        final DocumentMapper documentMapper)
    {
        this.metadataRepository = Preconditions.checkNotNull(metadataRepository);
        this.sourceControlDocumentRepository = Preconditions.checkNotNull(sourceControlDocumentRepository);
        this.repositoryProperties = Preconditions.checkNotNull(repositoryProperties);
        this.fullTextSearchResource = Preconditions.checkNotNull(fullTextSearchResource);
        this.documentMapper = Preconditions.checkNotNull(documentMapper);
    }

    public List<Document> getRepositoryDocuments() throws IOException, SourceControlUnspecifiedException
    {
        List<DocumentBean> documentBeanList =
            sourceControlDocumentRepository.getDocuments(repositoryProperties.getPath());
        List<Document> documentList = new ArrayList<>(documentBeanList.size());

        for (DocumentBean documentBean : documentBeanList)
        {
            DocumentAccessPermissionBean accessPermission =
                metadataRepository.getAccessPermission(documentBean.getPath());
            documentList.add(createDocument(accessPermission, documentBean));
        }

        return documentList;
    }

    public void AddDocumentToSearchIndex(final List<Document> document) throws IOException
    {
        Validate.noNullElements(document);

        List<DocumentIndexInformation> indexInformationList =
            document.stream().map(this::createDocumentIndexInformation).collect(Collectors.toList());
        fullTextSearchResource.addDocumentsToIndex(indexInformationList);
    }

    private DocumentIndexInformation createDocumentIndexInformation(final Document document)
    {
        DocumentIndexInformation indexInformation = new DocumentIndexInformation();
        indexInformation.setOwner(document.getOwner());
        indexInformation.setContent(document.getContent());
        indexInformation.setId(document.getId());
        indexInformation.setPath(document.getPath());
        indexInformation.setName(document.getName());

        return indexInformation;
    }

    private Document createDocument(
        final DocumentAccessPermissionBean accessPermission,
        final DocumentBean documentBean)
    {
        Document document = documentMapper.newBusinessObject(documentBean);
        document.setOwner(accessPermission.getOwner());

        return document;
    }
}
