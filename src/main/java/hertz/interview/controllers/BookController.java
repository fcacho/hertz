package hertz.interview.controllers;

import hertz.interview.controllers.mappers.Book;
import hertz.interview.entities.BookEntity;
import hertz.interview.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // TODO add @Valid expresions to validate the message body and category exits.
    @PostMapping("/createBook")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBook(@RequestBody Book book)  {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @GetMapping("/bookList")
    public List<BookEntity> getUserBooks() {
        return bookService.getBookList();

    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long id)
    {
        if (bookService.getBook(id).isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

}
