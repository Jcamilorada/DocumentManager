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

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Jairo Andres Velasco
 */
interface DocumentPermissionRepository extends MongoRepository<DocumentAccessPermissionBean, String>
{
    DocumentAccessPermissionBean findByDocumentId(String documentId);
}
