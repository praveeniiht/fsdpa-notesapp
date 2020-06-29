package com.iiht.yaksha.fsdpa.notesapp.exceptions;

public class InvalidNoteIdException extends RuntimeException {
	
	public InvalidNoteIdException() {
		super("Invalid NoteId or Noteid doesnt exists");
	}

}
