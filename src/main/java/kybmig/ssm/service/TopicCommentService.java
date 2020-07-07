package kybmig.ssm.service;


import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TopicCommentMapper;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.model.TopicCommentModel;
import kybmig.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCommentService {

    private TopicMapper mapper;
    private TopicCommentMapper topicCommentMapper;

    public TopicCommentService(TopicMapper mapper, TopicCommentMapper topicCommentMapper) {
        this.mapper = mapper;
        this.topicCommentMapper = topicCommentMapper;
    }

    public List<TopicCommentModel> findAllComment(Integer topicId) {
        return topicCommentMapper.selectAllTopicCommentByTopicId(topicId);
    }

    public void add(String content, Integer topicId, Integer userId) {
        TopicCommentModel comment = new TopicCommentModel();
        comment.setTopicId(topicId);
        comment.setContent(content);
        comment.setUserId(userId);

        Long unixTime = System.currentTimeMillis() / 1000L;

        comment.setCreatedTime(unixTime);
        comment.setUpdatedTime(unixTime);

        topicCommentMapper.insertTopicComment(comment);
    }

    public void update(Integer id, String content) {
        TopicCommentModel comment = new TopicCommentModel();
        comment.setId(id);
        comment.setContent(content);

        Long unixTime = System.currentTimeMillis() / 1000L;
        comment.setUpdatedTime(unixTime);

        topicCommentMapper.updateComment(comment);
    }

    public void delete(Integer id) {
        topicCommentMapper.deleteTopicComment(id);
    }

    public TopicCommentModel findCommentById(Integer id) {
        return topicCommentMapper.selectTopicComment(id);
    }

    public TopicCommentModel findCommentsById(Integer userId) {
        return topicCommentMapper.selectCommentByUserId(userId);
    }

}
