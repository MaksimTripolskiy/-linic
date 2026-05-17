package com.geoclinic.service;

import com.geoclinic.model.Comment;
import com.geoclinic.repository.CommentDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> getCommentsByClinicId(long id) {
        return commentDAO.findCommentsByClinicId(id);
    }

    public List<Comment> getApprovedCommentsByClinicId(long id) {
        return commentDAO.findApprovedCommentsByClinicId(id);
    }

//    public List<Comment> getPendingComments() {
//        return commentDAO.findAllPendingComments();
//    }

    public void addComment(Comment comment) {       // todo status set where?
        commentDAO.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentDAO.findAll();
    }

    public void approve(Long id) {
        Comment comment = commentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден с id: " + id));  // todo ap 3/10
        comment.setStatus("APPROVED");
        commentDAO.save(comment);
    }

    public void reject(Long id) {
        Comment comment = commentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден с id: " + id));
        comment.setStatus("REJECTED");
        commentDAO.save(comment);
    }
}

