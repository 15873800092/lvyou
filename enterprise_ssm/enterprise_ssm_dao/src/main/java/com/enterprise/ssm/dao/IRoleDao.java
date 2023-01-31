package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Permission;
import com.enterprise.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);



    //根据用户id查询出所有对应的角色
    @Select("select * from role where rid in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "rid", column = "rid"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions",column = "rid",javaType = java.util.List.class,many = @Many(select = "com.enterprise.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(Integer userId) throws Exception;

    @Select("select * from role where rid = #{roleId}")
    Role findById(Integer roleId);

    @Select("select * from permission where perid not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermissions(Integer roleId);

    @Insert("insert into role_permission (roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    @Delete("delete from role where rid = #{roleId}")
    void deleteRoleById(@Param("roleId") Integer roleId);

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(@Param("roleId") Integer roleId);
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(@Param("roleId") Integer roleId);
}
