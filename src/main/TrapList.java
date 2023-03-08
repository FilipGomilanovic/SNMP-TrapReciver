package main;

import java.util.ArrayList;

import gui.MainWindow;

public class TrapList{
	
	private ArrayList<Trap> trapList = new ArrayList<>();
	private MainWindow MW;
	public ArrayList<Trap> getTrapList() {
		return trapList;
	}

	public void dodajTrep(Trap trap) {
		trapList.add(trap);
		MW.add(trap);
		
	}
	
	public TrapList(MainWindow mw) {
		MW = mw;
	}
}
