package com.herbert.conferencial.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String location;

    @Column(name = "max_seats")
    @Getter @Setter
    private int maxSeats;
}
