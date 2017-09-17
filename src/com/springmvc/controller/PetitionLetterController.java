package com.springmvc.controller;

import com.springmvc.bean.PetitionLetter;
import com.springmvc.service.PetitionLetterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/11.
 */
@Controller
@RequestMapping("/petitionLetter")
public class PetitionLetterController {

    @RequestMapping("/addLetter")
    public String addLetter(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {

        String purpose = request.getParameter("purpose");
        String title = request.getParameter("title");
        int isWillPublic = Integer.parseInt(request.getParameter("isWillPublic") != null ? request.getParameter("isWillPublic") : "0");
        int letterType = Integer.parseInt(request.getParameter("letterType") != null ? request.getParameter("letterType") : "0");
        String content = request.getParameter("content");
        String addressee = request.getParameter("addressee");
        String submitTime = request.getParameter("submitTime");

        Date date = new Date(submitTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(date);
        java.sql.Date submitDate = java.sql.Date.valueOf(dateString);
        System.out.println(submitDate);

        PetitionLetter petitionLetter = new PetitionLetter();
        PetitionLetterService petitionLetterService = new PetitionLetterService();

        petitionLetter.setPurpose(purpose);
        petitionLetter.setTitle(title);
        petitionLetter.setContent(content);
        petitionLetter.setIsWillPublic(isWillPublic);
        petitionLetter.setLetterType(letterType);
        petitionLetter.setAddressee(addressee);
        petitionLetter.setAttachment("daiding");
        petitionLetter.setSubmitTime(submitDate);

        int result = -1;
        result = petitionLetterService.add(petitionLetter);

        if (result > -1) {
            return "success";
        }

        return "login";
    }

    @RequestMapping("/page")
    public String viewsAllLetter(HttpServletRequest request, Model model) {

        PetitionLetterService petitionLetterService = new PetitionLetterService();
        int count = petitionLetterService.dataCount();
        int totalPage = count / 2 == 0 ? count / 2 : count / 2 + 1;
        request.setAttribute("totalPage",totalPage);
        System.out.println(totalPage);
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber") != null ? request.getParameter("pageNumber") : "1");
        request.setAttribute("pageNumber",pageNumber);
        System.out.println(pageNumber);


        return "viewsAllLetter";
    }

    @RequestMapping("/reviseLetter")
    public String reviseLetter(HttpServletRequest request){
        int letterId = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "1");
        PetitionLetter petitionLetter = new PetitionLetter();
        PetitionLetterService petitionLetterService = new PetitionLetterService();
        petitionLetter.setLetterId(letterId);
        Map<String,Object>map = petitionLetterService.query(petitionLetter);
        request.setAttribute("purpose",map.get("purpose"));
        request.setAttribute("title",map.get("title"));
        request.setAttribute("content",map.get("content"));
        request.setAttribute("addressee",map.get("addressee"));
        request.setAttribute("submitTime",map.get("submitTime"));
        int letterType = Integer.parseInt(map.get("letterType") != null ? String.valueOf(map.get("letterType")) : "1");
        if (letterType == 1){
            request.setAttribute("letterType","市长之窗");
        }else if (letterType == 2){
            request.setAttribute("letterType","投诉受理信箱");
        }else if (letterType == 4){
            request.setAttribute("letterType","人民建议征集");
        }else if (letterType == 8){
            request.setAttribute("letterType","市委领导信箱");
        }
//        int isWillPublic = Integer.parseInt(map.get("isWillPublic") != null ? String.valueOf(map.get("isWillPublic")) : "0");
//        if (isWillPublic == 0){
//            request.setAttribute("isWillPublic","不愿意");
//        }else if (isWillPublic == 1){
//            request.setAttribute("isWillPublic","愿意");
//        }
        request.setAttribute("isWillPublic",map.get("isWillPublic"));

        String name = request.getParameter("name");
        int result = -1;
        if (name.equalsIgnoreCase("delete")){
            petitionLetter.setLetterId(letterId);
            result = petitionLetterService.delete(petitionLetter);
            if (result > 0){
                return "viewsAllLetter";
            }else {
                return "success";
            }
        }else if (name.equalsIgnoreCase("revise")){
            return "addLetter";
        }
        request.setAttribute("time","time");
        return "viewsLetter";
    }
}
