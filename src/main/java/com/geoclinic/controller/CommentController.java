
package com.geoclinic.controller;

import com.geoclinic.model.Clinic;
import com.geoclinic.model.Comment;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class CommentController {

    private CommentService commentService;
    private ClinicService clinicService;

    public CommentController(CommentService commentService, ClinicService clinicService) {
        this.commentService = commentService;
        this.clinicService = clinicService;
    }

//    @PostMapping("/leaveComment")
//    public String createPendingComment(@ModelAttribute Comment comment, Model model) {
//        try {
//            // Сохранить клинику
//            comment.setStatus("PENDING");  //   todo ap 3/10 fixme
//            comment.setCreatedAt(LocalDateTime.now());
//            commentService.addComment(comment);
//            model.addAttribute("message", "Comment created successfully!");
//        } catch (Exception e) {
//            model.addAttribute("error", "Error: " + e.getMessage());
//        }
//        return "redirect:/getClinic?id=1"; // todo ap 8/10 change id to variable
//    }

    @PostMapping("/leaveComment")
    public String leaveComment(@ModelAttribute Comment comment,
                               @RequestParam(name = "clinicId") Long clinicId, Model model) {

        Clinic clinic = clinicService.getClinicById(clinicId);

        comment.setClinic(clinic);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setStatus("PENDING");
        model.addAttribute("message", "Comment created successfully!");

        commentService.addComment(comment);

        return "redirect:/getClinic?id=" + clinicId;
    }

    @PostMapping("/comments/{id}/moderate")
    public String moderateComment(@PathVariable("id") Long id,
                                  @RequestParam("action") String action) {
        if ("approve".equals(action)) {
            commentService.approve(id);
        } else if ("reject".equals(action)) {
            commentService.reject(id);
        }
        return "redirect:/getAllCommentsAsAdmin";
    }

    @GetMapping("/getAllCommentsAsAdmin")
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.getAllComments();

        model.addAttribute("comments", comments);

        return "pending-comments-2";
    }
}