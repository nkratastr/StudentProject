package com.project.contactMessage.controller;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import com.project.contactMessage.repository.ContactMessageRepository;
import com.project.contactMessage.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController //Controllerin ozellestirilmis hali. Mvc de controller annotationu ama su an restful api
// kullanacagimiz ve json dosyasiyla caliscaz. Restful api controlluru diyebiliriz.
@RequestMapping("/contactMessages") //http;//localhost:8080/contactMessages
@RequiredArgsConstructor
//eskiden servis kati bir interface uzerinden yapilir ve dinjection interface ile yapilirdi
// su an boyle bir seye ihtiyac kalmadi. interface olsa da bu hata degil. Sadece bir tane concreet classimiz var.

public class ContactMessageController {

    private  final ContactMessageService contactMessageService;

    @PostMapping("/save") //....contactMessages/save // http://localhost:8080/contactMessages/save   + POST + JSON
    public ResponseEntity<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest){

        //Clienttan gelen requestteki DTO ile Clienta giden DTO ayni
        //degildir. responseda Id bilgisi de vardir. Bu yuzden entity basi 2 DTO olmalidir.
        //ResponseDTO ve RequestDTO olmalidir

        return ContactMessageService.save(contactMessageRequest);

    }


}
