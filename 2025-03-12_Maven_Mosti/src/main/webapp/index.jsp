<!--
	Pagina jsp (simile a php ma usato con server jetty e tomcat)
	della home del progetto
	
	si raggiunge scrivendo localhost:8080
-->
<html>
	<!--
		Importare le librerie necessarie
	-->
	<%@ page import="java.util.Date" %> <!--Per mostrare la data di oggi-->
	<%@ page import="java.text.SimpleDateFormat" %> <!--Per mostrare la data in un formato semplificato-->
	<%
		//Data di oggi
	    Date today = new Date();
		
		//Formato con cui si vuole vedere la data
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    
		String date = formatter.format(today);
	%>
	
	<body>
		<h2>Home</h2>
		<p>	
			Benvenuto Utente Curioso, questo e' la mia prima JSP.
			</br>Oggi e' il giorno <%= date %>
			</br><a href="author.jsp">Autore</a>
		</p>
	</body>
	
</html>
