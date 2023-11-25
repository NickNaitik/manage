package com.nick.product.manage.Util;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public void sendMessage(String phoneNumber, String messageBody) {
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber("+18145177269"),
                        messageBody)
                .create();

        // Optionally, you can check the message status or handle any errors
        System.out.println(message.getSid());
    }

        public void sendWhatsappMessage (String phoneNumber, String messageBody){
        try {
            Message message = Message.creator(
                            new PhoneNumber("whatsapp:" + phoneNumber),
                            new PhoneNumber("whatsapp:+14155238886"),
                            messageBody)
                    .create();

            // Optionally, you can check the message status or handle any errors
            System.out.println(message.getSid());
        } catch (Exception ex){
            Thread.interrupted();
        }
    }
}
