package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ISellerDao {

    @Select("select * from tab_seller")
    @Results({
            @Result(id = true,property = "sid",column = "sid"),
            @Result(property = "sname",column = "sname"),
            @Result(property = "consphone",column = "consphone"),
            @Result(property = "address",column = "address")
    })
    List<Seller> findAll();


    @Select("select * from tab_seller where sid=#{sid}")
    @Results({
            @Result(id = true,property = "sid",column = "sid"),
            @Result(property = "sname",column = "sname"),
            @Result(property = "consphone",column = "consphone"),
            @Result(property = "address",column = "address")
    })
    Seller findById(Integer sid);


    @Delete("delete from tab_seller sid = #{sid}")
    void deleteSellerById(@Param("sid") Integer sid);
}
