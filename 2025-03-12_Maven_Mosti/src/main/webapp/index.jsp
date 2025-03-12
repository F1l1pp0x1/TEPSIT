<html>
	<%@ page import="java.util.Date" %>
	<%@ page import="java.text.SimpleDateFormat" %>
	<%
	    Date today = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    String date = formatter.format(today);
	%>
	
	<body>
		<p>	
			Benvenuto Utente Curioso, questo e' la mia prima JSP.
			</br>Oggi e' il giorno <%= date %>
			</br><a href="author.jsp">Autore</a>
		</p>
	</body>
</html>
