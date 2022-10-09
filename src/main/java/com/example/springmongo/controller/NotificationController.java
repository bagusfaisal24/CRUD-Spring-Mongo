package com.example.springmongo.controller;

import com.example.springmongo.domain.form.NotificationForm;
import com.example.springmongo.service.NotificationSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationSvc notificationSvc;

    @GetMapping
    public ResponseEntity<Object> getNotif(@RequestBody NotificationForm form){
        return notificationSvc.getNotif(form);
    }
}
