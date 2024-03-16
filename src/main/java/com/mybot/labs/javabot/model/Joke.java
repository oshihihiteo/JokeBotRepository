package com.mybot.labs.javabot.model;

import java.sql.Date;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "created")
    private Date created = new java.sql.Date(System.currentTimeMillis());

    @Column(name = "updated")
    private Date updated = new java.sql.Date(System.currentTimeMillis());

}
