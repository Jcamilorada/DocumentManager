/*
 * MongoBeanMapper.java is part of Document Manager (c) 2015.
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jairo Andres Velasco
 */
class MongoBeanMapper
{
	public List<DocumentAccessPermissionBean> newDocumentAccessPermissionsDtoList(List<DocumentAccessPermission> documentAccessRights)
	{
		return documentAccessRights.stream().map(this::newDocumentAccessPermissionDto).collect(Collectors.toList());
	}

	public DocumentAccessPermissionBean newDocumentAccessPermissionDto(DocumentAccessPermission rights)
	{
		DocumentAccessPermissionBean dto = new DocumentAccessPermissionBean();
		dto.setId(rights.getId());
		dto.setDocumentId(rights.getDocumentId());
		dto.setAbleToDelete(rights.isAbleToDelete());
		dto.setAbleToRead(rights.isAbleToRead());
		dto.setAbleToUpdate(rights.isAbleToUpdate());
		dto.setOwner(rights.getOwner());
		dto.setUser(rights.getUser());
		return dto;
	}

	public List<DocumentAccessPermission> newDocumentAccessPermissionsList(List<DocumentAccessPermissionBean> beans)
	{
		return beans.stream().map(this::newDocumentAccessPermission).collect(Collectors.toList());
	}

	public DocumentAccessPermission newDocumentAccessPermission(DocumentAccessPermissionBean bean)
	{
		DocumentAccessPermission rights = new DocumentAccessPermission();
		rights.setId(bean.getId());
		rights.setUser(bean.getUser());
		rights.setOwner(bean.getOwner());
		rights.setDocumentId(bean.getDocumentId());
		rights.setAbleToDelete(bean.isAbleToDelete());
		rights.setAbleToRead(bean.isAbleToRead());
		rights.setAbleToUpdate(bean.isAbleToUpdate());
		return  rights;
	}
}
