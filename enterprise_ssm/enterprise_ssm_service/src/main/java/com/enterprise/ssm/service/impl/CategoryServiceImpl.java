package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.ICategoryDao;
import com.enterprise.ssm.domain.Category;
import com.enterprise.ssm.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> findAll() throws Exception {
        return categoryDao.findAll();
    }
}
