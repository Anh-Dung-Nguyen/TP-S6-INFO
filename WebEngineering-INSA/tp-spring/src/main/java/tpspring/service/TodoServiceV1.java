package tpspring.service;

import tpspring.model.Todo;

import java.util.HashMap;
import java.util.Map;

public class TodoServiceV1 {
    private final Map<Long, Todo> todos;
    private long cpt = 0;

    public TodoServiceV1() {
        this.todos = new HashMap<>();
    }

    public Todo addTodo(final Todo todo) {
        todo.setId(++cpt);
        todos.put(todo.getId(), todo);
        return todo;
    }

    public boolean replaceTodo(final Todo newTodo) {
        if (todos.containsKey(newTodo.getId())) {
            todos.put(newTodo.getId(), newTodo);
            return true;
        }
        return false;
    }

    public boolean removeTodo(final long id) {
        return todos.remove(id) != null;
    }

    public Todo modifyTodo(final Todo partialTodo) {
        Todo existingTodo = todos.get(partialTodo.getId());
        if (existingTodo == null) {
            return null;
        }
        if (partialTodo.getTitle() != null) {
            existingTodo.setTitle(partialTodo.getTitle());
        }
        if (partialTodo.getDescription() != null) {
            existingTodo.setDescription(partialTodo.getDescription());
        }
        if (partialTodo.getCategories() != null) {
            existingTodo.setCategories(partialTodo.getCategories());
        }
        return existingTodo;
    }

    public Todo findTodo(final long id) {
        return todos.get(id);
    }
}