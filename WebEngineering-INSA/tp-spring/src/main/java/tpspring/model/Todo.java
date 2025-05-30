package tpspring.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = SpecificTodo.class, name = "specific")
})

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	protected String title;
	protected String description;

	@ElementCollection
	protected List<Category> categories;

	@ManyToOne
	@JoinColumn(name = "todoList_id")
	@JsonIgnore
	protected TodoList list;

	protected String owner;

	/**
	 * Temporary constructor for TP1
	 */
	public Todo(long id, String title) {
		this.id = id;
		this.title = title;
		description = "";
		categories = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title
				+ ", description=" + description + ", categories=" + categories + "]";
	}
}
