package hertz.interview.controllers.mappers;


import hertz.interview.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Book {

    private String name;
    private String author;
    private List<Long> categoryList;
    UserEntity userEntity;

}
