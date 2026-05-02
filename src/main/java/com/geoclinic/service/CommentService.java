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

    public List<Comment> getPendingComments() {
        return null;    // todo
//        return commentDAO.findAllPendingComments();
    }

    public void addComment(Comment comment) {       // todo status set where?
        commentDAO.save(comment);
    }
}

