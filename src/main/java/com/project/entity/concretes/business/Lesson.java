package com.project.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    private String lessonName;

    private Integer creditScore;

    private Boolean isCompulsory;

    @JsonIgnore
    //lessons degiskeni yoksa hata verir
    @ManyToMany(mappedBy = "lessons", cascade = CascadeType.REMOVE)
    private Set<LessonProgram> lessonPrograms;
    //Ayni degerden bir tane daha olmasin diye List degil Set diye ayarladik
}