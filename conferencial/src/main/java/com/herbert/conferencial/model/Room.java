package com.herbert.conferencial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;

    @Setter
    private String location;

    @Column(name = "max_seats")
    @Setter
    private int maxSeats;
}
