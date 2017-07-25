package com.feng.dao;

import com.feng.model.LoginToken;
import org.apache.ibatis.annotations.Param;


public interface LoginTokenDAO {

    void addLoginToken(LoginToken token);
    LoginToken selectLoginTokenByToken(String token);
    void updateStatus(@Param("token") String token, @Param("status") int status);
}
