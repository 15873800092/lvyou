package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.IRoleDao;
import com.enterprise.ssm.domain.Permission;
import com.enterprise.ssm.domain.Role;
import com.enterprise.ssm.service.IRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(Integer roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] permissionIds) {
        for (int permissionId: permissionIds){
            roleDao.addPermissionToRole(roleId,permissionId);
        }

    }

    @Override
    public void deleteRoleByIds(Integer[] roleIds) {
        for(int roleId:roleIds){
            //从user_role表中删除
            roleDao.deleteFromUser_RoleByRoleId(roleId);
            //从role_permission表中删除
            roleDao.deleteFromRole_PermissionByRoleId(roleId);
            //从role表中删除
            roleDao.deleteRoleById(roleId);
        }
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public List<Role> findAll(Integer page,Integer size) throws Exception{
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }
}
