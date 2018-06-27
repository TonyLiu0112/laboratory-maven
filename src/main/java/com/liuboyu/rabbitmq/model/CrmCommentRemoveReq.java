package com.liuboyu.rabbitmq.model;

/**
 * 评论删除
 *
 * @author Tony
 */
public class CrmCommentRemoveReq {

    /**
     * 评论ID
     */
    private String comment_id;

    /**
     * 投顾ID
     */
    private Long advisor_id;

    /**
     * 操作时间
     * eg: 2017-12-05 18:50:11
     */
    private String operate_time;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public Long getAdvisor_id() {
        return advisor_id;
    }

    public void setAdvisor_id(Long advisor_id) {
        this.advisor_id = advisor_id;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }
}
