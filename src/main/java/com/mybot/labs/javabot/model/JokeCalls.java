package com.mybot.labs.javabot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jokeCalls")
public class JokeCalls {

    @Id
    @GeneratedValue(generator = "called_joke_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "called_joke_id_seq", sequenceName = "called_joke_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "called_joke")
    private Joke calledJoke;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;


}
