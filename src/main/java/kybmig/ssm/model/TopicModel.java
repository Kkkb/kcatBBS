package kybmig.ssm.model;

import java.util.ArrayList;

public class TopicModel extends BaseModel {
    private Integer id;
    private String title;
    private String rawContent;

    public String getRawContent() {
        return rawContent;
    }

    public void setRawContent(String rawContent) {
        this.rawContent = rawContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String content;

    private Integer userId;
    private String type;

    private UserModel user;
    private TopicCommentModel latestComment;

    public TopicCommentModel getLatestComment() {
        return latestComment;
    }

    public void setLatestComment(TopicCommentModel latestComment) {
        this.latestComment = latestComment;
    }

    private Long createdTime;



    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    private Integer clickCount;

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    private Long updatedTime;

    private Integer numberOfComments;

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


    private ArrayList<TopicCommentModel> commentList;


    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<TopicCommentModel> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<TopicCommentModel> commentList) {
        this.commentList = commentList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
