package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.ISellerDao;
import com.enterprise.ssm.domain.Seller;
import com.enterprise.ssm.service.ISellerService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {

    @Autowired
    private ISellerDao sellerDao;

    @Override
    public List<Seller> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sellerDao.findAll();
    }

    @Override
    public Seller findById(Integer sid) throws Exception {
        return sellerDao.findById(sid);
    }

    @Override
    public void deleteSellerById(Integer sid) throws Exception {
        sellerDao.deleteSellerById(sid);
    }

    @Override
    public void deleteSellerByIds(Integer[] sellersId) throws Exception {
        for(Integer selelrId : sellersId){
            deleteSellerById(selelrId);
        }
    }
}
