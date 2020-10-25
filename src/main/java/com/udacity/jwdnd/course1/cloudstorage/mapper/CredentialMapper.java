package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllCredentialsForUser(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredentialById(Integer credentialid);

    @Delete("DELETE FROM CREDENTIALS where credentialId = #{credentialId}")
    int delete(@Param("credentialId") Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, userid) VALUES(#{url}, #{userName}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Insert("UPDATE CREDENTIALS  SET url=#{url}, username=#{userName}, password=#{password}, userid=#{userId} WHERE credentialid = #{credentialId}")
    int update(@Param("credentialId") Integer credentialId,
               @Param("url") String url,
               @Param("userName") String userName,
               @Param("password") String password,
               @Param("userId") String userId);
}
