package com.herbert.conferencial.model;

import jakarta.annotation.Nullable;
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
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @JoinColumn(name= "id", nullable = true)
    @Getter @Setter
    private int roomId;

    @Getter @Setter
    @Column(name = "is_canceled")
    private boolean isCanceled;

    protected Conference() {
    }

    public Conference(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }
}
