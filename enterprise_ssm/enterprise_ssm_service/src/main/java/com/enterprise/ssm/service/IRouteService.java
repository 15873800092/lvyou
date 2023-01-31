package com.enterprise.ssm.service;

import com.enterprise.ssm.domain.Route;

import java.util.List;

public interface IRouteService {
    List<Route> findAllPage(Integer page, Integer size) throws Exception;

    List<Route> findfindLikeName(Integer page, Integer size,String rname) throws Exception;

    List<Route> findAll(Integer page, Integer size)throws Exception;

    void save(Route route) throws Exception;

    void deleteRouteById(Integer roid)throws Exception;

    void deleteRouteByIds(Integer[] routesId)throws Exception;

    Route findById(Integer roid)throws Exception;
}
