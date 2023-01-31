package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Role;
import com.enterprise.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "uid",javaType = java.util.List.class,many = @Many(select = "com.enterprise.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where uid=#{uid}")
    @Results({
            @Result(id = true, property = "uid", column = "uid"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "uid",javaType = java.util.List.class,many = @Many(select = "com.enterprise.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(Integer uid) throws Exception;

    @Select("select * from role where rid not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(Integer userId) throws Exception;

    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Update("update from users set password = #{password} where uid = #{uid}")
    void update_pwd(UserInfo userInfo) throws Exception;

    @Delete("delete from users_role where userId=#{userId}")
    void deleteFromUser_RoleByUserId(Integer userId);

    @Delete("delete from users where uid = #{userId}")
    void deleteUserById(Integer userId);



}
