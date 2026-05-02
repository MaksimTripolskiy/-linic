
package com.geoclinic.controller;

import com.geoclinic.model.Comment;
import com.geoclinic.model.CommentStatus;
import com.geoclinic.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/leaveComment")
    public String createPendingComment(@ModelAttribute Comment comment, Model model) {
        try {
            // Сохранить клинику
            comment.setStatus(CommentStatus.PENDING);  //   fixme
            comment.setDateTime(LocalDateTime.now());
            commentService.addComment(comment);
            model.addAttribute("message", "Comment created successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "clinic-page"; // вернуть ту же страницу с сообщением
    }
}