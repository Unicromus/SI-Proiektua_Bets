package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Result;
import domain.ResultContainer;
import domain.SuperBet;
import domain.SuperBetContainer;
import domain.User;
import domain.Admin;
import domain.Bet;
import domain.BetContainer;
import domain.Erabiltzailea;
import domain.Event;
import domain.Langilea;
import domain.Mugimendua;
import domain.MugimenduaContainer;
import domain.QuestionContainer;
import exceptions.EndResultAlreadyExists;
import exceptions.EventFinished;
import exceptions.EventInCurrent;
import exceptions.MaximumMoneyInserted;
import exceptions.NotEnoughMoney;
import exceptions.QuestionAlreadyExist;
import exceptions.ResultAlreadyExist;
import exceptions.SuperBetMinimumRes;
import exceptions.betMinimum;
import iterator.ExtendedIterator;
import iterator.ExtendedIteratorEvents;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	private static final String ETIQUETAS = "Etiquetas";
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}
	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager = da;
	}

	// ----------------------------------------------------

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		dbManager.initializeDB();
		dbManager.close();
	}

	// ----------------------------------------------------

	/**
	 * This method insert new user in database; if the user does not exist in DB
	 * 
	 * @param Izena
	 *            user name
	 * @param Pasahitza
	 *            user password
	 * @return true <--> user does not exists in database
	 */
	@WebMethod
	public boolean register(boolean langileaDa, String iz, String ab1, String ab2, String erabiz, String pass,
			String nan, String jd, String email, String tlf, String helb, String pstkod, String hrld, String prob,
			String herria) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		boolean b = dbManager.Register(langileaDa, iz, ab1, ab2, erabiz, pass, nan, jd, email, tlf, helb, pstkod, hrld,
				prob, herria);
		dbManager.close();
		return b;
	}

	/**
	 * This method check if user exist in Database
	 * 
	 * @param izena
	 *            user name
	 * @param pasahitza
	 *            user password
	 * @return true <--> user exists in database
	 */
	@WebMethod
	public boolean isRegister(String izena, String pasahitza) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		boolean b = dbManager.isRegister(izena, pasahitza);
		dbManager.close();
		return b;
	}

	// ----------------------------------------------------

	@WebMethod
	public QuestionContainer getQuestionContainer(Question question) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		QuestionContainer questionContainer = dbManager.getQuestionContainer(question);
		dbManager.close();
		return questionContainer;
	}

	@WebMethod
	public ResultContainer getResultContainer(Result result) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		ResultContainer resultContainer = dbManager.getResultContainer(result);
		dbManager.close();
		return resultContainer;
	}

	@WebMethod
	public BetContainer getBetContainer(Bet bet) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		BetContainer betContainer = dbManager.getBetContainer(bet);
		dbManager.close();
		return betContainer;
	}

	@WebMethod
	public SuperBetContainer getSuperBetContainer(SuperBet superBet) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		SuperBetContainer superBetContainer = dbManager.getSuperBetContainer(superBet);
		dbManager.close();
		return superBetContainer;
	}

	@WebMethod
	public MugimenduaContainer getMugimenduaContainer(Mugimendua mugimendua) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		MugimenduaContainer mugimenduaContainer = dbManager.getMugimenduaContainer(mugimendua);
		dbManager.close();
		return mugimenduaContainer;
	}

	// ----------------------------------------------------

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date
	 *            in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	@WebMethod
	public ExtendedIterator<Event> getEventsIterator(Date date) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return new ExtendedIteratorEvents(new ArrayList<Event>(events));
	}

	@WebMethod
	public Vector<Question> getQuestions(Event event) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Vector<Question> qu = dbManager.getQuestions(event);
		dbManager.close();
		return qu;
	}

	@WebMethod
	public Vector<Result> getResults(Question question) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Vector<Result> qu = dbManager.getResults(question);
		dbManager.close();
		return qu;
	}

	@WebMethod
	public Vector<Mugimendua> getMugimenduak(String izena) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Vector<Mugimendua> mugimenduak = dbManager.getMugimenduak(izena);
		dbManager.close();
		return mugimenduak;
	}

	@WebMethod
	public User getUser(String izena) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		User user = dbManager.getUser(izena);
		dbManager.close();
		return user;
	}

	@WebMethod
	public Erabiltzailea getErabiltzailea(String erab) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		return dbManager.getErabiltzailea(erab);
	}

	@WebMethod
	public Langilea getLangilea(String erab) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		return dbManager.getLangilea(erab);
	}

	@WebMethod
	public Admin getAdmin(String erab) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		return dbManager.getAdmin(erab);
	}

	// ----------------------------------------------------

	@WebMethod
	public void gertaeraBatBikoiztu(Event ev, Date data) throws EventFinished {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		if (new Date().compareTo(data) >= 0)
			throw new EventFinished(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorEventHasFinished"));
		dbManager.gertaeraBatBikoiztu(ev, data);
		dbManager.close();
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event
	 *            to which question is added
	 * @param question
	 *            text of the question
	 * @param betMinimum
	 *            minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished
	 *             if current data is after data of the event
	 * @throws QuestionAlreadyExist
	 *             if the same question already exists for the event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {
		// The minimum bed must be greater than 0
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Question qry = null;
		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorEventHasFinished"));
		qry = dbManager.createQuestion(event, question, betMinimum);
		dbManager.close();
		return qry;
	}

	@WebMethod
	public Result createFee(Question q, String res, float fee) throws ResultAlreadyExist, EventFinished {
		// The minimum bed must be greater than 0
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		if (new Date().compareTo(dbManager.getQuestionContainer(q).getEvent().getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorEventHasFinished"));
		Result r = null;
		r = dbManager.createFee(q, res, fee);
		dbManager.close();
		return r;
	}

	@WebMethod
	public boolean deleteEvent(Event e) {
		boolean res = true;
		try {
			dbManager.open(false);
			// DataAccess dBManager = new DataAccess();
			dbManager.deleteEvent(e);
		} catch (Exception e1) {
			e1.printStackTrace();
			res = false;
		}
		return res;
	}

	@WebMethod
	public boolean deleteQuestion(Question q) {
		boolean res = true;
		try {
			dbManager.open(false);
			// DataAccess dBManager = new DataAccess();
			dbManager.deleteQuestion(q);
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	@WebMethod
	public boolean deleteResult(Result r) {
		boolean res = true;
		try {
			dbManager.open(false);
			// DataAccess dBManager = new DataAccess();
			dbManager.deleteResult(r);
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	// ----------------------------------------------------

	@WebMethod
	public void addDirua(Erabiltzailea erab, float dirua) throws MaximumMoneyInserted {
		if (dirua > 500)
			throw new MaximumMoneyInserted(ResourceBundle.getBundle(ETIQUETAS).getString("MaxMoneyInsert"));
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		dbManager.addDirua(erab, dirua);
		dbManager.close();
	}

	@WebMethod
	public void superApustuaEgin(String erab, float price, Vector<Result> r)
			throws NotEnoughMoney, betMinimum, EventFinished, SuperBetMinimumRes {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Erabiltzailea erabiltzailea = dbManager.getErabiltzailea(erab);
		Vector<Result> results = dbManager.getResults(r);
		float batura = 0;

		// bi kontainer erabili beharko dira atzera egiteko
		for (Result re : results) {
			Question q = dbManager.getResultContainer(re).getQuestion();
			Event e = dbManager.getQuestionContainer(q).getEvent();
			if (new Date().compareTo(e.getEventDate()) > 0)
				throw new EventFinished(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorEventHasFinished"));
			batura += q.getBetMinimum();
		}

		if (results.size() <= 1)
			throw new SuperBetMinimumRes(ResourceBundle.getBundle(ETIQUETAS).getString("SuperBetMinimumres"));
		if (erabiltzailea.getDiruzorroa() < price)
			throw new NotEnoughMoney(ResourceBundle.getBundle(ETIQUETAS).getString("NotEnoughMoney"));
		if (batura > price)
			throw new betMinimum(ResourceBundle.getBundle(ETIQUETAS).getString("betMinimum"));

		dbManager.superApustuaEgin(erabiltzailea, price, results);
		dbManager.close();
	}

	@WebMethod
	public void apustuaEgin(String erab, float price, Result res) throws NotEnoughMoney, betMinimum, EventFinished {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		// bi kontainer erabili beharko dira atzera egiteko
		if (new Date().compareTo(dbManager.getQuestionContainer(dbManager.getResultContainer(res).getQuestion())
				.getEvent().getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorEventHasFinished"));

		Erabiltzailea erabiltzailea = dbManager.getErabiltzailea(erab);
		Result result = dbManager.getResult(res);

		if (erabiltzailea.getDiruzorroa() < price)
			throw new NotEnoughMoney(ResourceBundle.getBundle(ETIQUETAS).getString("NotEnoughMoney"));
		// kontainer bat erabiliz
		if (dbManager.getResultContainer(result).getQuestion().getBetMinimum() > price)
			throw new betMinimum(ResourceBundle.getBundle(ETIQUETAS).getString("betMinimum"));

		dbManager.apustuaEgin(erabiltzailea, price, result);
		dbManager.close();
	}

	@WebMethod
	public float getDirua(Erabiltzailea erab) {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		float diru = dbManager.getDirua(erab);
		dbManager.close();
		return diru;
	}

	@WebMethod
	public float getIrabazia() {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		float irabaziak = dbManager.getIrabazia();
		dbManager.close();
		return irabaziak;
	}

	@WebMethod
	public void emaitzaipini(Result emaitza) throws EventInCurrent, EndResultAlreadyExists {
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		if (new Date().compareTo(dbManager.getResultContainer(emaitza).getQuestion().getEvent().getEventDate()) < 0)
			throw new EventInCurrent(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorEventInCurrent"));
		dbManager.emaitzaipini(emaitza);
		dbManager.close();
	}

	// ----------------------------------------------------

	@WebMethod
	public Vector<Erabiltzailea> getErabiltzaileak() {
		System.out.println(">> DataAccess: getUsers");
		dbManager.open(false);
		// DataAccess dBManager = new DataAccess();
		Vector<Erabiltzailea> u = dbManager.getErabiltzaileak();
		dbManager.close();
		return u;
	}

	@WebMethod
	public boolean deleteErabiltzailea(String erabizena) {
		boolean b = true;
		try {
			dbManager.open(false);
			// DataAccess dBManager = new DataAccess();
			dbManager.deleteErabiltzailea(erabizena);
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	// ----------------------------------------------------

	public Event addEvent(String desc, Date d) {
		dbManager.open(false);
		Event o = dbManager.addEvent(desc, d);
		dbManager.close();
		return o;
	}

}
