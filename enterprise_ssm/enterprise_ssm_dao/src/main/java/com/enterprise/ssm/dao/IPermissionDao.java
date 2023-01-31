package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    //多表操作
    @Select("select * from permission where perid in (select permissionId from role_permission where roleId=#{roleId} )")
    public List<Permission> findPermissionByRoleId(Integer roleId) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Delete("delete from role_permission where permissionId = #{perid}")
    void deleteFromRole_PermissionByPermissionId(Integer perid);

    @Delete("delete from permission where perid = #{perid}")
    void deletePermissionById(Integer perid);
}
