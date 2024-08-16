# StudentManagementSystem

Java Spring Boot + Html + Css + Javascript

Java: [/src/main/java/com/example/demo](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/java/com/example/demo) <br />
Html: [/src/main/resources/templates](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/resources/templates) <br />
Css + Js: [/src/main/resources/static](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/resources/static) <br />
Python: [/pythonScripts](https://github.com/Andreii1414/StudentManagementSystem/tree/main/pythonScripts) <br />
Schema baza de date: [/database.png](https://github.com/Andreii1414/StudentManagementSystem/blob/main/database.png) <br />
Videoclip de prezentare:

Optiuni:
	- Register
	- Login (admin/user)
	- User panel (top 10 elevi, toti dirigintii, top 5 materii ca nr de absente,
		 elevi cu bursa sociala+merit pe semestrul 2)
	- Admin panel (adaugare elev, mutare elev la alta clasa, motivare absenta)

Register [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/RegistrationController.java):
	- parola => hashed, validari, erori
	- request-ul e facut in [forms.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/forms.js)

Login [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/LoginController.java):
	- /login
	- JWT: JwtUtils => generare token (valabil 24h), validare token, extragere email din token
	       JwtFilter => eroare 401 daca tokenul e invalid
	       FilterConfig => specificarea endpoint-urilor care vor fi verificate de filtru
         [/src/main/java/com/example/demo/Jwt](https://github.com/Andreii1414/StudentManagementSystem/tree/main/src/main/java/com/example/demo/Jwt)
	- salvare token => cookie

Admin check [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/AdminCheckController.java):
	- /isAdmin
	- extrage email-ul din tokenul din cookie si verifica daca userul logat este admin
	- protejeaza toate rutele din admin panel (adaugare elev, etc)

Database [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Database.java):
	- H2 database (in-memory database) => compatibilitate SQL
	- toate operatiile (insert, select, etc)
	- schema.sql [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/schema.sql) => crearea tabelelor
	- data.sql [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/data.sql) => insert-uri pt popularea bazei de date
	- scripturile din [link](https://github.com/Andreii1414/StudentManagementSystem/tree/main/pythonScripts) => popularea bazei de date
	- diagrama baza de date [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/database.png)

User panel [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/ReadController.java):
	- select-uri in baza de date
	- request-uri [readActions.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/readActions.js)

Admin panel [link](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/java/com/example/demo/Controllers/UpdateController.java): 
	- insert, update, delete in baza de date
	- vizibil doar pt admini
	- protejat de token-ul jwt prin care se verifica inainte de fiecare request
	ca userul este admin
	- [action5.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/action5.js),[action6.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/action6.js), [action7.js](https://github.com/Andreii1414/StudentManagementSystem/blob/main/src/main/resources/static/action5.js)


Instalare si utilizare:
	- instalarea unui jdk: https://www.oracle.com/java/technologies/downloads/
	- comanda: **mvnw spring-boot:run** => in directorul proiectului
	- localhost:8888/login
	- cont admin => email: admin@gmail.com, parola: admin
	- cont user => email: user@gmail.com, parola: user
	- localhost:8888/h2-console => pentru consola bazei de date; daca este nevoie: 
	JDBC URL: jdbc:h2:mem:testdb


