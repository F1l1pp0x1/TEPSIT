<!DOCTYPE html>
<html>
	<head>
	    <title>Client</title>
	    <script>
	        function getBooks() {
				
	            fetch('http://localhost:8080/api/books/')  // URL dell'API
	                .then(response => response.json())  // Converti la risposta in formato JSON
	                .then(data => {
	                    // Mostra i dati ricevuti nella pagina
	                    let output = '<h2>Lista dei Libri:</h2>';
	                    data.forEach(book => {
	                        output += `
								Titolo: ${book.title}
								</br>Autori: ${book.authors}
								</br>Lingua: ${book.language}</br></br>`;
	                    });
						
	                    document.getElementById("bookList").innerHTML = output;
	                })
	                .catch(error => {
	                    console.error('Errore nella richiesta:', error);
	                    document.getElementById("bookList").innerHTML = "<p>Si è verificato un errore.</p>";
	                });
	        }
	    </script>
	</head>
	<body>
	
	    <h1>Invia una richiesta GET per visualizzare i libri</h1>
	
	    <!-- Bottone per inviare la richiesta GET -->
	    <button onclick="getBooks()">Mostra Libri</button>
	
	    <!-- Dove verranno mostrati i libri -->
	    <div id="bookList"></div>
	
	</body>
</html>
