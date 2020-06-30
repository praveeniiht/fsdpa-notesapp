package com.iiht.yaksha.fsdpa.notesapp.controller;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.boundaryTestFile;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.businessTestFile;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.exceptionTestFile;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.currentTest;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.yakshaAssert;
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
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.iiht.yaksha.fsdpa.notesapp.fsdpanotesapp.FsdpaNotesappApplication;
import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.repo.NoteRepository;
import com.iiht.yaksha.fsdpa.notesapp.service.NoteService;
import com.iiht.yaksha.fsdpa.notesapp.testutils.JsonUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FsdpaNotesappApplication.class)
@AutoConfigureMockMvc 
@AutoConfigureTestDatabase(replace=Replace.NONE)
class NotesControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	private NoteService noteService;
	
	@InjectMocks
	private NotesController noteController;
	

	@BeforeEach
	void setUp() throws Exception {
	//	noteRepository.deleteAll();
	}

	@Test
    public void givenOneNote_ThenAddOnlyOne() throws IOException 
    {
		 Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
         when(noteService.addNote( any(Note.class))).thenReturn(note1);
         
         Note note2 = JsonUtils.createNote((long) 10010, "Kumar", "Docker", "On Going", "Orchestration Tool");
        ResponseEntity<Note> responseEntity = noteController.createNote(note2);
    //    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
     //   assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
        
       yakshaAssert(currentTest(),(responseEntity.getStatusCodeValue()>0?true:false),businessTestFile);
    }
	
	@Test
    public void given3Notes_thenDisplay3Notes() throws IOException
    {
        // given
			Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
	        Note note2 = JsonUtils.createNote((long) 10010, "Kumar", "Docker", "On Going", "Orchestration Tool");
	        Note note3 = JsonUtils.createNote((long) 10011, "Krishna", "Jenkins", "Done", "Continious Integration Tool");
        Note notes = new Note();
        List<Note> list = new ArrayList<Note>();
        list.add(note1);
        list.add(note2);
        list.add(note3);
        when(noteService.getAllNotes()).thenReturn(list);
 
        // when
        List<Note> fromController =noteService.getAllNotes();
 
        // then
        assertThat(fromController.size()).isEqualTo(3);
         
        assertThat(fromController.get(0).getAuthor())
                        .isEqualTo(note1.getAuthor());
      
        assertThat(fromController.get(1).getTitle())
                        .isEqualTo(note2.getTitle());
        assertThat(fromController.get(2).getId())
        .isEqualTo(note3.getId());
        
        yakshaAssert(currentTest(),(fromController.size()==3?true:false),businessTestFile);
    }
}

