package com.project.contactMessage.controller;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import com.project.contactMessage.repository.ContactMessageRepository;
import com.project.contactMessage.service.ContactMessageService;
import com.project.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //Controllerin ozellestirilmis hali. Mvc de controller annotationu ama su an restful api
// kullanacagimiz ve json dosyasiyla caliscaz. Restful api controlluru diyebiliriz.
@RequestMapping("/contactMessages") //http;//localhost:8080/contactMessages
@RequiredArgsConstructor
//eskiden servis kati bir interface uzerinden yapilir ve dinjection interface ile yapilirdi
// su an boyle bir seye ihtiyac kalmadi. interface olsa da bu hata degil. Sadece bir tane concreet classimiz var.

public class ContactMessageController {

    private  final ContactMessageService contactMessageService;

    @PostMapping("/save") //....contactMessages/save // http://localhost:8080/contactMessages/save   + POST + JSON
    public ResponseMessage<ContactMessageResponse> save(@RequestBody @Valid ContactMessageRequest contactMessageRequest){

        //Clienttan gelen requestteki DTO ile Clienta giden DTO ayni
        //degildir. responseda Id bilgisi de vardir. Bu yuzden entity basi 2 DTO olmalidir.
        //ResponseDTO ve RequestDTO olmalidir

        return contactMessageService.save(contactMessageRequest);
    }

    @GetMapping("/getAll") // http://localhost:8080/contactMessages/getAll + GET
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public Page<ContactMessageResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){
        return contactMessageService.getAll(page,size,sort,type);
    }

    @GetMapping("/searchByEmail") // http://localhost:8080/contactMessages/searchByEmail + GET
    //   /searchbyemail?jfldsjf@dkjfd.com gibi bir parametre gelmeli
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public Page<ContactMessageResponse> searchByEmail(
            @RequestParam(value = "email") String email, //gelen degerlerde email olmazsa hata verir. handle etmeliyiz
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){
        return contactMessageService.searchByEmail(email, page, size, sort, type);
    }

    @GetMapping("/searchBySubject") //.../searchBySubject?subject=deneme   +GET
    //page ve pageable tum yapilar data domainden import edilmeli
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public Page<ContactMessageResponse> searchBySubject(
            @RequestParam(value = "subject") String subject, //gelen degerlerde subject olmazsa hata verir. handle etmeliyiz
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size",defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "dateTime") String sort,
            @RequestParam(value = "type", defaultValue = "desc") String type
    ){
        return  contactMessageService.searchBySubject(subject,page,size,sort,type);

    }
    // Pathvariable ile silme

        @DeleteMapping("/deleteById/{contactMessageId}")  // http://localhost:8080/contactMessages/deleteById/1 + DELETE
        @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
        public ResponseEntity<String> deleteByIdPath(@PathVariable Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }
    //Requestparam ile silme
    @DeleteMapping("/deleteByIdParam") //http://localhost:8080/contactMessages/deleteByIdParam?contactMessageId=1 + DELETE
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public ResponseEntity<String> deleteByIdParam(@RequestParam(value = "contactMessageId") Long contactMessageId){
        return ResponseEntity.ok(contactMessageService.deleteById(contactMessageId));
    }

    @GetMapping("/getById/{contactMessageId}") // http://localhost:8080/contactMessages/getById/1  + GET
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public ResponseEntity<ContactMessage> getByIdWithPath(@PathVariable Long contactMessageId ){
        return ResponseEntity.ok(contactMessageService.getContactMessageById(contactMessageId));
    }



    @GetMapping("/searchBetweenDates")// http://localhost:8080/contactMessages/searchBetweenDates?beginDate=2023-09-13&endDate=2023-09-15  + GET
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public ResponseEntity<List<ContactMessage>> searchBetweenDates(
            @RequestParam(value = "beginDate") String beginDateString,
            @RequestParam(value = "endDate") String endDateString
    ){
        List<ContactMessage> contactMessages = contactMessageService.searchByDateBetween(beginDateString,endDateString);
        return ResponseEntity.ok(contactMessages);
    }

    @GetMapping("/searchBetweenTimes") // http://localhost:8080/contactMessages/searchBetweenTimes?startHour=09&startMinute=00&endHour=17&endMinute=30  + GET
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER', 'ASSISTANT_MANAGER)")
    public ResponseEntity<List<ContactMessage>> searchBetweenTimes(
            @RequestParam(value = "startHour") String startHour,
            @RequestParam(value = "startMinute") String startMinute,
            @RequestParam(value = "endHour") String endHour,
            @RequestParam(value = "endMinute") String endMinute
    ){
        List<ContactMessage> contactMessages = contactMessageService.searchBetweenTimes(startHour,startMinute,endHour,endMinute);
        return ResponseEntity.ok(contactMessages);
    }

}
