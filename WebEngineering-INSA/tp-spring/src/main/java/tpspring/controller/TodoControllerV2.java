package tpspring.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpspring.controller.dto.patchTodoDTO;
import tpspring.model.Category;
import tpspring.model.SpecificTodo;
import tpspring.model.Todo;
import tpspring.service.TodoServiceV2;

@RestController
@RequestMapping("api/v2/public/todo")
@CrossOrigin

public class TodoControllerV2 {
    @Autowired private TodoServiceV2 todoService;

//    @GetMapping("todo/{id}")
//    public SpecificTodo getTodoByIds(@PathVariable long id) {
//        return new SpecificTodo(42L, "Dinard");
//    }

    @PostMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo newTodo) {
        Todo created = todoService.addTodo(newTodo);
        return ResponseEntity.ok(created);
    }

    @GetMapping("todo/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable long id) {
        Todo todo = todoService.findTodo(id);
        if (todo == null) {
            return ResponseEntity.badRequest().body("Todo introuvable");
        }
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable long id) {
        if (todoService.removeTodo(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Todo introuvable");
        }
    }

    @PutMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTodo(@RequestBody Todo updatedTodo) {
        if (!todoService.replaceTodo(updatedTodo)) {
            return ResponseEntity.badRequest().body("Todo avec id " + updatedTodo.getId() + " introuvable.");
        }
        return ResponseEntity.ok(updatedTodo);
    }


    @PatchMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchTodo(@RequestBody Todo todo) {
        Todo todoFound = todoService.modifyTodo(todo);
        if (todoFound == null) {
            return ResponseEntity.badRequest().body("Todo introuvable");
        }
        return ResponseEntity.ok(todoFound);
    }

}