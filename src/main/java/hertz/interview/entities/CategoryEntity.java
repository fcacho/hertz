package hertz.interview.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
}
