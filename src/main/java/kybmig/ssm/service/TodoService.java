package kybmig.ssm.service;


import kybmig.ssm.mapper.TodoMapper;
import kybmig.ssm.model.TodoModel;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TodoService {

    private TodoMapper mapper;

    public TodoService(TodoMapper mapper) {
        this.mapper = mapper;
    }

    public TodoModel add(String content) {
        TodoModel m = new TodoModel();
        m.setContent(content);

        mapper.insertTodo(m);
        return m;
    }

    public void update(Integer id, String content) {
        TodoModel m = new TodoModel();
        m.setId(id);
        m.setContent(content);
        mapper.updateTodo(m);
    }

    public void deleteById(Integer id) {
        mapper.deleteTodo(id);
    }


    public TodoModel findById(Integer id) {
        return mapper.selectTodo(id);
    }



    public List<TodoModel> all() {
        return mapper.selectAllTodo();
    }
}
