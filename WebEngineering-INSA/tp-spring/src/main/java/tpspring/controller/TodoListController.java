package tpspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpspring.controller.dto.NamedDTO;
import tpspring.controller.dto.patchTodoDTO;
import tpspring.model.Todo;
import tpspring.model.TodoList;
import tpspring.service.TodoListService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v2/public/todolist")
@CrossOrigin
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping
    public ResponseEntity<TodoList> addTodoList(@RequestBody TodoList newList) {
        return ResponseEntity.ok(todoListService.addTodoList(newList));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTodoList(@PathVariable long id) {
        Optional<TodoList> todo = todoListService.findTodoList(id);
        if (todo == null) {
            return ResponseEntity.badRequest().body("Todo introuvable");
        }
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTodoList(@PathVariable long id) {
        if (todoListService.removeTodoList(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("TodoList introuvable");
    }

    @PutMapping
    public ResponseEntity<?> updateTodoList(@RequestBody TodoList updatedList) {
        if (todoListService.replaceTodoList(updatedList)) {
            return ResponseEntity.ok(updatedList);
        }
        return ResponseEntity.badRequest().body("TodoList introuvable");
    }

    @PostMapping(path = "empty", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoList> createEmptyTodoList(@RequestBody NamedDTO dto) {
        TodoList list = new TodoList();
        list.setName(dto.name());
        list.setDescription(dto.description());
        list.setTodos(List.of()); // Liste vide explicite
        return ResponseEntity.ok(todoListService.addTodoList(list));
    }

    @PostMapping("{listId}/add/{todoId}")
    public ResponseEntity<?> addTodoToTodoList(@PathVariable long listId, @PathVariable long todoId) {
        Optional<TodoList> result = todoListService.addTodoToList(todoId, listId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.badRequest().body("Todo ou TodoList introuvable");
    }

    @PatchMapping(path = "todo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchTodo(@RequestBody patchTodoDTO patch) {
        TodoList updated = todoListService.modifyTodo(patch);
        if (updated == null) {
            return ResponseEntity.badRequest().body("Todo introuvable");
        }
        return ResponseEntity.ok(updated);
    }

}

