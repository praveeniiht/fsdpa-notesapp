package com.iiht.yaksha.fsdpa.notesapp.service;

import java.util.List;
import java.util.Optional;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;

public interface NoteService {
	public List<Note> getAllNotes();
	public Optional<Note> getNoteById(Long id);
	public Note addNote(Note note);
	public void deleteNote(Note note);
	public List<Note> getAllNotesByStatus(String status);
}
