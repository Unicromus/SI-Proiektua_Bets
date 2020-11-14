package gui;

import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import factory.FactoryBLFacade;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

public class ApplicationLauncher {

	public static void main(String[] args) {

		ConfigXML c = ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: " + Locale.getDefault());

		// ----------------------------------------------------
		UnRegisteredGUI hasieraPantaila = new UnRegisteredGUI();
		hasieraPantaila.setVisible(true);
		// ----------------------------------------------------

		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			BLFacade appFacadeInterface;
			FactoryBLFacade factoryBLFacade = new FactoryBLFacade();

			if (c.isBusinessLogicLocal()) {
				appFacadeInterface = factoryBLFacade.localBLFacade(c);
			} else { // Si es remoto
				appFacadeInterface = factoryBLFacade.remoteBLFacade(c);
			}

			/*
			 * if (c.getDataBaseOpenMode().equals("initialize"))
			 * appFacadeInterface.initializeBD();
			 */

			UnRegisteredGUI.setBussinessLogic(appFacadeInterface);
			RegisterGUI.setBussinessLogic(appFacadeInterface);
			FindQuestionsGUI.setBussinessLogic(appFacadeInterface);
			AdminGUI.setBussinessLogic(appFacadeInterface);
			LangileaGUI.setBussinessLogic(appFacadeInterface);
			ErabiltzaileaGUI.setBussinessLogic(appFacadeInterface);
			SeeMugimenduakGUI.setBussinessLogic(appFacadeInterface);
			InsertMoneyGUI.setBussinessLogic(appFacadeInterface);
			CreateFeesGUI.setBussinessLogic(appFacadeInterface);
			CreateQuestionGUI.setBussinessLogic(appFacadeInterface);
			CreateWorkerGUI.setBussinessLogic(appFacadeInterface);
			DeleteFeesGUI.setBussinessLogic(appFacadeInterface);
			DeleteQuestionsGUI.setBussinessLogic(appFacadeInterface);
			PutResultGUI.setBussinessLogic(appFacadeInterface);
			DeleteErabiltzaileaGUI.setBussinessLogic(appFacadeInterface);
			DeleteEventsGUI.setBussinessLogic(appFacadeInterface);
			GertaeraBatBikoiztuGUI.setBussinessLogic(appFacadeInterface);
			ApustuAnitzaGUI.setBussinessLogic(appFacadeInterface);

		} catch (Exception e) {
			// a.jLabelSelectOption.setText("Error: "+e.toString());
			// a.jLabelSelectOption.setForeground(Color.RED);
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
		// a.pack();

	}
}
