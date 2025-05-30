package tpspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpspring.model.Todo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TodoServiceV2 {
    @Autowired private TodoCrudRepository repository;

//    private final Map<Long, Todo> todos;
//    private long cpt = 0;

    public TodoServiceV2() {
//        this.todos = new HashMap<>();
    }

    public Todo addTodo(final Todo todo) {
        return repository.save(todo);
    }

    public boolean replaceTodo(final Todo newTodo) {
        if (repository.existsById(newTodo.getId())) {
            repository.save(newTodo);
            return true;
        }
        return false;
    }

    public boolean removeTodo(final long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Todo modifyTodo(final Todo partialTodo) {
        Optional<Todo> optionalTodo = repository.findById(partialTodo.getId());
        if (!optionalTodo.isPresent()) {
            return null;
        }
        Todo todoFound = optionalTodo.get();
        if (partialTodo.getTitle() != null) {
            todoFound.setTitle(partialTodo.getTitle());
        }
        if (partialTodo.getDescription() != null) {
            todoFound.setDescription(partialTodo.getDescription());
        }
        if (partialTodo.getCategories() != null) {
            todoFound.setCategories(partialTodo.getCategories());
        }
        return repository.save(todoFound);
    }

    public Todo findTodo(final long id) {
        return repository.findById(id).orElse(null);
    }

}
