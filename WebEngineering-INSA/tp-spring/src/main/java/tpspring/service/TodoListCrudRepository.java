package tpspring.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tpspring.model.TodoList;

@Repository
public interface TodoListCrudRepository extends CrudRepository<TodoList, Long> {
}

