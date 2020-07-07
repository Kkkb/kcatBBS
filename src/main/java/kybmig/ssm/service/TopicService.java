package kybmig.ssm.service;


import kybmig.ssm.mapper.TopicCommentMapper;
import kybmig.ssm.mapper.TopicMapper;
import kybmig.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class TopicService {

    private TopicMapper mapper;
    private TopicCommentMapper topicCommentMapper;

    public TopicService(TopicMapper mapper, TopicCommentMapper topicCommentMapper) {
        this.mapper = mapper;
        this.topicCommentMapper = topicCommentMapper;
    }

    public TopicModel add(HttpServletRequest request, Integer userId) {

        TopicModel m = new TopicModel();
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 转义 script 标签, 避免 xss 攻击
        title = title.replace("script", "");
        content = content.replace("script", "");

        m.setTitle(title);
        m.setContent(content);

        m.setUserId(userId);
        m.setClickCount(0);
        m.setType(request.getParameter("type"));
        m.setRawContent(request.getParameter("rawContent"));

        Long unixTime = System.currentTimeMillis() / 1000L;
        m.setCreatedTime(unixTime);
        m.setUpdatedTime(unixTime);

        mapper.insertTopic(m);

        return m;
    }

    public void update(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        String rawContent = request.getParameter("rawContent");

        TopicModel m = new TopicModel();
        m.setId(id);
        m.setTitle(title);
        m.setContent(content);
        m.setType(type);
        m.setRawContent(rawContent);

        Long unixTime = System.currentTimeMillis() / 1000L;
        m.setUpdatedTime(unixTime);

        mapper.updateTopic(m);
    }

    public void deleteById(Integer id) {
        mapper.deleteTopic(id);
    }

    public TopicModel findById(Integer id) {
        return mapper.selectTopic(id);
    }

    public List<TopicModel> all() {
        return mapper.selectAllTopic();
    }

    public TopicModel findByIdWithComment(Integer id) {
        return mapper.selectOneWithComments(id);
    }

    public List<TopicModel> findTopicByComment(Integer id) {
        return mapper.selectTopicByComment(id);
    }

    public void updateNumberOfClick(Integer id) {
        TopicModel topic = findById(id);

        Integer numberOfClick = topic.getClickCount();
        numberOfClick += 1;
        topic.setClickCount(numberOfClick);

        mapper.updateClickCount(topic);
    }

    public Integer numberOfTopicByUser(int id) {
        return mapper.numberOfTopicByUserId(id);
    }

    public Integer numberOfCommentsByUserId(int id) {
        return topicCommentMapper.numberOfCommentsByUserId(id);
    }

    public List<TopicModel> findTopicByType(String tab) {
        List<TopicModel> topics;
        if (tab == null) {
            topics = this.all();
        } else if (tab.equals("game")) {
            topics = mapper.selectTopicByType("游戏");
        } else if (tab.equals("hot")) {
            topics = mapper.selectTopicByClickTimes();
        } else if (tab.equals("tech")) {
            topics = mapper.selectTopicByType("技术");
        } else if (tab.equals("dm")) {
            topics = mapper.selectTopicByType("动漫");
        } else if (tab.equals("movie")) {
            topics = mapper.selectTopicByType("电影");
        } else if (tab.equals("social")) {
            topics = mapper.selectTopicByType("社会");
        } else {
            topics = this.all();
        }
        return topics;
    }

    public List<TopicModel> topicsOfOnePage(HttpServletRequest request) {
        String tab = request.getParameter("tab");
        List<TopicModel> topics = findTopicByType(tab);
        String pageStr = request.getParameter("page");
        Integer len = topics.size();
        List<TopicModel> topicsOfOnePage = topics;
        if (pageStr == null) {
            if (len > 16) {
                Integer page = 1;
                Integer fromIndex = 15 * (page - 1);
                Integer toIndex = fromIndex + 15;
                topicsOfOnePage = topics.subList(fromIndex, toIndex);
            }
        } else {
            Integer page = Integer.valueOf(pageStr);
            if (len > 16) {
                Integer fromIndex = 15 * (page - 1);
                Integer toIndex = fromIndex + 15;
                if (toIndex < len) {
                    topicsOfOnePage = topics.subList(fromIndex, toIndex);
                } else {
                    topicsOfOnePage = topics.subList(fromIndex, len);
                }
            }
        }
        return topicsOfOnePage;
    }
}
