package hertz.interview.controllers.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Book {

    private String name;
    private String author;
    private List<Long> categoryList;

}
