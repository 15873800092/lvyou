package com.enterprise.ssm.controller;


import com.enterprise.ssm.domain.Seller;
import com.enterprise.ssm.service.ISellerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ISellerService sellerService;

    //查询所有订单--分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "4")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Seller> sellerList = sellerService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(sellerList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("seller-page-list");
        return mv;
    }


    /**
     * 根据ID查询订单详情
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "sid", required = true) Integer sid) throws Exception {
        ModelAndView mv = new ModelAndView();
        Seller seller = sellerService.findById(sid);
        mv.addObject("seller",seller);
        mv.setViewName("seller-show");
        return mv;
    }


    /**
     * 删除单个商家
     * @param sid
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteSeller.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteSeller(@RequestParam(name="sid",required = true) Integer sid) throws Exception {
        sellerService.deleteSellerById(sid);
        return "redirect:findAll.do";
    }

    /**
     * 删除多个商家
     * @param sellersId
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteSellers.do")
    @RolesAllowed({"USER","ADMIN"})
    public String deleteSellers(@RequestParam(name="ids",required = true) Integer[] sellersId) throws Exception {
        sellerService.deleteSellerByIds(sellersId);
        return "redirect:findAll.do";
    }
}
