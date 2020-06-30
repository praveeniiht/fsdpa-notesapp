package com.iiht.yaksha.fsdpa.notesapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="notes")
public class Note {
	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", status=" + status + "]";
	}

	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@Size(min=3,max=20)
	private String title;
	@Column
	@Size(min=3,max=20)
	private String author;
	@Column
	private String description;
	@Column
	private String status;
	
	public Note() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
