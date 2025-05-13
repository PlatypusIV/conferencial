package com.herbert.conferencial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity(name= "Participant")
@Table(name = "participant")
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(name = "full_name")
    private String fullName;

    @Setter
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Setter
    @Column(name = "conference_id")
    private int conferenceId;
}
