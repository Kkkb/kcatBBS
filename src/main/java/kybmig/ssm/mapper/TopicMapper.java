package kybmig.ssm.mapper;

import kybmig.ssm.model.TopicModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicMapper {
    void insertTopic(TopicModel topic);

    List<TopicModel> selectAllTopic();

    TopicModel selectTopic(int id);

    void updateTopic(TopicModel topic);

    void deleteTopic(int id);

    TopicModel selectOneWithComments(int id);

    List<TopicModel> selectTopicByUserId(int id);

    List<TopicModel> selectTopicByComment(int id);

    void updateClickCount(TopicModel topic);

    Integer numberOfTopicByUserId(int id);

    List<TopicModel> selectTopicByType(String type);

    List<TopicModel> selectTopicByClickTimes();
}
