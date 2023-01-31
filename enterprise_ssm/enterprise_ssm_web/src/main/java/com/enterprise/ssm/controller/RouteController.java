package com.enterprise.ssm.controller;


import com.enterprise.ssm.domain.Route;
import com.enterprise.ssm.service.IRouteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private IRouteService routeService;

    private Integer id=0;


    @RequestMapping("/saveId")
    public String saveId(@RequestParam(name = "cid",required = true)Integer cid) throws Exception{
        id=cid;
        return "redirect:route-list.jsp";
    }

    @RequestMapping("/findAllPage.do")
    public ModelAndView findAllPage(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "8")Integer size)throws  Exception{
        ModelAndView mv=new ModelAndView();
        List<Route> routeList=routeService.findAllPage(page,size);
        PageInfo pageInfo=new PageInfo(routeList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/route-list.jsp");
        return mv;
    }

    @RequestMapping("/findLikeName")
    public ModelAndView findLikeName(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "8")Integer size,@RequestParam(name = "rname",required = true)String rname)throws  Exception{
        ModelAndView mv=new ModelAndView();
        List<Route> routeList=routeService.findfindLikeName(page,size,rname);
        PageInfo pageInfo=new PageInfo(routeList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("/route-list.jsp");
        return mv;
    }

    //查询所有旅游路线信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Route> routeList = routeService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(routeList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("route-list");
        return mv;
    }

    //保存商品信息
    @RequestMapping("/save.do")
    @RolesAllowed({"USER","ADMIN"})
    public String save(Route route) throws Exception{
        routeService.save(route);
        return "redirect:findAll.do";
    }

    /**
     * 删除单个旅游路线
     * @param roid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRoute.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteRoute(@RequestParam(name="roid",required = true) Integer roid) throws Exception {
        routeService.deleteRouteById(roid);
        return "redirect:findAll.do";
    }

    /**
     * 删除多个旅游路线
     * @param routesId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteRoutes.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteRoutes(@RequestParam(name="ids",required = true) Integer[] routesId) throws Exception {
        routeService.deleteRouteByIds(routesId);
        return "redirect:findAll.do";
    }

    /**
     * 根据ID查询产品详情
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "roid", required = true) Integer roid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Route route = routeService.findById(roid);
        mv.addObject("route",route);
        mv.setViewName("route-show");
        return mv;
    }
}
