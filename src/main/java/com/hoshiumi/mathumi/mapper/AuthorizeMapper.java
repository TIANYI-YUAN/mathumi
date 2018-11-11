package com.hoshiumi.mathumi.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hoshiumi.mathumi.entity.UserEntity;

@Component
public interface AuthorizeMapper {
	
	
	@Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
        @Result(property = "userid",  column = "userid"),
        @Result(property = "username", column = "username"),
        @Result(property = "password_salthash", column = "password_salthash"),
        @Result(property = "firstname", column = "firstname"),
        @Result(property = "lastname", column = "lastname"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "address", column = "address"),
        @Result(property = "logincookie", column = "logincookie")
    })
    UserEntity getOneByUsername(String username);
	
	@Update("UPDATE user SET logincookie=#{logincookie} WHERE userid =#{userid}")
    void updateLogincookie(@Param("logincookie")String logincookie,@Param("userid")Long userid);
		
	
	@Select("SELECT logincookie FROM user WHERE username = #{username}")
	String getLoginCookieByUsername(String username);
}
