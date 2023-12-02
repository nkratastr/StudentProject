package com.project.contactMessage.mapper;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component// injection yapmamizi saglar
//StudentManagementApplication ayni seviyede oldugu icin @componentlari tariyor
public class ContactMessageMapper {
    // Request --> POJO

    public ContactMessage requestToContactMessage(ContactMessageRequest contactMessageRequest){
            // service katmanini yardimci metodlar ile daha okunakli yapmaya calistik.
            //builder yapisi cok kullanilan bir  yapidir. Bu bir design patterndir
        return ContactMessage.builder()
                .name(contactMessageRequest.getName())
                .subject(contactMessageRequest.getSubject())
                .message(contactMessageRequest.getMessage())
                .email(contactMessageRequest.getEmail())
                .dateTime(LocalDateTime.now()) //simdini zamanini kullandik
                .build();

    }

    public ContactMessageResponse contactMessageToResponse(ContactMessage contactMessage){
       //builder kullanarak tum fieldlari degil ihtiyacim olanlari setliyorum
        //json dosyasi bu sekilde olusuyor
        // datetime icin contactMessage.getDateTime
        return  ContactMessageResponse.builder()
                .name(contactMessage.getName())
                .subject(contactMessage.getSubject())
                .message(contactMessage.getMessage())
                .email(contactMessage.getEmail())
                .dateTime(LocalDateTime.now()) //bu bilgi db de yok
                .build();


    }


}
