package com.hoshiumi.mathumi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import com.hoshiumi.mathumi.entity.UserEntity;

@SuppressWarnings("unused")
@Component
public interface UserMapper {

    @Select("SELECT * FROM user")
    @Results({
        @Result(property = "userid",  column = "userid"),
        @Result(property = "username", column = "username"),
        @Result(property = "password_salthash", column = "password_salthash"),
        @Result(property = "firstname", column = "firstname"),
        @Result(property = "lastname", column = "lastname"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "address", column = "address")
    
    })
    List<UserEntity> getAll();

    @Select("SELECT * FROM user WHERE userid = #{userid}")
    @Results({
        @Result(property = "userid",  column = "userid"),
        @Result(property = "username", column = "username"),
        @Result(property = "password_salthash", column = "password_salthash"),
        @Result(property = "firstname", column = "firstname"),
        @Result(property = "lastname", column = "lastname"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "address", column = "address")
    })
    UserEntity getOneByUserid(Long userid);

    @Insert("INSERT INTO user(username,password_salthash,firstname,lastname,"+
            "phone,address)" +
            "VALUES(#{username},#{password_salthash},#{firstname},"+
            "#{lastname},#{phone},#{address});")
    void insert(UserEntity user);

    // @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    // void update(UserEntity user);

    @Delete("DELETE FROM users WHERE userid =#{userid}")
    void delete(Long userid);

}