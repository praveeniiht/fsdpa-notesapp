package com.iiht.yaksha.fsdpa.notesapp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.service.NoteService;
@RestController
public class NotesController {
	
	@Autowired
	private NoteService noteService;
	
	@RequestMapping("/")
    public String home() {
		
       return "Welcome to NotesApp- FSD Pre Assessment !!";
    }
	
	@GetMapping("/allnotes")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }
	
	@GetMapping("/{Id}")
    public Optional<Note> getNoteById(@PathVariable("Id") Long id) {
        return noteService.getNoteById(id);
    }

	@PostMapping("/addnote")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        System.out.println("Insidethe post controller");
    	HttpStatus status = HttpStatus.CREATED;
    	System.out.println(note.getDescription());
        Note saved = noteService.addNote(note);
        return new ResponseEntity<>(saved, status);
    }
	@DeleteMapping("/deletenote")
	public void deleteNote(@RequestBody Note note) {
		noteService.deleteNote(note);
		
	}

}
