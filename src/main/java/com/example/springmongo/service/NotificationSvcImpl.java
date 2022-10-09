package com.example.springmongo.service;

import com.example.springmongo.constant.AppConstant;
import com.example.springmongo.domain.ResponseUtil;
import com.example.springmongo.domain.form.NotificationForm;
import com.example.springmongo.domain.sql.Notification;
import com.example.springmongo.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationSvcImpl implements NotificationSvc{

    private final NotificationRepository repository;

    @Autowired
    public NotificationSvcImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Object> getNotif(NotificationForm form) {
        try {
            List<Notification> products = repository.findAll();
            if (form != null &&  form.getFilter() != null && form.getFilter().equals(NotificationForm.READ_)){
                products = products.stream()
                        .filter(Notification::is_read)
                        .collect(Collectors.toList());
            } else if (form != null &&  form.getFilter() != null && form.getFilter().equals(NotificationForm.UNREAD_)){
                products = products.stream()
                        .filter(v->!v.is_read())
                        .collect(Collectors.toList());
            }

            return ResponseUtil.build(AppConstant.SUCCESS, products, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.build(AppConstant.UNKNOWN_ERROR, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
