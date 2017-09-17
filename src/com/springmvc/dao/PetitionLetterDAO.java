package com.springmvc.dao;

import com.springmvc.bean.PetitionLetter;
import com.springmvc.db.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/9/10.
 */
public class PetitionLetterDAO {

    JdbcUtils jdbcUtils = new JdbcUtils();

    public int add(PetitionLetter petitionLetter){
        int result = -1;
        String sql = "INSERT INTO petitionletter (purpose,title,content,isWillPublic,letterType,addressee,attachment," +
                "submitTime) VALUES (?,?,?,?,?,?,?,?)";
        Object[] objects = new Object[8];
        objects[0] = petitionLetter.getPurpose();
        objects[1] = petitionLetter.getTitle();
        objects[2] = petitionLetter.getContent();
        objects[3] = petitionLetter.getIsWillPublic();
        objects[4] = petitionLetter.getLetterType();
        objects[5] = petitionLetter.getAddressee();
        objects[6] = petitionLetter.getAttachment();
        objects[7] = petitionLetter.getSubmitTime();

        result = jdbcUtils.update(sql,objects);

        return result;
    }

    public List<Map<String,Object>> queryPage(int pageIndex, int pageSize){
        String sql = "SELECT * FROM petitionletter";
        String orderBy = "letterId";
        List<Map<String,Object>>list = jdbcUtils.queryPage(sql,orderBy,pageIndex,pageSize);

        return list;
    }
    public int dataCount(){
        String sql = "SELECT * FROM petitionletter";
        int count = jdbcUtils.dataCount(sql);

        return count;
    }

    public List<Map<String,Object>>query(PetitionLetter petitionLetter){
        List<Map<String,Object>>list = null;
        String sql = "SELECT * FROM petitionletter WHERE letterId=?";
        Object[] objects = new Object[1];
        objects[0] = petitionLetter.getLetterId();
        list = jdbcUtils.query(sql,objects);

        return list;
    }

    public int delete(PetitionLetter petitionLetter){
        String sql = "DELETE FROM petitionletter WHERE letterId = ?";
        int result = -1;
        Object[] objects = new Object[1];
        objects[0] = petitionLetter.getLetterId();
        result = jdbcUtils.update(sql,objects);

        return result;
    }
}
