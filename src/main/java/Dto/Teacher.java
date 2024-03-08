package Dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "teachers")
@Entity
public class Teacher {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "cabinet")
    private int cabinet;




}
