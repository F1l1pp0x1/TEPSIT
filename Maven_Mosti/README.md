# Progetto Maven - Mosti Filippo 5°AI

Questo progetto è stato creato seguendo le lezioni della guida ( https://www.html.it/guide/restful-web-services-in-java-con-jersey/) per imparare a sviluppare applicazioni web con Java e JSP. Il progetto include una pagina JSP (`index.jsp`) che visualizza un messaggio di benvenuto, un link ad una pagina che mostra le informazioni dell'autore, e una API REST che risponde a una richiesta HTTP.

## Descrizione del Progetto

Il progetto **HelloWeb** ha le seguenti funzionalità principali:

1. **Pagina di Benvenuto (index.jsp)**:
   
   - Quando un utente visita la pagina principale (`index.jsp`), viene visualizzato il messaggio:
     
     ```
     Home
     Benvenuto Utente Curioso, questo e' la mia prima JSP.
     Oggi e' il giorno <data>
     Autore<link>
     ```
     - `<data>` è la data corrente (data di visita).
     - `<link>` è un collegamento a una pagina chiamata `author.jsp` che mostra i dettagli dell'autore.

2. **Pagina dell'Autore (author.jsp)**:
   
   - La pagina `author.jsp` visualizza le informazioni dell'autore, incluse:
     - Nome
     - Cognome
     - Classe dell'autore

3. **API REST**:
   
   - È presente una API REST che risponde alla richiesta `/api/test/eleonora` con un messaggio predefinito.
   - È presente una API REST che risponde alla richiesta `/api/books/` con un oggetto json contenente la lista di tutti i libri presenti nel server
   - È presente una API REST che risponde alla richiesta `/api/books/{id}` con un oggetto json contenente il libro con l'id medesimo a quello inserito nell'URL
   - È presente una API REST che risponde alla richiesta `/api/books/{id}/authors` con un oggetto json contenente gli autori del libro con l'id medesimo a quello inserito nell'URL

# 


