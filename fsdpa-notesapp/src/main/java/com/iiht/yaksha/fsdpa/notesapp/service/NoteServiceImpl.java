package com.iiht.yaksha.fsdpa.notesapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.repo.NoteRepository;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteRepository noteRepository;
	@Override
	public List<Note> getAllNotes() {
		// TODO Auto-generated method stub
		return noteRepository.findAll();
	}

	@Override
	public Optional<Note> getNoteById(Long id) {
		// TODO Auto-generated method stub
		return noteRepository.findById(id);
	}

	@Override
	public Note addNote(Note note) {
		// TODO Auto-generated method stub
		return noteRepository.save(note);
	}

	@Override
	public void deleteNote(Note note) {
		// TODO Auto-generated method stub
		noteRepository.delete(note);
		
	}

	@Override
	public List<Note> getAllNotesByStatus(String status) {
		// TODO Auto-generated method stub
		return noteRepository.getAllNotesByStatus(status);
	}
	
	

}
