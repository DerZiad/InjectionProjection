# WebSecurityPentest
# _Everyone want to try Web Pentesting, it exist a lot of project to test pentesting like Metasploitable, Bee project,Juice and DVWA_
This is our personal project wich contains 4 Principal attacks coded using JEE, we added solution in JEE for every attack, you can watch the code, you will see solution commented
- SQL Injection
- XSS (Stored,Reflected)
- Upload Shell
- Brute force 
##### This is a small challenge for you guys enjoy it
[![N|Solid](https://wnscom-bucket.s3.amazonaws.com/S3_5/Images/GenericHeaderBanner/MobileImg/19087/3093/Security-TRAC-Mobile-568x568px.jpg)](https://nodesource.com/products/nsolid)
# Installation
#### Database configuration
##### _We re using MySQL as principal database for our website so please follow the step to configure the Database_
#
```sh
CREATE DATABASE forumbancaire;
CREATE USER 'injection'@'localhost' IDENTIFIED BY 'injection';
GRANT ALL PRIVILEGES ON forumbancaire. * TO 'injection'@'localhost';
FLUSH PRIVILEGES;
exit # We should exit MySQL server
mysql -u injection -p < Database.sql
<typeyourpassword>
```  
#### Server configuration

  
coded by Derziads , ABOUHALI
