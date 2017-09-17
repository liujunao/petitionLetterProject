package com.springmvc.bean;

import java.util.Date;

/**
 * Created by lenovo on 2017/9/10.
 */
public class PetitionLetter {

    private int letterId;
    private String purpose;//信访目的
    private String title;//信件标题
    private String content;//信件内容
    private int isWillPublic;//是否愿意公开， 0 不愿意；1 愿意
    private int letterType;//信件类型 1 市长之窗 2 投诉受理信箱 4 人民建议征集 8 市委领导信箱
    private String addressee;//收信人
    private String attachment;//附件存放路径
    private Date submitTime;//提交时间 默认值：getdate()
    private int outUserId;//信访人用户ID
    private String submitterId;//提交人IP地址

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsWillPublic() {
        return isWillPublic;
    }

    public void setIsWillPublic(int isWillPublic) {
        this.isWillPublic = isWillPublic;
    }

    public int getLetterType() {
        return letterType;
    }

    public void setLetterType(int letterType) {
        this.letterType = letterType;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public int getOutUserId() {
        return outUserId;
    }

    public void setOutUserId(int outUserId) {
        this.outUserId = outUserId;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }
}
