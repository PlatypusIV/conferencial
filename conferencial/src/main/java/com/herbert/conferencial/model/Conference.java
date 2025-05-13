package com.herbert.conferencial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity(name = "Conference")
@Table(name = "conference")
@NoArgsConstructor
@AllArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;

    @Setter
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Setter
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Setter
    @JoinColumn(name= "id", nullable = true)
    private int roomId;

    @Setter
    @Column(name = "is_canceled")
    private boolean isCanceled;
}
