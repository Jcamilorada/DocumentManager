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

package persistence.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author Jairo Andres Velasco
 */
public class DocumentMetadataRepository
{
	@Autowired private MongoOperations mongoOps;
	@Autowired private IDocumentAccessPermissionsRepository permissionsRepository;
	@Autowired private MongoBeanMapper beanMapper;

	public List<DocumentAccessPermissionBean> listDocumentAccessPermissions(List<String> documentIds)
	{
		Query query = query(where("documentId").in(documentIds));
		List<DocumentAccessPermission> documentAccessRights = mongoOps.find(query, DocumentAccessPermission.class);
		return beanMapper.newDocumentAccessPermissionsDtoList(documentAccessRights);
	}

	public void updateDocumentAccessPermissions(List<DocumentAccessPermissionBean> beans)
	{
		List<DocumentAccessPermission> permissions = beanMapper.newDocumentAccessPermissionsList(beans);
		permissionsRepository.save(permissions);
	}
}
