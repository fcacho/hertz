package hertz.interview.repositories;

import hertz.interview.entities.BookEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
