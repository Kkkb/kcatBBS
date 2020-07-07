package kybmig.ssm.model;

import java.util.List;

public class TopicCommentModel extends BaseModel {
    private Integer id;
    private String content;
    private Integer userId;
    private Integer topicId;

    private UserModel user;

    public TopicCommentModel getLatestComment() {
        return latestComment;
    }

    public void setLatestComment(TopicCommentModel latestComment) {
        this.latestComment = latestComment;
    }

    private TopicModel topic;

    private List<TopicModel> topicList;

    private TopicCommentModel latestComment;

    public List<TopicModel> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicModel> topicList) {
        this.topicList = topicList;
    }

    public TopicModel getTopic() {
        return topic;
    }

    public void setTopic(TopicModel topic) {
        this.topic = topic;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    private Long createdTime;
    private Long updatedTime;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }
}
