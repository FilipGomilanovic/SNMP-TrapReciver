package main;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Trap {
	
	private JLabel router;
	private JLabel trapOid;
	private JLabel trapName;
	private JLabel time;

	public Trap(String routerr,String trapOidd, String trapNamee, String timee) {
		router = new JLabel(routerr, SwingConstants.CENTER);
		trapOid = new JLabel(trapOidd, SwingConstants.CENTER);
		trapName = new JLabel(trapNamee, SwingConstants.CENTER);
		time = new JLabel(timee, SwingConstants.CENTER);

	}	

	public JLabel getRouter() {
		return router;
	}
	public JLabel getTrapOid() {
		return trapOid;
	}
	public JLabel getTrapName() {
		return trapName;
	}
	public JLabel getTime() {
		return time;
	}
}
