package com.enterprise.ssm.controller;


import com.enterprise.ssm.domain.Category;
import com.enterprise.ssm.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;



    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv=new ModelAndView();
        List<Category> categoryList=categoryService.findAll();
        mv.addObject("categoryList",categoryList);
        mv.setViewName("/header.jsp");
        return  mv;
    }



}
