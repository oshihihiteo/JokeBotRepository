package com.mybot.labs.javabot.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jokes")
@Table(name = "jokes")
public class Joke {

    @Id
    @GeneratedValue(generator = "joke_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "joke_id_seq", sequenceName = "joke_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "created")
    private Date created = new java.sql.Date(System.currentTimeMillis());

    @Column(name = "updated")
    private Date updated = new java.sql.Date(System.currentTimeMillis());

    @OneToMany(mappedBy = "calledJoke", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<JokeCalls> calls;

}
