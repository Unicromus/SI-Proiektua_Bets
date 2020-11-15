package adapter;

import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLogic.BLFacade;
import configuration.ConfigXML;
import domain.Erabiltzailea;
import factory.FactoryBLFacade;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		ConfigXML c = ConfigXML.getInstance();
		BLFacade facadeInterface = (new FactoryBLFacade()).getBusinessLogicFactory(c);

		Erabiltzailea mikel = facadeInterface.getErabiltzailea("Mikel");

		ErabiltzaileaModelAdapter model = new ErabiltzaileaModelAdapter(mikel);

		JFrame j = new JFrame();

		JTable table = new JTable(model);

		j.add(new JScrollPane(table));

		j.setTitle(mikel.getErabizena() + "'s bets");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.pack();
		j.setVisible(true);
	}

}
