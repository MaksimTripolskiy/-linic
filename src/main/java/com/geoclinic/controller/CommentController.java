package com.geoclinic.controller;

import com.geoclinic.model.Comment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {

    private CommentService commentService;


    @PostMapping
    public void addComment(@RequestBody Comment comment) {

    }
}
