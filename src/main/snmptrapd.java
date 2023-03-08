package main;

import com.ireasoning.protocol.*;
import com.ireasoning.protocol.snmp.*;
import com.ireasoning.util.ParseArguments;

import gui.MainWindow;

import java.awt.Label;
import java.awt.Panel;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class snmptrapd implements Listener {

	
	private String router;
	private String trapOid;
	private String trapName;
	private String time;
	private SnmpTrapdSession session;
	MainWindow MW;
		
	
	public snmptrapd(MainWindow mw) {
		MW = mw;
	}
	
	public void closeSession() {
		if (session != null)
			session.close();
	}
	
	
    public void trapd()
    {
        try
        {
            System.out.println( "Trap receiver listening on port: " + 1026);
            session = new SnmpTrapdSession(1026);
            session.addListener(this);
            session.waitForTrap();
            session.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public synchronized void handleMsg(Object msgSender, Msg msg)  
    {
        if(msg.getType() == Msg.ERROR_TYPE)
        {
            ErrorMsg err = (ErrorMsg) msg;
            System.out.println( "Error occurred. Error code: " + err.getErrorCode() + ". Error message: " + err.getErrorString() );
        }
        else
        {
            SnmpDataType t = (SnmpDataType)msg;
            if(t.getType() == SnmpConst.V2TRAP)
            {
                SnmpV2Notification trap = (SnmpV2Notification)t;
                printV2Notification(trap);
            }
        	
        }
    }
    
   
    
    private void printV2Notification(SnmpV2Notification trap)
    {
    	System.out.println("Usao ovde");
    	String name = MibUtil.translateOID("" + trap.getSnmpTrapOID(), false);
    	if(name == null)
        {
            name = "" + trap.getSnmpTrapOID();
        }
    	if (!name.equals(".1.3.6.1.4.1.9.9.187.0.1")  && !name.equals(".1.3.6.1.4.1.9.9.187.0.2")) {
    		router = "Received SNMPv2 trap from " + trap.getIpAddress();
    		
    		if (router.substring(35, 38).equals("2.2") || router.substring(35, 38).equals("3.2")) {
    			router = router + " (R2)";
    		}
    		else if (router.substring(35, 38).equals("3.3"))  {
    			router = router + " (R3)";
    		}
//    		else if (router.substring(35, 38).equals("3.1") || router.substring(35, 38).equals("2.1"))  {
//    			router = router + " (R1)";
//    		}
//    		else if (router.substring(35, 38).equals("3.3"))  {
//    			router = router + " (R3)";
//    		}
    		else {
    			router = router + " (R1)";
    		}
    		
    		trapOid = "SnmpTrap Oid: " + name;
	        
	        if (name.equals(".1.3.6.1.2.1.15.7.2"))
	        	trapName = " ***bgpEstablished*** ";
	        else 
	        	trapName = " ***bgpBackwardTrans*** ";
	        
	        LocalTime x = java.time.LocalTime.now();
	        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
	        time = "Vreme pristizanja: " + x.format(myFormatObj);
	        MW.getList().dodajTrep(new Trap(router,trapOid,trapName,time));

    	}
    }  
}

