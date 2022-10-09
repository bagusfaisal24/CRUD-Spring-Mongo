package com.example.springmongo.controller;

import com.example.springmongo.domain.form.NotificationForm;
import com.example.springmongo.service.NotificationSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationSvc notificationSvc;

    @PostMapping
    public ResponseEntity<Object> getNotif(@RequestBody NotificationForm form){
        return notificationSvc.getNotif(form);
    }
}
