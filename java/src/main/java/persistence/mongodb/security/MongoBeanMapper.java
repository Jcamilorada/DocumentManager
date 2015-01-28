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

package persistence.mongodb.security;

import domain.document.security.DocumentAccessPermission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jairo Andres Velasco
 */
@Component
class MongoBeanMapper
{
    public List<DocumentAccessPermission> newDocumentAccessPermissionsDtoList(List<DocumentAccessPermissionBean> documentAccessRights)
    {
        return documentAccessRights.stream().map(this::newDocumentAccessPermissionDto).collect(Collectors.toList());
    }

    public DocumentAccessPermission newDocumentAccessPermissionDto(final DocumentAccessPermissionBean rights)
    {
        DocumentAccessPermission documentAccessPermission = new DocumentAccessPermission();
        documentAccessPermission.setId(rights.getId());
        documentAccessPermission.setDocumentId(rights.getDocumentId());
        documentAccessPermission.setAbleToDelete(rights.isAbleToDelete());
        documentAccessPermission.setAbleToRead(rights.isAbleToRead());
        documentAccessPermission.setAbleToUpdate(rights.isAbleToUpdate());
        documentAccessPermission.setOwner(rights.getOwner());
        return documentAccessPermission;
    }

    public List<DocumentAccessPermissionBean> newDocumentAccessPermissionsList(final List<DocumentAccessPermission> beans)
    {
        return beans.stream().map(this::newDocumentAccessPermission).collect(Collectors.toList());
    }

    public DocumentAccessPermissionBean newDocumentAccessPermission(final DocumentAccessPermission bean)
    {
        DocumentAccessPermissionBean documentAccessPermissionBean = new DocumentAccessPermissionBean();
        documentAccessPermissionBean.setId(bean.getId());
        documentAccessPermissionBean.setOwner(bean.getOwner());
        documentAccessPermissionBean.setDocumentId(bean.getDocumentId());
        documentAccessPermissionBean.setAbleToDelete(bean.isAbleToDelete());
        documentAccessPermissionBean.setAbleToRead(bean.isAbleToRead());
        documentAccessPermissionBean.setAbleToUpdate(bean.isAbleToUpdate());
        return  documentAccessPermissionBean;
    }
}
