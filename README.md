# **PentestMe**
### Description
 - PentestMe is a website designed for ethical hackers to learn how to exploit and protect themselves from XSS, SQL, Upload Shell, Bruteforce. The site offers a variety of exercises and tutorials aimed at helping users understand the different techniques and tools used in pentesting websites.

 -  PentestMe is an excellent resource for anyone interested in ethical hacking and improving their security skills. The website offers extensive materials and tutorials to help understand the concepts and apply the techniques in practice. In addition, the website also has tools and resources to help users protect themselves from attacks and make their own websites more secure.
 
 - The Project is created using JEE, for an university presentation to explain 4 hacking attacks, which are : 
    
    - SQL Injection
    - XSS Stored and Reflected
    - Bruteforce
    - Upload Shell
    
[![N|Solid](./pictures/pentestme.jpg)](https://nodesource.com/products/nsolid)
# Installation
## Requirement

-  Install Java jdk 16 and configure JAVA_HOME
-  Install Eclipse EE and configure tomcat9
-  Install Maven and add it to the environment PATH
-  Resolving Project dependencies : 
```sh
    cd PentestMe
    mvn clean dependency:resolve
 ```
-  Open in ecplise src/main/resources then configure MySQL credinitials
-  Run the project in the tomcat server
