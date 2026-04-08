package com.geoclinic.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private String user;
    private String comment;
    private LocalDateTime dateTime;
    private int clinicId;
    private CommentStatus status;

}
