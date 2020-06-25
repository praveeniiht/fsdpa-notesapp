package com.iiht.yaksha.fsdpa.notesapp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;
public interface NoteRepository extends JpaRepository<Note, Long> {
	
}
