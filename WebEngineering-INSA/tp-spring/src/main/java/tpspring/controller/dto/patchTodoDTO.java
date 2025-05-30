package tpspring.controller.dto;

import java.util.List;

public record patchTodoDTO(Long id, String title, String description, List<String> categories) {
}
