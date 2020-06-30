package com.iiht.yaksha.fsdpa.notesapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.repo.NoteRepository;
import com.iiht.yaksha.fsdpa.notesapp.testutils.*;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.*;

@ExtendWith(SpringExtension.class)
public class NoteServiceTest {
	
	@TestConfiguration
    static class NoteServiceImplTestContextConfiguration {
        @Bean
        public NoteService noteService() {
            return new NoteServiceImpl();
        }
    }
	
	@Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    
    @BeforeEach
    public void setUp() {
        Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
        Note note2 = JsonUtils.createNote((long) 10010, "Kumar", "Docker", "On Going", "Orchestration Tool");
        Note note3 = JsonUtils.createNote((long) 10011, "Krishna", "Jenkins", "Done", "Continious Integration Tool");
     
        List<Note> allNotes = Arrays.asList(note1,note2,note3);
        List<Note> doneNotes = Arrays.asList(note1,note3);

        Mockito.when(noteService.addNote(note1)).thenReturn(note1);
        Mockito.when(noteService.getAllNotesByStatus("Done")).thenReturn(doneNotes);
        Mockito.when(noteService.getNoteById((long) 10009)).thenReturn(Optional.of(note1));
        Mockito.when(noteService.getAllNotes()).thenReturn(allNotes);
     }
    
	@Test
	void testGetAllNotes() throws IOException {
		List<Note> fromDb = noteService.getAllNotes();
		assertEquals(fromDb.size(),3);
		int count= fromDb.size();
		yakshaAssert(currentTest(),(count==3?true:false),businessTestFile);
	}

	@Test
	void testGetNoteById() throws IOException {
		Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
	    Mockito.when(noteService.getNoteById((long) 10009)).thenReturn(Optional.of(note1));
		Optional<Note> note2 = noteService.getNoteById((long) 10009);
		yakshaAssert(currentTest(),(note2!=null?true:false),businessTestFile);
	}
	
	@Test
	void testAddNote() throws IOException {
		
		Note note = JsonUtils.createNote((long) 10010, "Kumar", "Docker", "On Going", "Orchestration Tool");
		Mockito.when(noteService.addNote(note)).thenReturn(note);
		Note note1 = noteService.addNote(note);
		yakshaAssert(currentTest(),(note1.getId().equals(note.getId())?true:false),businessTestFile);
	}

	@Test
	void testGetAllNotesByStatus() throws IOException {
		 Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
		 Note note3 = JsonUtils.createNote((long) 10011, "Krishna", "Jenkins", "Done", "Continious Integration Tool");
		 List<Note> doneNotes = Arrays.asList(note1,note3);
		 Mockito.when(noteService.getAllNotesByStatus("Done")).thenReturn(doneNotes);
	     List<Note> list1 = noteService.getAllNotesByStatus("Done");
	   //  assertThat(list1.containsAll(doneNotes));
	     yakshaAssert(currentTest(),(doneNotes.containsAll(list1)?true:false),businessTestFile);
	}
}

