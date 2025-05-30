package tpspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpspring.controller.dto.patchTodoDTO;
import tpspring.model.Category;
import tpspring.model.Todo;
import tpspring.model.TodoList;
import tpspring.service.TodoListCrudRepository;

import java.util.Optional;

@Service
public class TodoListService {
    @Autowired
    private TodoListCrudRepository repository;
    @Autowired private TodoListCrudRepository todoListCrudRepository;
    @Autowired private TodoCrudRepository todoRepository;

    // Ajoute un Todo existant dans une TodoList existante
    public Optional<TodoList> addTodoToList(long todoId, long listId) {
        Optional<Todo> optTodo = todoRepository.findById(todoId);
        Optional<TodoList> optList = todoListCrudRepository.findById(listId);

        if (optTodo.isPresent() && optList.isPresent()) {
            Todo todo = optTodo.get();
            TodoList list = optList.get();

            todo.setList(list); // mettre à jour la relation du côté Todo
            list.getTodos().add(todo); // ajouter dans la liste
            todoRepository.save(todo); // persister la relation

            return Optional.of(todoListCrudRepository.save(list));
        }

        return Optional.empty();
    }

    public TodoList addTodoList(TodoList list) {
        return repository.save(list);
    }

    public Optional<TodoList> findTodoList(long id) {
        return repository.findById(id);
    }

    public boolean removeTodoList(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean replaceTodoList(TodoList updatedList) {
        if (repository.existsById(updatedList.getId())) {
            repository.save(updatedList);
            return true;
        }
        return false;
    }

    public TodoList modifyTodo(patchTodoDTO patch) {
        Optional<TodoList> optionalTodoList = repository.findById(patch.id());
        if (optionalTodoList.isEmpty()) {
            return null;
        }

        TodoList todoList = optionalTodoList.get();

        if (patch.title() != null) {
            todoList.setTitle(patch.title());
        }
        if (patch.description() != null) {
            todoList.setDescription(patch.description());
        }
        if (patch.categories() != null) {
            todoList.setCategories(patch.categories());// attention aux erreurs si string invalide
        }

        return repository.save(todoList);
    }

}

