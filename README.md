# hertz Exam

This library has been developed using intellij and I use mvn to build it.
It runs on a spring boot server and the data is saved in a memory database. 
To run it, open the project in your intellij application.
Main application: hertz.interview.InterviewApplication. Use the application.properties to see the database logs.
Database: com.h2database.
lombok plug in is required to build the application
Two users with ids, one and two are created when the application starts.
Two categories with ids, one and two are created when the application starts.

End points:
Book:
- Create a book
    POST: localhost:8080/createBook
      body:   {
            "name": "book1",
            "categoryList": [
            "1",
            "2"
            ],
                "author": "author1"
            }
- Delete a book
      DELETE: localhost:8080/deleteBook/1

- List of books
      GET: localhost:8080/bookList

USER
- Loan a book
       PUT: localhost:8080/loanBook?userId=1&bookId=1
- Return a book
       PUT: localhost:8080/returnBook?userId=1&bookId=1
- Get user books
       GET: localhost:8080/getUserBooks?userId=1

To do: 
- add transactions management
- Add end points parameters validations.
- Improve returns messages returns and errors.
- Add unit testing and integration testing.
