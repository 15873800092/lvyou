package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Seller;

import java.util.List;

public interface ISellerService {
    List<Seller> findAll(Integer page, Integer size)throws Exception;

    Seller findById(Integer sid) throws Exception;

    void deleteSellerById(Integer sid)throws Exception;

    void deleteSellerByIds(Integer[] sellersId)throws Exception;
}
