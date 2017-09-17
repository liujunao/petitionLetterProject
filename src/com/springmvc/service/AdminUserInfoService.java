package com.springmvc.service;

import com.springmvc.bean.AdminUserInfo;
import com.springmvc.dao.AdminUserInfoDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/8.
 */


@Service
public class AdminUserInfoService {

    AdminUserInfoDAO adminUserInfoDAO = new AdminUserInfoDAO();

    public int add(AdminUserInfo adminUserInfo){
        int result = -1;
        result = adminUserInfoDAO.add(adminUserInfo);
        return result;
    }

    public Map<String,Object>query(AdminUserInfo adminUserInfo){
        List<Map<String,Object>>list = null;
        list = adminUserInfoDAO.query(adminUserInfo);
        Map<String,Object>map = list.get(0);

        return map;
    }

    public int delete(AdminUserInfo adminUserInfo){
        int result = -1;
        result = adminUserInfoDAO.delete(adminUserInfo);
        return result;
    }

    public int update(AdminUserInfo adminUserInfo){
        int result = -1;
        result = adminUserInfoDAO.update(adminUserInfo);
        return result;
    }

    public int login(AdminUserInfo adminUserInfo){
        int result = -1;
        result = adminUserInfoDAO.login(adminUserInfo);

        return result;
    }
}
