package com.geoclinic.controller;

import com.geoclinic.model.Comment;
import com.geoclinic.model.CommentStatus;
import com.geoclinic.service.CommentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/createPendingComment")
    public String createPendingComment(@ModelAttribute Comment comment, Model model) {
        try {
            // Сохранить клинику
            comment.setStatus(CommentStatus.PENDING);
            commentService.addComment(comment);
            model.addAttribute("message", "Comment created successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "clinic-page"; // вернуть ту же страницу с сообщением
    }
}
