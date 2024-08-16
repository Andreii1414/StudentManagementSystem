# StudentManagementSystem

## Java Spring Boot + Html + Css + Javascript

* Java: [/src/main/java/com/example/demo](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/java/com/example/demo) 
* Html: [/src/main/resources/templates](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/resources/templates) 
* Css + Js: [/src/main/resources/static](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/resources/static) 
* Python: [/pythonScripts](https://github.com/Andreii1414/StudentManagementSystem/tree/main/pythonScripts) 
* Schema baza de date: [/database.png](https://github.com/Andreii1414/StudentManagementSystem/blob/main/database.png)
* Videoclip de prezentare:

Optiuni: 
	- Register 
	- Login (admin/user) <br />
	- User panel (top 10 elevi, toti dirigintii, top 5 materii ca nr de absente, elevi cu bursa sociala+merit pe semestrul 2) <br />
	- Admin panel (adaugare elev, mutare elev la alta clasa, motivare absenta) 

Register [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/RegistrationController.java): <br />
	- parola => hashed, validari, erori <br />
	- request-ul e facut in [forms.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/forms.js) 

Login [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/LoginController.java): <br />
	- /login <br />
	- JWT: JwtUtils => generare token (valabil 24h), validare token, extragere email din token <br />
	       JwtFilter => eroare 401 daca tokenul e invalid <br />
	       FilterConfig => specificarea endpoint-urilor care vor fi verificate de filtru <br />
         [/src/main/java/com/example/demo/Jwt](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/java/com/example/demo/Jwt) <br />
	- salvare token => cookie 

Admin check [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/AdminCheckController.java): <br />
	- /isAdmin <br />
	- extrage email-ul din tokenul din cookie si verifica daca userul logat este admin <br />
	- protejeaza toate rutele din admin panel (adaugare elev, etc)

Database [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Database.java): <br />
	- H2 database (in-memory database) => compatibilitate SQL <br />
	- toate operatiile (insert, select, etc) <br />
	- schema.sql [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/schema.sql) => crearea tabelelor <br />
	- data.sql [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/data.sql) => insert-uri pt popularea bazei de date <br />
	- scripturile din [link](https://github.com/Andreii1414/StudentManagementSystem/tree/main/pythonScripts) => popularea bazei de date <br />
	- diagrama baza de date [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/database.png) 

User panel [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/ReadController.java): <br />
	- select-uri in baza de date <br />
	- request-uri [readActions.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/readActions.js)

Admin panel [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/UpdateController.java): <br />
	- insert, update, delete in baza de date <br />
	- vizibil doar pt admini <br />
	- protejat de token-ul jwt prin care se verifica inainte de fiecare request ca userul este admin <br />
	- [action5.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/action5.js),[action6.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/action6.js), [action7.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/action5.js)


Instalare si utilizare: <br />
	- instalarea unui jdk: https://www.oracle.com/java/technologies/downloads/ <br />
	- comanda: **mvnw spring-boot:run** => in directorul proiectului <br />
	- localhost:8888/login <br />
	- cont admin => email: admin@gmail.com, parola: admin <br />
	- cont user => email: user@gmail.com, parola: user <br />
	- localhost:8888/h2-console => pentru consola bazei de date; daca este nevoie: JDBC URL: jdbc:h2:mem:testdb


