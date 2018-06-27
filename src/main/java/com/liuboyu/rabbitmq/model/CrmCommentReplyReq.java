package com.liuboyu.rabbitmq.model;

/**
 * 评论回复
 *
 * @author Tony
 */
public class CrmCommentReplyReq {

    /**
     * 评论ID, CRM系统生成
     */
    private Long comment_id;

    /**
     * 投顾ID
     */
    private Long advisor_id;

    /**
     * 原评论ID, 在线投顾系统生成
     */
    private String former_comment_id;

    /**
     * 评论内容，仅限文本
     */
    private String content;

    /**
     * 直播间ID
     */
    private Long live_id;

    /**
     * 观点ID
     */
    private Long viewpoint_id;

    /**
     * 操作时间
     * eg: 2017-12-05 18:50:11
     */
    private String operate_time;

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Long getAdvisor_id() {
        return advisor_id;
    }

    public void setAdvisor_id(Long advisor_id) {
        this.advisor_id = advisor_id;
    }

    public String getFormer_comment_id() {
        return former_comment_id;
    }

    public void setFormer_comment_id(String former_comment_id) {
        this.former_comment_id = former_comment_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLive_id() {
        return live_id;
    }

    public void setLive_id(Long live_id) {
        this.live_id = live_id;
    }

    public Long getViewpoint_id() {
        return viewpoint_id;
    }

    public void setViewpoint_id(Long viewpoint_id) {
        this.viewpoint_id = viewpoint_id;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }
}
