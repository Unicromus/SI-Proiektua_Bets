
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Result;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FacadeMockTest {
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	Event mockedEvent = Mockito.mock(Event.class);

	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);


	@Test
	// ExceptionParametroDesegokiak()
	public void test1() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = 2.0f;
			String erantzuna = null;		// "erantzuna"

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2022");

			//configure Mock
			Question mockedQuestion = Mockito.mock(Question.class);

			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.when(dataAccess.createFee(Mockito.any(Question.class), Mockito.eq(null), Mockito.any(float.class))).thenThrow(Exception.class);


			//invoke System Under Test (sut)  
			sut.createFee(mockedQuestion, erantzuna, betMinimum);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK  
			assertTrue(true);
		}
	}


	@Test
	// ExceptionFeeNegatiboa()
	public void test2() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = -2.0f;
			String erantzuna = "erantzuna";		// "erantzuna"

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2022");

			//configure Mock
			Question mockedQuestion = Mockito.mock(Question.class);

			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.when(dataAccess.createFee(Mockito.any(Question.class), Mockito.any(String.class), Mockito.eq(-2.0f))).thenThrow(Exception.class);


			//invoke System Under Test (sut)  
			sut.createFee(mockedQuestion, erantzuna, betMinimum);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK  
			assertTrue(true);
		}
	}


	@Test
	// ExceptionEventFinished()
	public void test3() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = 2.0f;
			String erantzuna = "erantzuna";		// "erantzuna"

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2019");

			//configure Mock
			Question mockedQuestion = Mockito.mock(Question.class);

			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.when(dataAccess.createFee(Mockito.any(Question.class), Mockito.any(String.class), Mockito.any(float.class))).thenThrow(Exception.class);


			//invoke System Under Test (sut)  
			sut.createFee(mockedQuestion, erantzuna, betMinimum);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK  
			assertTrue(true);
		}
	}


	@Test
	// return "erantzuna"
	public void test4() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = 2.0f;
			String erantzuna = "erantzuna";		// "erantzuna"

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2019");

			//configure Mock
			Question mockedQuestion = Mockito.mock(Question.class);

			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.doReturn(new Result(erantzuna, betMinimum, mockedQuestion)).when(dataAccess).createFee(Mockito.any(Question.class),Mockito.any(String.class), Mockito.any(float.class));


			//invoke System Under Test (sut)  
			sut.createFee(mockedQuestion, erantzuna, betMinimum);


			//verify the results
			//Mockito.verify(dataAccess,Mockito.times(1)).createFee(Mockito.any(Question.class),Mockito.any(String.class), Mockito.any(float.class));

			ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);
			ArgumentCaptor<String> resultStringCaptor = ArgumentCaptor.forClass(String.class);
			ArgumentCaptor<Float> betMinimunCaptor = ArgumentCaptor.forClass(Float.class);

			Mockito.verify(dataAccess,Mockito.times(1)).createFee(questionCaptor.capture(),resultStringCaptor.capture(), betMinimunCaptor.capture());

			assertEquals(resultStringCaptor.getValue(), erantzuna);

		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}


}
