package hertz.interview.services;

import hertz.interview.controllers.mappers.Book;
import hertz.interview.entities.BookEntity;
import hertz.interview.entities.CategoryEntity;
import hertz.interview.repositories.BookRepository;
import hertz.interview.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;


    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        // add two categories to the database so a book can be linked to these categories
        categoryRepository.saveAll(Arrays.asList(new CategoryEntity(1L, "Category1"), new CategoryEntity(2L, "Category2")));
    }

    // save books and relationship with the categories
    public BookEntity save(Book book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        bookEntity.setAuthor(book.getAuthor());
        List<CategoryEntity> categoryEntityList = categoryRepository.findAllById(book.getCategoryList());
        bookRepository.save(bookEntity);
        bookEntity.setCategoryEntityList(categoryEntityList);
        return bookRepository.save(bookEntity);

    }

    public List<BookEntity> getBookList() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getBook(Long id) {
        return bookRepository.findById(id);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
