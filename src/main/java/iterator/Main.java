package iterator;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import businessLogic.BLFacade;
import configuration.ConfigXML;
import domain.Event;
import factory.FactoryBLFacade;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		// Facade objektua lortu lehendabiziko ariketa erabiliz
		ConfigXML c = ConfigXML.getInstance();
		BLFacade facadeInterface = (new FactoryBLFacade()).getBusinessLogicFactory(c);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date;

		try {
			date = sdf.parse("17/12/2020");
			ExtendedIterator<Event> i = facadeInterface.getEventsIterator(date);
			Event ev;

			System.out.println("_____________________");
			System.out.println("ATZETIK AURRERAKA");
			i.goLast(); // Azkeneko elementuan kokatu
			while (i.hasPrevious()) {
				ev = i.previous();
				System.out.println(ev.toString());
			}
			System.out.println();
			System.out.println("_____________________");
			System.out.println("AURRETIK ATZERAKA");
			// Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
			i.goFirst(); // Lehen elem. kokatu
			while (i.hasNext()) {
				ev = i.next();
				System.out.println(ev.toString());
			}

		} catch (ParseException e1) {
			System.out.println("Problems with date?? " + "17/12/2020");
		}
	}

}
