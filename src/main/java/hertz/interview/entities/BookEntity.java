package hertz.interview.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private String author;
    //each book can have more than one category
    @ManyToMany( )
    List<CategoryEntity> categoryEntityList;
    @ManyToOne()
    private UserEntity userEntity;
    LocalDate date;


}
