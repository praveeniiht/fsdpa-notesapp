package com.iiht.yaksha.fsdpa.notesapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

@ExtendWith(SpringExtension.class)
class NoteServiceTest {
	
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
	void testGetAllNotes() {
		List<Note> fromDb = noteService.getAllNotes();
		assertEquals(fromDb.size(),3);
	}

	@Test
	void testGetNoteById() {
	//	fail("Not yet implemented");
	//	Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
		Optional<Note> note2 = noteService.getNoteById((long) 10009);
		
	}

	@Test
	void testAddNote() {
	Note note = JsonUtils.createNote((long) 10010, "Kumar", "Docker", "On Going", "Orchestration Tool");
	Note note1 = noteService.addNote(note);
	assertThat(note1);
	}

	@Test
	void testGetAllNotesByStatus() {
		 Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
		 Note note3 = JsonUtils.createNote((long) 10011, "Krishna", "Jenkins", "Done", "Continious Integration Tool");
		 List<Note> doneNotes = Arrays.asList(note1,note3);
	     List<Note> list1 = noteService.getAllNotesByStatus("Done");
	     assertThat(list1.containsAll(doneNotes));
		
	}

}
