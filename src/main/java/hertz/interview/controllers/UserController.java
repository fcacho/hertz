package hertz.interview.controllers;

import hertz.interview.entities.BookEntity;
import hertz.interview.entities.UserEntity;
import hertz.interview.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserBooks")
    public ResponseEntity<List<BookEntity>> getUserBooks(@RequestParam() Long userId) {
        Optional<UserEntity> user = userService.getUser(userId);
        return user.isPresent() ? new ResponseEntity<>(user.get().getBookEntityList(), HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/loanBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> loanBook(@RequestParam Long userId, @RequestParam Long bookId) {
         return new ResponseEntity<>(userService.loanBook(userId, bookId), ACCEPTED);
    }

    @PutMapping("/returnBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.returnBook(userId, bookId), ACCEPTED);
    }

}
