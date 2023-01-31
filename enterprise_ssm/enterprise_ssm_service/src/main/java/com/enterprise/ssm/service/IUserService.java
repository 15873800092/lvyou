package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Role;
import com.enterprise.ssm.domain.Traveller;
import com.enterprise.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(Integer page,Integer size) throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(Integer id) throws Exception;

    List<Role> findOtherRoles(Integer id) throws Exception;

    void addRoleToUser(Integer userId, Integer[] roleIds);

    void update_pwd(UserInfo userInfo) throws Exception;

    void deleteUserById(Integer userId) throws Exception;


}
