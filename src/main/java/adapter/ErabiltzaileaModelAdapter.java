package adapter;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Bet;
import domain.Erabiltzailea;
import domain.Event;
import domain.Mugimendua;
import domain.Question;
import domain.SuperBet;
import domain.User;

public class ErabiltzaileaModelAdapter extends AbstractTableModel {

	private final List<Bet> bets;
	private Erabiltzailea erabiltzailea;
	private String[] colNames = new String[] { "Event", "Question", "Event Date", "Bet (€)" };

	public ErabiltzaileaModelAdapter(Erabiltzailea erab) {
		this.erabiltzailea = erab;
		bets = new ArrayList<Bet>(erab.getEbets());
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return bets.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ((Object) bets.get(rowIndex).getResult().getQuestion().getEvent().getDescription());
		case 1:
			return ((Object) bets.get(rowIndex).getResult().getQuestion().getQuestion());
		case 2:
			return ((Object) bets.get(rowIndex).getResult().getQuestion().getEvent().getEventDate());
		case 3:
			return ((Object) bets.get(rowIndex).getPrice());
		}
		return null;
	}

}
