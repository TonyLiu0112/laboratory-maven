package com.liuboyu.rabbitmq.model;

/**
 * 评论设置
 * <p>
 * 公开、私密 互转
 *
 * @author Tony
 */
public class CrmCommentSetReq {

    /**
     * 评论ID
     */
    private String comment_id;

    /**
     * 投顾ID
     */
    private Long advisor_id;

    /**
     * 操作类型
     * <p>
     * <li>1. 公开转私密</li>
     * <li>2. 私密转公开</li>
     */
    private Integer type;

    /**
     * 操作时间
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }
}
