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
    @Column(name="commenter")
    private String commenter;
    @Column(name="text")
    private String text;
    @Column(name="datetime")
    private LocalDateTime dateTime;
    @Column(name="clinic_id")
    private int clinicId;
    @Column(name="status")
    private CommentStatus status;
    @Column(name="rating")
    private int rating;

    public Comment() {}

}