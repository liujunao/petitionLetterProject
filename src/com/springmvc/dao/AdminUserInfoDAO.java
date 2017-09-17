package com.springmvc.dao;

import com.springmvc.bean.AdminUserInfo;
import com.springmvc.db.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/8.
 */
@Repository
public class AdminUserInfoDAO {

    JdbcUtils jdbcUtils = new JdbcUtils();

    public List<Map<String,Object>>query(AdminUserInfo adminUserInfo){
        List<Map<String,Object>>list = null;
        String sql = "SELECT * FROM adminuserinfo WHERE adminUserID = ?";
        Object[] objects = new Object[1];
        objects[0] = adminUserInfo.getAdminUserID();
        list = jdbcUtils.query(sql,objects);
        return list;
    }

    public int add(AdminUserInfo adminUserInfo){
        int result = -1;
        String sql = "INSERT INTO adminuserinfo (userName,password,name,mobileNumber,gender,email,address,identityCard) VALUES(?,?,?,?,?,?,?,?)";
        Object[] objects = new Object[8];
        objects[0] = adminUserInfo.getUserName();
        objects[1] = adminUserInfo.getPassword();
        objects[2] = adminUserInfo.getName();
        objects[3] = adminUserInfo.getMobileNumber();
        objects[4] = adminUserInfo.getGender();
        objects[5] = adminUserInfo.getEmail();
        objects[6] = adminUserInfo.getAddress();
        objects[7] = adminUserInfo.getIdentityCard();
        result = jdbcUtils.update(sql,objects);
        return result;
    }

    public int delete(AdminUserInfo adminUserInfo){
        int result = -1;
        String sql = "DELETE FROM adminuserinfo WHERE adminUserID = ?";
        Object[] objects = new Object[1];
        objects[0] = adminUserInfo.getAdminUserID();
        result = jdbcUtils.update(sql,objects);
        return result;
    }

    public int update(AdminUserInfo adminUserInfo){
        int result = -1;
        String sql = "UPDATE adminuserinfo SET userName=?,password=?,name=?,mobileNumber=?,gender=?,email=?,address=?,identity=? " +
                "WHERE adminUserID=" + adminUserInfo.getAdminUserID();
        Object[] objects = new Object[8];
        objects[0] = adminUserInfo.getUserName();
        objects[1] = adminUserInfo.getPassword();
        objects[2] = adminUserInfo.getName();
        objects[3] = adminUserInfo.getMobileNumber();
        objects[4] = adminUserInfo.getGender();
        objects[5] = adminUserInfo.getEmail();
        objects[6] = adminUserInfo.getAddress();
        objects[7] = adminUserInfo.getIdentityCard();
        result = jdbcUtils.update(sql,objects);
        return result;
    }

    public int login(AdminUserInfo adminUserInfo){

        int result = -1;
        String sql = "SELECT * FROM adminuserinfo WHERE userName = ? AND password = ?";
        Object[] objects = new Object[2];
        objects[0] = adminUserInfo.getUserName();
        objects[1] = adminUserInfo.getPassword();
        List<Map<String,Object>> list = jdbcUtils.query(sql,objects);
        result = list.size();

        return result;
    }
}
