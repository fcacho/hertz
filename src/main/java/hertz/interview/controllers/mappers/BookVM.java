package hertz.interview.controllers.mappers;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookVM {
    private String name;
    private String author;
}
