package hertz.interview.repositories;

import hertz.interview.entities.BookEntity;
import hertz.interview.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
