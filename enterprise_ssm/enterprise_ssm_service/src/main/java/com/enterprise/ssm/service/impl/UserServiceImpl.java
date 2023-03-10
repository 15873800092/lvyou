package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.IUserDao;
import com.enterprise.ssm.domain.Role;
import com.enterprise.ssm.domain.Traveller;
import com.enterprise.ssm.domain.UserInfo;
import com.enterprise.ssm.service.IUserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserInfo findById(Integer id) throws Exception{

        return  userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer id) throws Exception {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) {
        for (Integer roleId:roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public void update_pwd(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.update_pwd(userInfo);
    }

    @Override
    public void deleteUserById(Integer userId) throws Exception {
        //从user_role表中删除
        userDao.deleteFromUser_RoleByUserId(userId);
        userDao.deleteUserById(userId);
    }




    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try{
            userInfo=userDao.findByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
        }
        //自己的用户对象封装成UserDetails对象
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
        }
        return list;
    }
}
