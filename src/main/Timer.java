package main;

import java.awt.Color;

import gui.MainWindow;

public class Timer extends Thread{
	private Trap lastRecivedTrap;
	private Trap trap;
	private MainWindow mainW; 
	
	@Override
	public void run() {
		if (lastRecivedTrap != trap)
			mainW.setFlag(true);
			trap.getRouter().setForeground(Color.red);
			trap.getTrapOid().setForeground(Color.red);
			trap.getTrapName().setForeground(Color.red);
			trap.getTime().setForeground(Color.red);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			trap.getRouter().setForeground(Color.BLACK);
			trap.getTrapOid().setForeground(Color.BLACK);
			trap.getTrapName().setForeground(Color.BLACK);
			trap.getTime().setForeground(Color.BLACK);
			mainW.setFlag(false);
		
		
	}
	public Timer(MainWindow mw,Trap t) {
		trap = t;
		mainW = mw;
	}
	

}
