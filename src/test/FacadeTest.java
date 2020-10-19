package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Result;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class FacadeTest {
	static BLFacadeImplementation sut;
	static BLFacadeImplementation testBL;

	private Event ev;

	@BeforeClass
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();

		// you can parametrize the DataAccess used by BLFacadeImplementation
		DataAccess da = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		sut = new BLFacadeImplementation(da);

		testBL = new BLFacadeImplementation();
	}


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

			//configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate );
			Question question = sut.createQuestion(ev, queryText, betMinimum);


			//invoke System Under Test (sut)  
			sut.createFee(question, erantzuna, betMinimum);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK  
			assertTrue(true);
		} finally {
			//Remove the created objects in the database (cascade removing)   
			boolean b = testBL.deleteEvent(ev);
			System.out.println("Finally "+b);          
		}
	}


	@Test
	// ExceptionFeeNegatiboa()
	public void test2() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = -2.0f;		// betMinimum > 0 izan behar du
			String erantzuna = "erantzuna";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2022");

			//configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate );
			Question question = sut.createQuestion(ev, queryText, betMinimum);


			//invoke System Under Test (sut)  
			sut.createFee(question, erantzuna, betMinimum);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK  
			assertTrue(true);
		} finally {
			//Remove the created objects in the database (cascade removing)   
			boolean b = testBL.deleteEvent(ev);
			System.out.println("Finally "+b);          
		}
	}


	@Test
	// ExceptionEventFinished()
	public void test3() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = 2.0f;		// betMinimum > 0 izan behar du
			String erantzuna = "erantzuna";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2019");

			//configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate );
			Question question = sut.createQuestion(ev, queryText, betMinimum);


			//invoke System Under Test (sut)  
			sut.createFee(question, erantzuna, betMinimum);


			//if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK  
			assertTrue(true);
		} finally {
			//Remove the created objects in the database (cascade removing)   
			boolean b = testBL.deleteEvent(ev);
			System.out.println("Finally "+b);          
		}
	}


	@Test
	// return "erantzuna"
	public void test4() throws ParseException {
		try {
			//define paramaters
			String queryText = "proba galdera";
			float betMinimum = 2.0f;		// betMinimum > 0 izan behar du
			String erantzuna = "erantzuna";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = sdf.parse("05/10/2022");

			//configure the state of the system (create object in the dabatase)
			ev = testBL.addEvent(queryText, oneDate );
			Question question = sut.createQuestion(ev, queryText, betMinimum);


			//invoke System Under Test (sut)  
			Result res = sut.createFee(question, erantzuna, betMinimum);


			//verify the results
			assertEquals(res.getResult(), erantzuna);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			boolean b = testBL.deleteEvent(ev);
			System.out.println("Finally "+b);          
		}
	}


}
