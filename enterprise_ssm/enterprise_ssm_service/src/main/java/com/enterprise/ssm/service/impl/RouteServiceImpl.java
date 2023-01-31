package com.enterprise.ssm.service.impl;

import com.enterprise.ssm.dao.IRouteDao;
import com.enterprise.ssm.domain.Route;
import com.enterprise.ssm.service.IRouteService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RouteServiceImpl implements IRouteService {

    @Autowired
    private IRouteDao routeDao;

    @Override
    public List<Route> findAllPage(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return routeDao.findAll();
    }

    @Override
    public List<Route> findfindLikeName(Integer page, Integer size,String rname) throws Exception {
        PageHelper.startPage(page,size);
        return routeDao.findLikeName("%"+rname+"%");
    }

    @Override
    public List<Route> findAll(Integer page, Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return routeDao.findAll();
    }

    @Override
    public void save(Route route) throws Exception {
        routeDao.save(route);
    }

    @Override
    public void deleteRouteById(Integer roid) throws Exception {
        routeDao.deleteRouteById(roid);
    }

    @Override
    public void deleteRouteByIds(Integer[] routesId) throws Exception {
        for(Integer routeId:routesId){
            routeDao.deleteRouteById(routeId);
        }
    }

    @Override
    public Route findById(Integer roid) throws Exception {
        return routeDao.findById(roid);
    }
}
