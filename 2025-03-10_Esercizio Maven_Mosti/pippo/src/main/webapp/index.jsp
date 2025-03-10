<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Benvenuto</title>
</head>
<body>
    <h1>Benvenuto Utente Curioso, questo è la mia prima JSP.</h1>
    <p>Oggi è il giorno <%= new java.util.Date() %></p>
    <p>Autore: <a href="author.jsp">Vai alla pagina dell'autore</a></p>
</body>
</html>
