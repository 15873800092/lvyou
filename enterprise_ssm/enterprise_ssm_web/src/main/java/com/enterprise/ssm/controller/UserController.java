package com.enterprise.ssm.controller;

import com.enterprise.ssm.domain.Role;
import com.enterprise.ssm.domain.Traveller;
import com.enterprise.ssm.domain.UserInfo;
import com.enterprise.ssm.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 根据ID查询用户信息
     * @param uid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(Integer uid) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(uid);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    /**
     * 根据ID查询用户信息
     * @param uid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findOneById.do")
    public ModelAndView findOneById(Integer uid) throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(uid);
        mv.addObject("user",userInfo);
        mv.setViewName("user-update-pwd");
        return mv;
    }

    /**
     * 修改用户密码
     * @param uid
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping("/update_pwd")
    public String update_pwd(@RequestParam(name = "uid")Integer uid,@RequestParam(name = "password")String password) throws Exception{
        UserInfo userInfo = userService.findById(uid);
        userInfo.setPassword(password);
        userService.update_pwd(userInfo);
        return "redirect:findAll.do";
    }


    /**
     * 用户添加
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有用户信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }


    /**
     * 查询用户以及用户可以添加角色
     * @param userid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "uid", required = true) Integer userid) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    @RolesAllowed("ADMIN")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) Integer userId, @RequestParam(name = "ids", required = true) Integer[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 删除单个用户
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteUser.do")
    @RolesAllowed({"ADMIN"})
    public String deleteUser(@RequestParam(name="uid",required = true) Integer userId) throws Exception {
        userService.deleteUserById(userId);
        return "redirect:findAll.do";
    }


}
