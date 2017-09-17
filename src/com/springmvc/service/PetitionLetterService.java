package com.springmvc.service;

import com.springmvc.bean.PetitionLetter;
import com.springmvc.dao.PetitionLetterDAO;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/10.
 */
public class PetitionLetterService {

    PetitionLetterDAO petitionLetterDAO = new PetitionLetterDAO();

    public int add(PetitionLetter petitionLetter){
        int result = -1;
        result = petitionLetterDAO.add(petitionLetter);

        return result;
    }

    public List<Map<String,Object>> queryPage(int pageIndex,int pageSize){
        List<Map<String,Object>> list = null;
        list = petitionLetterDAO.queryPage(pageIndex,pageSize);

        return list;
    }
    public int dataCount(){
        int count = 0;
        count = petitionLetterDAO.dataCount();

        return count;
    }

    public Map<String,Object>query(PetitionLetter petitionLetter){
        Map<String,Object> map = null;
        List<Map<String,Object>>list = petitionLetterDAO.query(petitionLetter);
        map = list.get(0);
        return map;
    }

    public int delete(PetitionLetter petitionLetter){
        int result = -1;
        result = petitionLetterDAO.delete(petitionLetter);

        return result;
    }
}
