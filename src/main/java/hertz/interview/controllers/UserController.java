package hertz.interview.controllers;

import hertz.interview.controllers.mappers.BookVM;
import hertz.interview.entities.BookEntity;
import hertz.interview.entities.UserEntity;
import hertz.interview.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUserBooks")
    public ResponseEntity<List<BookVM>> getUserBooks(@RequestParam() Long userId) {
        // the book entity has to be mapped to a simple object to be able to return it correctly
        Optional<UserEntity> user = userService.getUser(userId);
        return user.map(userEntity -> new ResponseEntity<>(mapToBookVM(userEntity.getBookEntityList()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // TODO change this mapping for the spring standard @Mapper(componentModel = "spring").
    List<BookVM> mapToBookVM(List<BookEntity> list) {
        return list.stream().map(bookEntity -> new BookVM(bookEntity.getName(), bookEntity.getAuthor())).collect(Collectors.toList());

    }

    // TODO add @Valid expressions to validate user and bookId exist like @ValidUserId
    // TODO improve return message with json format and codes.
    @PutMapping("/loanBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> loanBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.loanBook(userId, bookId), ACCEPTED);
    }

    // TODO add @Valid expresions to validate user and bookId exist like @ValidUserId
    // TODO improve return message with json format and codes.
    @PutMapping("/returnBook")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.returnBook(userId, bookId), ACCEPTED);
    }

}
