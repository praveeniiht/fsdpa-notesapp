package com.iiht.yaksha.fsdpa.notesapp.exceptiontests;

import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.currentTest;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.exceptionTestFile;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.yakshaAssert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.iiht.yaksha.fsdpa.notesapp.exceptions.InvalidNoteIdException;
import com.iiht.yaksha.fsdpa.notesapp.exceptions.InvalidNoteStatusException;
import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.repo.NoteRepository;
import com.iiht.yaksha.fsdpa.notesapp.service.NoteService;
import com.iiht.yaksha.fsdpa.notesapp.service.NoteServiceImpl;
import com.iiht.yaksha.fsdpa.notesapp.testutils.JsonUtils;


@ExtendWith(SpringExtension.class)
public class NotesAppExceptionsTest {
	
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

	@Test
	public void testInvalidStatus() throws IOException {
		Mockito.when(noteService.getAllNotesByStatus("pending"))
        .thenThrow(InvalidNoteStatusException.class);
		
		InvalidNoteStatusException e = assertThrows(InvalidNoteStatusException.class, () -> {
		    noteService.getAllNotesByStatus("pending");
		  });
		yakshaAssert(currentTest(),(e!=null?true:false),exceptionTestFile);	       	
	}
	
	@Test
	public void testInvalidNoteId() throws IOException {
		 Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
		Mockito.when(noteService.getNoteById((long) 10009))
		.thenThrow(InvalidNoteIdException.class);
		InvalidNoteIdException e = assertThrows(InvalidNoteIdException.class, () -> {
		    noteService.getNoteById((long) 10009);
		  });
	 yakshaAssert(currentTest(),(e!=null?true:false),exceptionTestFile);
	}
  }
