package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Category;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ICategoryDao {

    @Select("select * from tab_category")
    @Results({
            @Result(id = true,property = "cid",column = "cid"),
            @Result(property = "cname",column = "cname"),
            @Result(property = "routeList",column = "cid",javaType =java.util.List.class,many = @Many(select = "com.enterprise.ssm.dao.IRouteDao.findRouteByCid"))
    })
    List<Category> findAll();


    /**
     * 根据cid查询线路信息
     * @param cid
     * @return
     */
    @Select("select * from tab_category  where cid=#{cid} ")
    @Results({
            @Result(id = true,property = "cid",column = "cid"),
            @Result(property = "cname",column = "cname"),
            @Result(property = "routeList",column = "cid",javaType =java.util.List.class,many = @Many(select = "com.enterprise.ssm.dao.IRouteDao.findRouteByCid"))
    })
    Category findById(Integer cid);
}
