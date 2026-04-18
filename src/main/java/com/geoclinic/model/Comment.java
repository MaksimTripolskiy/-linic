package com.geoclinic.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Table(name="comments")
@Entity
public class Comment {

    private String user;
    private String comment;
    private LocalDateTime dateTime;
    private int clinicId;
    private CommentStatus status;
    private int rating;

    public Comment() {}

}
