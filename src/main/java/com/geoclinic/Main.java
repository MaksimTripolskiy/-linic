package com.geoclinic;

import com.geoclinic.dto.RegistrationRequest;
import com.geoclinic.model.Clinic;
import com.geoclinic.model.Comment;
import com.geoclinic.service.ClinicService;
import com.geoclinic.service.CommentService;
import com.geoclinic.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);



        RegistrationRequest req = new RegistrationRequest();
        req.setUsername("admin");
        req.setPassword("pass");

        UserService service = context.getBean(UserService.class);


        service.registerAdminUser(req);

        //---------

//        ClinicService clinicService = context.getBean(ClinicService.class);
//
//        Clinic clinic = new Clinic();
//        clinic.setId(777L);
//        clinic.setAddress("caddress");
//        clinic.setName("cname");
//        clinic.setLatitude(44.444444);
//        clinic.setLongitude(44.444444);
//        clinic.setPhone("328923789423");
//        clinic.setType("Dental");
//        clinic.setWorkHours("10am-5pm test");
//        clinicService.createClinic(clinic);
//
//        //---------
//
//        CommentService commentService = context.getBean(CommentService.class);
//
//        Comment comment = new Comment();
//        comment.setText("this is a comment");
//        comment.setAuthor("Me Author");
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setStatus("PENDING");
//        comment.setClinic(clinic);
//
//        commentService.addComment(comment);
;
        //---------



//        String apiKey = "eyJvcmciOiI1YjNjZTM1OTc4NTExMTAwMDFjZjYyNDgiLCJpZCI6IjkyN2Q3OTA0ZTA5NDQ4OTVhZjRiNTI5MmM4Njc5NTkwIiwiaCI6Im11cm11cjY0In0";

//        RouteService routeService = new RouteService();
//
//        try {
//            System.out.println(routeService.getRoute(55.751849391735284, 37.60594367980958, 55.76189525593947, 37.61817455291749));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
}
