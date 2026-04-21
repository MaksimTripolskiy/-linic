package com.geoclinic;

import com.geoclinic.dto.RegistrationRequest;
import com.geoclinic.model.Comment;
import com.geoclinic.model.User;
import com.geoclinic.service.CommentService;
import com.geoclinic.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);


//        User user = new User("user2","pass2","ROLE_USER");

        RegistrationRequest req = new RegistrationRequest();
        req.setUsername("user2");
        req.setPassword("pass2");

        UserService service = context.getBean(UserService.class);


        service.registerNewUser(req);



        //---------

        CommentService commentService = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("this is a comment");

        commentService.addComment(comment);
    }
}
