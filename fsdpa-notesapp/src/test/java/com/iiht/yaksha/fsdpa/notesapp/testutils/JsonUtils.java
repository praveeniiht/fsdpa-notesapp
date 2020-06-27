package com.iiht.yaksha.fsdpa.notesapp.testutils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.yaksha.fsdpa.notesapp.model.Note;

import java.io.IOException;

public class JsonUtils {
    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }
    public static Note createNote(Long id, String author, String title, String status, 
    		String description) {
    	Note note = new Note();
    	note.setId(id);
    	note.setAuthor(author);
    	note.setDescription(description);
    	note.setStatus(status);
    	note.setTitle(title);
  	 	return note;
    }
}
