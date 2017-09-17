package com.springmvc.controller;

import com.springmvc.service.PetitionLetterService;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/15.
 */
public class PetitionLetterControllerTest {
    PetitionLetterController petitionLetterController = new PetitionLetterController();
    @Test
    public void viewsAllLetter() throws Exception {
        PetitionLetterService petitionLetterService = new PetitionLetterService();
        int count = petitionLetterService.dataCount();
        List<Map<String, Object>> list = null;
        Map<String, Object> map = null;
        int pageSize = 3;
        int pageLevel = count / 3 == 0 ? count / 3 : count / 3 + 1;
        System.out.println(pageLevel);
        list = petitionLetterService.queryPage(1,pageSize);
        System.out.println(list);
        map = list.get(0);
        System.out.println(map);
        System.out.println(map.get("title"));
    }

}