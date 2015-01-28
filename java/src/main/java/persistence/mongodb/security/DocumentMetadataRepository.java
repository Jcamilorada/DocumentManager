/*
 * DocumentMetadataRepository.java is part of Document Manager (c) 2015.
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

package persistence.mongodb.security;

import com.google.common.base.Preconditions;
import domain.document.security.DocumentAccessPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Jairo Andres Velasco
 */
@Component
public class DocumentMetadataRepository
{
    private final MongoOperations mongoOperations;
    private DocumentPermissionRepository documentPermissionRepository;
    private final MongoBeanMapper mongoBeanMapper;

    @Autowired
    DocumentMetadataRepository(
        final MongoOperations mongoOperations,
        final DocumentPermissionRepository documentPermissionRepository,
        final MongoBeanMapper mongoBeanMapper)
    {
        this.mongoOperations = Preconditions.checkNotNull(mongoOperations, "mongoOperations cannot be null.");
        this.documentPermissionRepository = Preconditions.checkNotNull(documentPermissionRepository, "accessPermissionsRepository cannot be null.");
        this.mongoBeanMapper = Preconditions.checkNotNull(mongoBeanMapper, "mongoBeanMapper cannot be null.");
    }

    public DocumentAccessPermissionBean getAccessPermission(final String documentId)
    {
        Query searchUserQuery = new Query(Criteria.where("documentId").is(documentId));
        DocumentAccessPermissionBean permissionBean =  mongoOperations.findOne(searchUserQuery ,DocumentAccessPermissionBean.class);

        return permissionBean;
    }

    public List<DocumentAccessPermission> listDocumentAccessPermissions(final List<String> documentIds)
    {
        Query query = query(where("documentId").in(documentIds));
        List<DocumentAccessPermissionBean> documentAccessRights = mongoOperations.find(query, DocumentAccessPermissionBean.class);
        return mongoBeanMapper.newDocumentAccessPermissionsDtoList(documentAccessRights);
    }

    public void updateDocumentAccessPermissions(final List<DocumentAccessPermission> beans)
    {
        List<DocumentAccessPermissionBean> permissions = mongoBeanMapper.newDocumentAccessPermissionsList(beans);
        documentPermissionRepository.save(permissions);
    }

    public void updateDocumentAccessPermissions(final DocumentAccessPermissionBean beans)
    {

        documentPermissionRepository.save(beans);
    }
}
