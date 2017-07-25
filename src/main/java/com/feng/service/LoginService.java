package com.feng.service;

import com.feng.dao.LoginTokenDAO;
import com.feng.dao.UserDAO;
import com.feng.model.LoginToken;
import com.feng.model.User;
import com.feng.util.RegisterUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LoginTokenDAO loginTokenDAO;

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDAO.selectUserByName(username);
        if (user != null) {
            map.put("msg", "用户名已经被注册");
            return map;
        }

        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setPassword(RegisterUtil.MD5(password+user.getSalt()));
        userDAO.addUser(user);

        return map;

    }

    public Map<String,Object> login(String username,String password){
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isBlank(username)){
            map.put("msg","用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userDAO.selectUserByName(username);
        if (user == null) {
            map.put("msg", "用户名不存在");
            return map;
        }
        if (!RegisterUtil.MD5(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "密码不正确");
            return map;
        }

        //登录成功 添加一个token
        String token = addLoginToken(user.getId());
        map.put("token", token);
        return map;
    }
    private String addLoginToken(Integer userId) {
        LoginToken token = new LoginToken();
        token.setUserId(userId);
        Date date = new Date();
        //过期时间 date.getTime()返回的是一个long型的毫秒数
        date.setTime(date.getTime() + 1000*3600*24);
        token.setExpired(date);
        //0代表登录状态
        token.setStatus(0);
        token.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTokenDAO.addLoginToken(token);
        //调用方法的目的 返回一个token
        return token.getToken();
    }

    public void logout(String token,int status) {
        loginTokenDAO.updateStatus(token, status);
    }

    public boolean ifOnline(String token){
        LoginToken tk = loginTokenDAO.selectLoginTokenByToken(token);
       if(tk!=null && tk.getStatus()==0 && tk.getExpired().after(new Date())) {
           return true;
       }else {
           return false;
       }
    }

}
