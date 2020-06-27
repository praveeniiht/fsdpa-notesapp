package com.iiht.yaksha.fsdpa.notesapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.iiht.yaksha.fsdpa.notesapp.fsdpanotesapp.FsdpaNotesappApplication;
import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.repo.NoteRepository;
import com.iiht.yaksha.fsdpa.notesapp.testutils.JsonUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FsdpaNotesappApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
class NotesControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired 
	private NoteRepository noteRepository;

	@BeforeEach
	void setUp() throws Exception {
		noteRepository.deleteAll();
	}

	
	@Test
	public void whenValidInput_thenCreateNote() throws IOException, Exception {
	        Note note1 = new Note();
	        note1.setAuthor("Praveen");
	        mockMvc.perform(post("/addnote").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(note1)));

	        List<Note> found = noteRepository.findAll();
	        assertThat(found).extracting(Note::getAuthor).containsOnly("Praveen");
	    }
		
	@Test
    public void givenNotes_whenGetNotes_thenStatus200() throws Exception {
		 Note note1 = new Note();
	        note1.setAuthor("Praveen");
	        Note note2 = new Note();
	        note2.setAuthor("Krishna");
	        noteRepository.saveAndFlush(note1);
	        noteRepository.saveAndFlush(note2);
          mockMvc.perform(get("/allnotes").contentType(MediaType.APPLICATION_JSON))
         // .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))))
          .andExpect(jsonPath("$[0].author", is("Praveen")))
          .andExpect(jsonPath("$[1].author", is("Krishna")));
    }
	
}
