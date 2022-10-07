package com.example.springmongo.queue;

import com.example.springmongo.domain.DtoProduct;
import com.example.springmongo.service.PubSubAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductReceiver {

    private static Logger LOGGER = LogManager.getLogger(ProductReceiver.class);

    private final PubSubAdapter pubSubAdapter;

    @Autowired
    public ProductReceiver(PubSubAdapter pubSubAdapter) {
        this.pubSubAdapter = pubSubAdapter;
    }

    @RabbitListener(queues = "product.queue")
    public void productListener(String message){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            DtoProduct dtoProduct = objectMapper.readValue(message, DtoProduct.class);
            pubSubAdapter.createNew(dtoProduct);
            LOGGER.info("success get message info");
        }catch (Exception e){
            LOGGER.error("error get message queue " + e.getMessage());
        }
    }
}
