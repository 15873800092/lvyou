package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll() throws Exception;
}
