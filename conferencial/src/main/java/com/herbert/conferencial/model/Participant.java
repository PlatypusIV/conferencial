package com.herbert.conferencial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name= "Participant")
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    @Column(name = "full_name")
    private String fullName;

    @Getter @Setter
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Getter @Setter
    @Column(name = "conference_id")
    private int conferenceId;
}
