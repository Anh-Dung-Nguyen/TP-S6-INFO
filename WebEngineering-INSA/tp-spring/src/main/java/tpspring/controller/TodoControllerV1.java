package tpspring.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpspring.model.Todo;
import tpspring.service.TodoServiceV1;

@RestController
@RequestMapping("api/v1/public/todo")
@CrossOrigin
public class TodoControllerV1 {
    private final Map<Long, Todo> todos;
    private long cpt = 0;

    public TodoControllerV1() {
        this.todos = new HashMap<>();
    }

//    @GetMapping(path = "todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Todo getTodoById(@PathVariable String id) {
//        if (!todos.containsKey(Long.parseLong(id))) {
//            throw new IllegalArgumentException("Todo not found");
//        }
//        return todos.get(Long.parseLong(id));
//    }

    @GetMapping("todo/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        Todo todo = todos.get(id);
        if (todo == null) {
            return ResponseEntity.badRequest().body("Todo avec id " + id + " introuvable.");
        }
        return ResponseEntity.ok(todo);
    }


    @PostMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo addTodo(@RequestBody Todo newTodo) {
        cpt++;
        newTodo.setId(cpt);
        todos.put(cpt, newTodo);
        System.out.println("Liste actuelle des todos :");
        todos.values().forEach(System.out::println);
        return newTodo;
    }

    @DeleteMapping(path = "todo/{id}")
    public void removeTodo(@PathVariable("id") Long id) {
        todos.remove(id);
    }

    @PutMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTodo(@RequestBody Todo updatedTodo) {
        if (!todos.containsKey(updatedTodo.getId())) {
            return ResponseEntity.badRequest().body("Todo avec id " + updatedTodo.getId() + " introuvable.");
        }
        todos.put(updatedTodo.getId(), updatedTodo);
        System.out.println("Todo mis à jour :");
        todos.values().forEach(System.out::println);
        return ResponseEntity.ok(updatedTodo);
    }

    @PatchMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchTodo(@RequestBody Todo todo) {
        Todo todoFound = todos.get(todo.getId());
        if (todoFound == null) {
            return ResponseEntity.badRequest().body("Todo introuvable");
        }
        if (todo.getTitle() != null) {
            todoFound.setTitle(todo.getTitle());
        }
        if (todo.getDescription() != null) {
            todoFound.setDescription(todo.getDescription());
        }
        if (todo.getCategories() != null) {
            todoFound.setCategories(todo.getCategories());
        }
        return ResponseEntity.ok(todoFound);
    }

}