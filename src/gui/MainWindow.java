package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.Timer;
import main.Trap;
import main.TrapList;
import main.snmptrapd;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private snmptrapd traps;
	private JLabel l1 = new JLabel("Show: ");
	private TrapList l = new TrapList(this);
	private JComboBox<String> cb = new JComboBox<String>();
	private JPanel p = new JPanel(new GridLayout(0, 1));
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p2 = new JPanel(new GridLayout(0, 1));
	private JPanel p3 = new JPanel(new BorderLayout());
	private JPanel contentPane;
	private Trap lastRecivedTrap;
	private boolean flag = false;
	CardLayout cardLayout = new CardLayout();
	private boolean prvi = true;

	public TrapList getList() {
		return l;
	}

	public Trap getLastRecivetTrap() {
		return lastRecivedTrap;
	}

	public void add(Trap trap) {
		Timer t = new Timer(this,trap);
		t.start();

		p.add(trap.getRouter());
		p.add(trap.getTrapOid());
		p.add(trap.getTrapName());
		p.add(trap.getTime());
		p.add(new JLabel());

		revalidate();
	}

	public MainWindow() {
		populateWindow();

	}
	
	public void setFlag(boolean b) {
		flag = b;
	}

	private void populateWindow() {
		setLocation(600, 100);
		setSize(new Dimension(500, 440));
		setBackground(Color.gray);

		setTitle("Trap Reciver");

		p1.add(p, BorderLayout.NORTH);
		p3.add(p2, BorderLayout.NORTH);

		contentPane = new JPanel(null);
		contentPane.setBackground(new Color(133, 213, 127));

		getContentPane().setBackground(Color.RED);
		JScrollPane scrollPane = new JScrollPane(p1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JScrollPane scrollPane1 = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	    scrollPane1.getVerticalScrollBar().setUnitIncrement(16);

		JPanel panel = new JPanel();
		panel.setBounds(140, 12, 50, 30);
		panel.setBackground(new Color(133, 213, 127));
		panel.add(l1);
		contentPane.add(panel);
		cb.setBounds(200, 10, 160, 30);
		cb.addItem("All traps");
		cb.addItem("Recived from R1");
		cb.addItem("Recived from R2");
		cb.addItem("Recived from R3");
		
		 scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() { 
			 public void adjustmentValueChanged(AdjustmentEvent e) { 
				 if (flag == true)
					 e.getAdjustable().setValue(e.getAdjustable().getMaximum()); 
		 
		 }});
		
		JPanel card = new JPanel(cardLayout);
		card.setBounds(50, 50, 400, 300);
		card.add(scrollPane);
		card.add(scrollPane1);
		contentPane.add(cb);
		contentPane.add(card);
		
		class ItemChangeListener implements ItemListener{
		    @Override
		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
 
		          int item = cb.getSelectedIndex();
		          if (item == 0) {
		        	  if (prvi == false) {
		        		  cardLayout.next(card);
		        		  prvi = true;	        		  
		        	  }
		          }
		          else if (item == 1) {
		        	  if (prvi == true) {
		        		  cardLayout.next(card);
		        		  prvi = false;     	
		        	  }
		        	  p2.removeAll();
	        		  p2.revalidate();
		        	  for (Trap i : getList().getTrapList()) {
		        		  
		        		  if (i.getRouter().getText().charAt(41) != '2' && i.getRouter().getText().charAt(41) != '3') {
		        			  Trap temp = new Trap(i.getRouter().getText(),i.getTrapOid().getText(),i.getTrapName().getText(),i.getTime().getText());
		        			  p2.add(temp.getRouter());
		        			  p2.add(temp.getTrapOid());
		        			  p2.add(temp.getTrapName());
		        		      p2.add(temp.getTime());
		        			  p2.add(new JLabel());
		        		  }
		        	  }
		          }
		          else if (item == 2) {
     	        	  if (prvi == true) {
		        		  cardLayout.next(card);
		        		  prvi = false; 
     	        	  }
     	        	  p2.removeAll();
	        		  p2.revalidate();
     	        	  for (Trap i : getList().getTrapList()) {
     	        		  
		        		  if (i.getRouter().getText().charAt(41) == '2') {
		        			  Trap temp = new Trap(i.getRouter().getText(),i.getTrapOid().getText(),i.getTrapName().getText(),i.getTime().getText());
		        			  p2.add(temp.getRouter());
		        			  p2.add(temp.getTrapOid());
		        			  p2.add(temp.getTrapName());
		        		      p2.add(temp.getTime());
		        			  p2.add(new JLabel());
		        		  }
		        	  }
		          }
		          else if (item == 3) {
		        	  if (prvi == true) {
		        		  cardLayout.next(card);
		        		  prvi = false; 
		        	  }
		        	  p2.removeAll();
	        		  p2.revalidate();
		        	  for (Trap i : getList().getTrapList()) {
		        		  
		        		  if (i.getRouter().getText().charAt(41) == '3') {
		        			  Trap temp = new Trap(i.getRouter().getText(),i.getTrapOid().getText(),i.getTrapName().getText(),i.getTime().getText());
		        			  p2.add(temp.getRouter());
		        			  p2.add(temp.getTrapOid());
		        			  p2.add(temp.getTrapName());
		        		      p2.add(temp.getTime());
		        			  p2.add(new JLabel());
		        		  }
		        	  }
		          }
		       }
		    }     
		}
		
		cb.addItemListener(new ItemChangeListener());

		add(contentPane);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				traps.closeSession();
				dispose();
			}
		});
		setVisible(true);
		traps = new snmptrapd(this);
		traps.trapd();

	}

	public static void main(String[] args) {
		new MainWindow();

	}

}

/*
 
 snmp-server host 192.168.122.1 traps version 2c bgp udp-port 1026
 snmp-server enable traps bgp 
 
 router bgp 1
 no synchronization
 bgp log-neighbor-changes
 network 192.168.10.0
 network 192.168.122.0
 neighbor 192.168.12.2 remote-as 2
 neighbor 192.168.13.3 remote-as 3
 no auto-summary
 
 router bgp 2
 no synchronization
 bgp log-neighbor-changes
 network 192.168.20.0
 neighbor 192.168.12.1 remote-as 1
 neighbor 192.168.23.3 remote-as 3
 no auto-summary
 
 router bgp 3
 no synchronization
 bgp log-neighbor-changes
 network 192.168.30.0
 neighbor 192.168.13.1 remote-as 1
 neighbor 192.168.23.2 remote-as 2
 no auto-summary
 
 */
 
 
