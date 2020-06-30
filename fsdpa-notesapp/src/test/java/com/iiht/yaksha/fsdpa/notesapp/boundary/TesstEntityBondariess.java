package com.iiht.yaksha.fsdpa.notesapp.boundary;

import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.boundaryTestFile;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.currentTest;
import static com.iiht.yaksha.fsdpa.notesapp.testutils.TestUtils.yakshaAssert;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.iiht.yaksha.fsdpa.notesapp.model.Note;
import com.iiht.yaksha.fsdpa.notesapp.testutils.JsonUtils;

@ExtendWith(SpringExtension.class)
public class TesstEntityBondariess {
	
	@Test
	public void testAuthorNameLength() throws Exception {

		Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
		int maxChar = 20;
		boolean authorLength = ((note1.getAuthor().length()) >= maxChar);
		 yakshaAssert(currentTest(),(authorLength?true:false),boundaryTestFile);

	}
	
	@Test
	public void testNotesTitleLength() throws Exception {
		Note note1 = JsonUtils.createNote((long) 10009, "Praveen", "Java", "Done", "Object Oriented Programming");
		int maxChar = 20;
		boolean titleLength = ((note1.getTitle().length()) >= maxChar);
		 yakshaAssert(currentTest(),(titleLength?true:false),boundaryTestFile);

	}

}
