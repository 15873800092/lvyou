package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Route;
import org.apache.ibatis.annotations.*;

import java.util.Calendar;
import java.util.List;

public interface IRouteDao {

    /**
     * 根据用户id查询账户信息
     * @param cid
     * @return
     */
    @Select("select * from tab_route where cid = #{cid}")
    List<Route> findRouteByCid(Integer cid);



    /**
     * 查询所有账户，并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from tab_route")
    @Results({
            @Result(id = true,property = "roid",column = "roid"),
            @Result(property = "rname",column = "rname"),
            @Result(property = "price",column = "price"),
            @Result(property = "routeIntroduce",column = "routeIntroduce"),
            @Result(property = "rflag",column = "rflag"),
            @Result(property = "rdate",column = "rdate"),
            @Result(property = "isThemeTour",column = "isThemeTour"),
            @Result(property = "count",column = "count"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "rimage",column = "rimage"),
            @Result(property = "sid",column = "sid"),
            @Result(property = "sourceId",column = "sourceId"),
            @Result(property = "category",column = "cid",javaType = Calendar.class,one=@One(select="com.enterprise.ssm.dao.ICategoryDao.findById"))
    })
    List<Route> findAll();


    @Select("select count(*) from tab_route where cid=#{cid}")
    int total(Integer cid);


    @Select("select * from tab_route where rname like #{rname} ")
    List<Route> findLikeName(String rname);

    @Insert("insert into tab_route(rname,price,routeIntroduce,rflag,rdate,isThemeTour,count,cid,rimage,sid,sourceld) values(#{rname},#{price},#{routeIntroduce},#{rflag},#{rdate},#{isThemeTour},#{count},#{cid},#{rimage},#{sid},#{sourceId])")
    void save(Route route);

    @Delete("delete from tab_route where roid =#{roid}")
    void deleteRouteById(@Param("roid") Integer roid);


    @Select("select * from tab_route where roid =#{roid]")
    @Results({
            @Result(id = true,property = "roid",column = "roid"),
            @Result(property = "rname",column = "rname"),
            @Result(property = "price",column = "price"),
            @Result(property = "routeIntroduce",column = "routeIntroduce"),
            @Result(property = "rflag",column = "rflag"),
            @Result(property = "rdate",column = "rdate"),
            @Result(property = "isThemeTour",column = "isThemeTour"),
            @Result(property = "count",column = "count"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "rimage",column = "rimage"),
            @Result(property = "sid",column = "sid"),
            @Result(property = "sourceId",column = "sourceId"),
            @Result(property = "category",column = "cid",javaType = Calendar.class,one=@One(select="com.enterprise.ssm.dao.ICategoryDao.findById"))
    })
    Route findById(Integer roid);
}
