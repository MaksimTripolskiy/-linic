package com.geoclinic.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name="comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="author")
    private String author;
    @Column(name="text")
    private String text;
    @Column(name="created_at")
    private LocalDateTime createdAt;
//    @Column(name="clinic_id")
//    private int clinicId;
    @Column(name="status")
    private String status;
    @Column(name="rating")
    private int rating;

//    @ManyToOne
//    @JoinColumn(name = "clinic_id")         // todo ap 5/10 cascade
//    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    public Comment() {}

}