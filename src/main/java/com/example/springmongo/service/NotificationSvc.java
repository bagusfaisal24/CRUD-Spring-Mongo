package com.example.springmongo.service;

import com.example.springmongo.domain.form.NotificationForm;
import org.springframework.http.ResponseEntity;

public interface NotificationSvc {

    ResponseEntity<Object> getNotif(NotificationForm form);
}
