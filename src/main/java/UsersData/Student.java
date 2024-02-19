package UsersData;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "students")
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int year;

    private String classType;

    @Column(name = "username_tg")
    private String tgUsername;

}
