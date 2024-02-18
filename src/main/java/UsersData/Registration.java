package UsersData;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "students")
@Entity
public class Registration {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int year;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private GroupType groupType;


}
