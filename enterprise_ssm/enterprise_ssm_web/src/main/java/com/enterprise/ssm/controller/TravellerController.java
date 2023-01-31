package com.enterprise.ssm.controller;


import com.enterprise.ssm.domain.Traveller;
import com.enterprise.ssm.service.ITravellerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/traveller")
public class TravellerController {

    @Autowired
    private ITravellerService travellerService;

    //查询所有旅客信息
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Traveller> travellerList = travellerService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(travellerList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("traveller-list");
        return mv;
    }

    /**
     * 旅客登录
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping("login1.do")
    public String login(@RequestParam(name="name")String name,@RequestParam(name = "password")String password) throws Exception{
        Traveller traveller=travellerService.findNameAndPwd(name,password);
        System.out.println(traveller);
        if(traveller!=null && traveller.getStatus()!="N"){
            return "redirect:/index.jsp";
        }
        return "redirect:/error/404.jsp";
    }


    /**
     * 注册旅客
     * @param traveller
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public void save(Traveller traveller)throws Exception{
        travellerService.save(traveller);
    }


    /**
     * 激活旅客
     * @param tid
     * @return
     * @throws Exception
     */
    @RequestMapping("/active.do")
    public String active(@RequestParam(name = "tid",required = true)Integer tid)throws Exception{
        travellerService.active(tid);
        return "redirect:login1.jsp";
    }

    /**
     * 删除单个旅客信息
     * @param travellerId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteTraveller.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteTraveller(@RequestParam(name="tid",required = true) Integer travellerId) throws Exception {
        travellerService.deleteTravellerById(travellerId);
        return "redirect:findAll.do";
    }

    /**
     * 删除多个旅客信息
     * @param taravellersId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteTravellers.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteTravellers(@RequestParam(name="ids",required = true) Integer[] taravellersId) throws Exception {
        travellerService.deleteTravellerByIds(taravellersId);
        return "redirect:findAll.do";
    }


    /**
     * 根据ID查询旅客详情
     * @param travellerId
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "tid", required = true) Integer travellerId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Traveller traveller = travellerService.findById(travellerId);
        mv.addObject("traveller",traveller);
        mv.setViewName("traveller-update");
        return mv;
    }


    //修改旅客信息
    @RequestMapping("/update.do")
    @RolesAllowed({"USER","ADMIN"})
    public String update(Traveller traveller) throws Exception{
        travellerService.update(traveller);
        return "redirect:findAll.do";
    }
}
