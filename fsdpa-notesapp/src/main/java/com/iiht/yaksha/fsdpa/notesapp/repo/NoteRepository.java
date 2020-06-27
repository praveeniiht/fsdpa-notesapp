package com.iiht.yaksha.fsdpa.notesapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;
public interface NoteRepository extends JpaRepository<Note, Long> {
	
	@Query("select n from Note n where n.status = :status")
	  List<Note> getAllNotesByStatus(String status);

}
