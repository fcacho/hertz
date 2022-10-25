package hertz.interview.services;

import hertz.interview.entities.BookEntity;
import hertz.interview.entities.UserEntity;
import hertz.interview.repositories.BookRepository;
import hertz.interview.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {

    private final BookService bookService;
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    public UserService(BookService bookService, UserRepository userRepository, BookRepository bookRepository) {
        this.bookService = bookService;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        userRepository.saveAll(Arrays.asList(new UserEntity(1L, "User1",new ArrayList<BookEntity>(), false),
                new UserEntity(1L, "User2",new ArrayList<BookEntity>(), false)));

    }

    public Optional <UserEntity> getUser(Long userId) {
        return userRepository.findById(userId);
    }

 public String returnBook(Long userId, Long bookId) {
     Optional<BookEntity> book = Optional.ofNullable(bookService.getBook(bookId).orElseThrow(EntityNotFoundException::new));
     Optional<UserEntity> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
     return book.get().getDate() == null                   ? "The book is not loaned"     :
            !book.get().getUserEntity().equals(user.get()) ? "The book has been loaned to other user": returnBook(user.get(), book.get());
    }

    private String returnBook(UserEntity user, BookEntity book) {
        if (user.getBlocked() && user.getBookEntityList().size() == 1) {
            user.setBlocked(false);
            userRepository.save(user);
        }
        book.setUserEntity(null);
        book.setDate(null);
        bookRepository.save(book);
        return "The book has been returned";

    }

  //  - maximum number of books loaned at any time is 3 per user
  // if a member has any outstanding loaned books, they cannot loan any more until all books returned.
  // there is only 1 copy of each book

    public String  loanBook(Long userId, Long bookId) {
        Optional<BookEntity> book = Optional.ofNullable(bookService.getBook(bookId).orElseThrow(EntityNotFoundException::new));
        Optional<UserEntity> user = Optional.ofNullable(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
        return  user.get().getBookEntityList().size() > 2 ? "This user can't loan more books. Maximum allowed is 3" :
                book.get().getDate() != null  ? "The book is not available": loanBook(user.get(), book.get());
 }
 private String loanBook(UserEntity user, BookEntity book) {
        LocalDate now = LocalDate.now();
        Boolean blockUser = user.getBookEntityList().stream().anyMatch(b -> b.getDate().isBefore(now.minusDays(7)));
        if (user.getBlocked() || blockUser) {
            user.setBlocked(true);
            userRepository.save(user);
            return "The user is blocked";
        }
        book.setUserEntity(user);
        book.setDate(LocalDate.now());
        bookRepository.save(book);
        return "the book has been loaned";
 }
}
