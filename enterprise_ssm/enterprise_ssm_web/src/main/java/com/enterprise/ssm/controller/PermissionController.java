package com.enterprise.ssm.controller;

import com.enterprise.ssm.domain.Permission;
import com.enterprise.ssm.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    /**
     * 权限添加
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 查找所有权限信息
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name="size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    /**
     * 删除单个权限
     * @param perid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletePermission.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteUser(@RequestParam(name="perid",required = true) Integer perid) throws Exception {
        permissionService.deletePermissionById(perid);
        return "redirect:findAll.do";
    }

}
