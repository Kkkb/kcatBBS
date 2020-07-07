package kybmig.ssm.mapper;

import kybmig.ssm.model.TodoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TodoMapper {
    void insertTodo(TodoModel todo);

    List<TodoModel> selectAllTodo();

    TodoModel selectTodo(int id);

    void updateTodo(TodoModel todo);

    void deleteTodo(int id);
}
