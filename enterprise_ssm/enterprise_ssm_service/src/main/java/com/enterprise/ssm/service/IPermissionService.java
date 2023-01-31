package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    public List<Permission> findAll(Integer page,Integer size) throws Exception;

    void save(Permission permission) throws Exception;

    void deletePermissionById(Integer perid) throws Exception;
}
