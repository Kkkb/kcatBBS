package kybmig.ssm.controller;
import kybmig.ssm.Utility;
import kybmig.ssm.mapper.TodoMapper;
import kybmig.ssm.model.TodoModel;
import kybmig.ssm.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping("/todo/add")
    public String add(String content) {
        TodoModel todo = todoService.add(content);

        return "redirect:/todo";
    }

    @GetMapping("/todo/delete")
    public String deleteMapper(Integer id) {
        todoService.deleteById(id);
        return "redirect:/todo";
    }


    @GetMapping("/todo/edit")
    public ModelAndView edit(Integer id) {
        TodoModel todo = todoService.findById(id);

        ModelAndView m = new ModelAndView("todo/todo_edit");
        m.addObject("todo", todo);
        return m;
    }

    @PostMapping("/todo/update")
    public String updateMapper(int id, String content) {
        todoService.update(id, content);
        return "redirect:/todo";
    }

    @GetMapping("/todo")
    public ModelAndView index() {
        List<TodoModel> todos = todoService.all();
        ModelAndView m = new ModelAndView("todo/todo_index");
        m.addObject("todos", todos);
        return m;
    }
}
