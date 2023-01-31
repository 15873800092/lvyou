package com.enterprise.ssm.dao;

import com.enterprise.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ITravellerDao {

    @Select("select * from traveller where tid in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(Integer ordersId) throws Exception;


    //根据id查询产品
    @Select("select * from traveller")
    @Results({
            @Result(id = true, property = "tid", column = "tid"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "credentialsType", column = "credentialsType"),
            @Result(property = "credentialsNum", column = "credentialsNum"),
            @Result(property = "travellerType", column = "travellerType"),
            @Result(property = "password",column = "password"),
            @Result(property = "status",column = "status"),
            @Result(property = "email",column = "email")
    })
    List<Traveller> findAll() throws Exception;



    @Delete("delete from traveller where travellerId = #{travellerId}")
    void deleteTravellerById(@Param("travellerId") Integer travellerId);

    @Select("select * from traveller where tid = #{travellerId}")
    @Results({
            @Result(id=true,property = "tid",column = "tid"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "credentialsType", column = "credentialsType"),
            @Result(property = "credentialsNum", column = "credentialsNum"),
            @Result(property = "travellerType", column = "travellerType"),
            @Result(property = "password",column = "password"),
            @Result(property = "status",column = "status"),
            @Result(property = "email",column = "email")
    })
    Traveller findById(Integer travellerId);

    @Update("update from traveller set name=#{name},sex=#{sex},phoneNum=#{phoneNum},credentialsType=#{credentialsType},credentialsNum=#{credentialsNum},travellerType=#{travellerType},status=#{status},password=#{password},email=#{email}")
    void update(Traveller traveller);

    @Select("select * from traveller where name=#{name} and password=#{password}")
    Traveller findNameAndPwd(@Param("name") String name, @Param("password") String password);

    @Insert("insert into traveller(name,password,email,credentialsNum,phoneNum,sex,credentialsType,travellerType,status) values(#{name},#{password},#{email},#{credentialsNum},#{phoneNum},#{sex},#{credentialsType},#{travellerType},#{status})")
    void save(Traveller traveller);
}
