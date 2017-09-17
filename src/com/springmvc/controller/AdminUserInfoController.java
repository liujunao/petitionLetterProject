package com.springmvc.controller;

import com.springmvc.Common.DesUtils;
import com.springmvc.Common.Validation;
import com.springmvc.bean.AdminUserInfo;
import com.springmvc.service.AdminUserInfoService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2017/9/8.
 */
@Controller
@SessionAttributes("sessionValidation")
@RequestMapping("/adminUserInfo")
public class AdminUserInfoController {

    @InitBinder
    public void dateInitBinder(WebDataBinder webDataBinder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat,true));
    }

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request) throws Exception {
        Map<String, String> map = new HashMap<>();

        String username = new String(request.getParameter("userName").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
        String IDNumber = request.getParameter("identityCard");
        String mobile = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        int gender = Integer.parseInt(request.getParameter("gender") != null ? request.getParameter("gender") : "0");
        String address = new String(request.getParameter("address").getBytes("iso-8859-1"),"utf-8");
        String validation = request.getParameter("validation");
        String sessionValidation = (String) request.getSession().getAttribute("sessionValidation");
        System.out.println("***" + sessionValidation);
        if (!validation.equalsIgnoreCase(sessionValidation)){
            return "register";
        }
        if (username == null || username.equals("")){
            return "register";
        }
        if (username.length() < 4 || username.length() > 18){
            map.put("msg", "用户名填写有误！");
            return "register";
        }
        if (!(username.indexOf("") > -1)) {
            map.put("msg", "用户名填写有误！");
            return "register";
        }
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-zA-Z]{6,18}$");
        Matcher matcherPassword = pattern.matcher(password);
        if (!matcherPassword.lookingAt() || password.length() < 6) {
            map.put("msg", "密码格式填写有误");
            return "register";
        }
        if (!password.equals(password2)) {
            map.put("msg", "前后密码填写不一致");
            return "register";
        }
        Pattern patternNameCN = Pattern.compile("[\\u4E00-\\u9FA5]+$");
        Pattern patternNameEN = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcherNameCN = patternNameCN.matcher(name);
        Matcher matcherNameEN = patternNameEN.matcher(name);
        if (matcherNameCN.lookingAt()) {
            if (!(name.indexOf("") > -1)) {
                map.put("msg", "姓名格式填写有误！");
                return "register";
            }
        }
        System.out.println(matcherNameCN.lookingAt());
        System.out.println(matcherNameEN.lookingAt());
        if (!matcherNameEN.lookingAt() && !matcherNameCN.lookingAt()) {
            map.put("msg", "姓名格式填写有误！");
            return "register";
        }

        if (name == null || name.equals("")) {
            map.put("msg", "姓名不能为空！");
            return "register";
        }
        if (IDNumber == "" || IDNumber.length() == 0 || IDNumber.length() != 18){
            map.put("msg","身份证号格式有误！");
            return "register";
        }
        String yyyy = IDNumber.substring(6,10),mm = IDNumber.substring(10,12),dd = IDNumber.substring(12,14);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = yyyy + "-" + mm + "-"+ dd;
        Date date = simpleDateFormat.parse(format);
        String formatTime = simpleDateFormat.format(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (Integer.parseInt(yyyy) != year || Integer.parseInt(mm) != month || Integer.parseInt(dd) != day){
            map.put("msg","身份证出身日期格式填写有误！");
            return "register";
        }
        if (mobile.length() < 7 || mobile.length() > 15) {
            map.put("msg", "手机号码格式填写有误！");
            return "register";
        }
        if (email == "" || email.equals("")) {
            map.put("msg", "电子邮箱不能为空！");
            return "register";
        }
        Pattern patternEmail = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");
        Matcher matcherEmail = patternEmail.matcher(email);
        if (!matcherEmail.lookingAt()) {
            map.put("msg", "电子邮箱格式填写有误！");
            return "register";
        }
        AdminUserInfo adminUserInfo = new AdminUserInfo();

        adminUserInfo.setUserName(username);

        DesUtils desUtils = new DesUtils();
        String encrypt = null;
        encrypt = desUtils.encrypt(password);

        adminUserInfo.setPassword(encrypt);
        adminUserInfo.setName(name);
        adminUserInfo.setMobileNumber(mobile);
        adminUserInfo.setGender(gender);
        adminUserInfo.setEmail(email);
        adminUserInfo.setAddress(address);
        adminUserInfo.setIdentityCard(IDNumber);
        AdminUserInfoService adminUserInfoService = new AdminUserInfoService();
        int result = adminUserInfoService.add(adminUserInfo);
        if (result > 0) {
            map.put("msg", "注册成功！");
            return "login";
        }
        map.put("msg", "注册失败！");
        return "register";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws UnsupportedEncodingException {

        String username = new String(request.getParameter("userName").getBytes("iso-8859-1"),"utf-8");
        String password = request.getParameter("password");
        AdminUserInfoService adminUserInfoService = new AdminUserInfoService();
        AdminUserInfo adminUserInfo = new AdminUserInfo();
        adminUserInfo.setUserName(username);
        adminUserInfo.setPassword(password);
        int result = -1;
        result = adminUserInfoService.login(adminUserInfo);
        System.out.println(result);
        if (result < 0){
            return "login";
        }
        HttpSession session = getSession();
        session.setAttribute("name",username);

        return "addLetter";
    }

    @RequestMapping("/validation")
    public void validate(HttpServletResponse response) throws IOException {
        Validation validation = new Validation();
        BufferedImage bufferedImage = validation.getBufferedImage(100,30);//设置验证图片的大小
        HttpSession session = getSession();
        session.setAttribute("sessionValidation",validation.getText());
        validation.output(bufferedImage,response.getOutputStream());
    }

    public HttpSession getSession(){
        HttpSession session = null;
        session = getRequest().getSession();

        return session;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }
}
