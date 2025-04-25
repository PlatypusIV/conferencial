package com.herbert.conferencial.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "conference")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    private int location;

    protected Conference() {
    }

    public Conference(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }
}
