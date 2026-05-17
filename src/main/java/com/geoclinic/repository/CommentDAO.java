package com.geoclinic.repository;

import com.geoclinic.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {


    @Query("SELECT c FROM Comment c WHERE c.clinic.id = :clinicId")
    List<Comment> findCommentsByClinicId(@Param("clinicId") Long clinicId);

    @Query("SELECT c FROM Comment c WHERE  c.clinic.id = :clinicId AND c.status = 'APPROVED'")
    List<Comment> findApprovedCommentsByClinicId(@Param("clinicId") Long clinicId);

//    @Query("SELECT c FROM Comment c WHERE c.status = 'PENDING' ORDER BY c.createdAt ASC")
//    List<Comment> findAllPendingComments();
}
