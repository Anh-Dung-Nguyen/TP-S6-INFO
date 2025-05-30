package tpspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TodoList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private String title;
	private String categories;
	@OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Todo> todos;
	private String owner;

	public TodoList(final String name) {
		super();
		this.name = name;
		todos = new ArrayList<>();
	}
}
