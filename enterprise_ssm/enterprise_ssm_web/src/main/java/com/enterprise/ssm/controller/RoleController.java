package com.enterprise.ssm.controller;

import com.enterprise.ssm.domain.Permission;
import com.enterprise.ssm.domain.Role;
import com.enterprise.ssm.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 添加角色
     * @param role
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有角色信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(roleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 角色详情查询
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "rid", required = true) Integer roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);

        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 根据roleId查询role，并查询出可以添加的权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    @RolesAllowed({"USER","ADMIN"})
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "rid", required = true) Integer roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询role
        Role role = roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> otherPermissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);
        mv.setViewName("role-permission-add");
        return mv;

    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    @RolesAllowed({"USER","ADMIN"})
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) Integer roleId, @RequestParam(name = "ids", required = true) Integer[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    /**
     * 删除多个权限
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRoles.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteRole(@RequestParam(name="ids",required = true) Integer[] roleIds) throws Exception {
        roleService.deleteRoleByIds(roleIds);
        return "redirect:findAll.do";
    }

    /**
     * 删除单个权限
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRole.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteRole(@RequestParam(name="rid",required = true) Integer roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }
}
