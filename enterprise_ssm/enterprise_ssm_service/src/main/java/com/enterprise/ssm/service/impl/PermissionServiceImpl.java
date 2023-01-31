package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.IPermissionDao;
import com.enterprise.ssm.domain.Permission;
import com.enterprise.ssm.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService{

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public void save(Permission permission) throws Exception{
        permissionDao.save(permission);
    }

    @Override
    public void deletePermissionById(Integer perid) throws Exception {
        //从role_permission表中删除
        permissionDao.deleteFromRole_PermissionByPermissionId(perid);
        //从role表中删除
        permissionDao.deletePermissionById(perid);
    }

    @Override
    public List<Permission> findAll(Integer page,Integer size) throws Exception{
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }
}
