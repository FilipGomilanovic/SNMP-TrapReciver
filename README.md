# SNMP-TrapReciver

Aplication that monitors the operation of a part of a network using SNMP protocol.
The network being monitored is implemented within the GNS3 simulator
 
SNMP Java API is used for monitoring devices - <a>https://ireasoning.com/snmpapi.shtml</a>

This application continuously listens for these traps:
- bgpEstablishedNotification
- bgpBackwardTransNotification

and immediately displays an alarm on the screen when one of these two events occurs,
indicating which router the event occurred on and the exact time of the event.

![TrapReciver](https://user-images.githubusercontent.com/97192866/223717375-0783efca-5879-4914-8885-306964e2c218.png)
