package kybmig.ssm.mapper;

import kybmig.ssm.model.TopicCommentModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicCommentMapper {
    void insertTopicComment(TopicCommentModel topicComment);

    List<TopicCommentModel> selectAllTopicComment();

    TopicCommentModel selectTopicComment(int id);

    void updateComment(TopicCommentModel topicComment);

    void deleteTopicComment(int id);

    List<TopicCommentModel> selectAllTopicCommentByTopicId(int topicId);

    TopicCommentModel selectCommentByUserId(int userId);

    Integer numberOfCommentsByTopicId(int topicId);

    TopicCommentModel selectLatestCommentByTopicId(Integer id);

    Integer numberOfCommentsByUserId(int topicId);

}
