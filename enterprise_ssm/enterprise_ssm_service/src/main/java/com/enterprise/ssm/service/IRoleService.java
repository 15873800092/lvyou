package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Permission;
import com.enterprise.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll(Integer page,Integer size) throws Exception;

    void save(Role role) throws Exception;

    Role findById(Integer roleId);

    List<Permission> findOtherPermissions(Integer roleId);

    void addPermissionToRole(Integer roleId, Integer[] permissionIds);

    void deleteRoleByIds(Integer[] roleIds);

    void deleteRoleById(Integer roleId);
}
