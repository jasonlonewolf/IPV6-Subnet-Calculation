# ipv6 subnet calcuator
Files:
Server: Server.java, Server.class
Client: Client.java, Client.class
Subnet calculator: Main.java, Main.class
 
Usage:
 
                Server:
                java Server
               
                Client:
 
                Java Client argument  ex) java Client 123:123::/40
 
Result:
                The Server create server.txt file from the argument of the client,
                and server sends server.txt to the client. Then, the client saves the file as client.txt
               The files are identical.
Ex)
server.txt
-----------------------------------------------------------
IPv6 address: 123:123::/40
Compressed Address: 123:123::
validation: true
Expanded Address:
0123:0123:0000:0000:0000:0000:0000:0000
first IP
123:123:0:0:0:0:0:0
last IP
123:123:ff:ffff:ffff:ffff:ffff:ffff
Prefix
ffff:ffff:ff00:0000:0000:0000:0000:0000
Number of /64s:
16777216

client.txt
---------------------------------------------------------------
IPv6 address: 123:123::/40
Compressed Address: 123:123::
validation: true
Expanded Address:
0123:0123:0000:0000:0000:0000:0000:0000
first IP
123:123:0:0:0:0:0:0
last IP
123:123:ff:ffff:ffff:ffff:ffff:ffff
Prefix
ffff:ffff:ff00:0000:0000:0000:0000:0000
Number of /64s:
16777216
