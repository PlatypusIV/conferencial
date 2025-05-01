package com.herbert.conferencial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "conference")
@Table(name = "conference")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Getter @Setter
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Getter @Setter
    @JoinColumn(name= "id", nullable = true)
    private int roomId;

    @Getter @Setter
    @Column(name = "is_canceled")
    private boolean isCanceled;

    public Conference() {
    }

    public Conference(String name, LocalDateTime startTime, LocalDateTime endTime, int roomId, boolean isCanceled) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomId = roomId;
        this.isCanceled = isCanceled;
    }
}
