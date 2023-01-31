package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Traveller;

import java.util.List;

public interface ITravellerService {
    List<Traveller> findAll(Integer page, Integer size) throws Exception;

    void deleteTravellerById(Integer travellerId) throws Exception;

    void deleteTravellerByIds(Integer[] taravellersId) throws Exception;

    Traveller findById(Integer travellerId) throws  Exception;

    void update(Traveller traveller)throws  Exception;

    Traveller findNameAndPwd(String name, String password) throws Exception;

    void save(Traveller traveller) throws Exception;

    void active(Integer tid) throws Exception;
}
