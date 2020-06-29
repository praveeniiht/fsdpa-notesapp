package com.iiht.yaksha.fsdpa.notesapp.exceptions;

public class InvalidNoteStatusException extends RuntimeException {
	public InvalidNoteStatusException() {
		super("No such status exists");
	}

}
