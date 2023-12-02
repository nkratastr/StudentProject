package com.project.contactMessage.service;

import com.project.contactMessage.dto.ContactMessageRequest;
import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import com.project.contactMessage.mapper.ContactMessageMapper;
import com.project.contactMessage.messages.Messages;
import com.project.contactMessage.repository.ContactMessageRepository;
import com.project.exception.ConflictException;
import com.project.exception.ResourceNotFoundException;
import com.project.payload.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.naming.spi.ResolveResult;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactMessageService {
    //java sinif ismi verirken entpoint adi ve sonrasinda katman adi yazilir
    // autowired kullanmadik. requiredArgsConstructor ve final yapisi ile bunu sagliyoruz. final ile nesne olusurken deger vermek gerekiryor

private final ContactMessageRepository contactMessageRepository;

private  final ContactMessageMapper contactMessageMapper;

public ResponseMessage<ContactMessageResponse> save(ContactMessageRequest contactMessageRequest){
    //DTO-->pojo
    ContactMessage contactMessage= contactMessageMapper.requestToContactMessage(contactMessageRequest);
    //Repoya gonderiliyor
    // save methodu  sinifi donduruyordu ama simdiye kadar hic kullanmadik
    // bu sekilde frontendde bu sinifi guncel olarak kullaniciya gosterebiliriz
    // tekrar sorgu yapmamiza gerek kalmaz
    ContactMessage savedData = contactMessageRepository.save(contactMessage);
    //ContactMessageResponse generik oldugu icin oncesinde nokta koyduk
    return ResponseMessage.<ContactMessageResponse>builder()
            .message("ContactMessage Created Successfully")
            .status(HttpStatus.CREATED)
            .object(contactMessageMapper.contactMessageToResponse(savedData))
            .build();

}

    public Page<ContactMessageResponse> getAll(int page, int size, String sort, String type) {


   //sort datadomainden import edileek
   // bu kodun daha okunabilir halini de ilerleyen bolumde yazacagiz
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        //olusturdugum pageable nesnesi ascending ama gelen descending olabilir diye
        // if icersinde kontrol edip oyleyse pageable nesneyi descendinge ceviriyoruz
        //eger gelen de ascending ise ife girmrz
        if(Objects.equals(type, "desc")){
            pageable=PageRequest.of(page, size, Sort.by(sort).ascending() );

        }
        //db den contactMessage page yapida geliyor.
        return   contactMessageRepository.findAll(pageable).map(contactMessageMapper::contactMessageToResponse);
        //method referansi kullandik :: ile
    }

    public Page<ContactMessageResponse> searchByEmail(String email, int page, int size, String sort, String type) {
    // ilerde refaktor edilmesi gereken bir kod.

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        //== yerine equal kullandik. Hata vermesin istedik. equals referansina bakmaz.

        if(Objects.equals(type, "desc")){
            pageable=PageRequest.of(page, size, Sort.by(sort).ascending() );
        }
        return contactMessageRepository.findByEmailEquals(email, pageable).map(contactMessageMapper::contactMessageToResponse); //okuma kolayligi icin equals

    }

    public Page<ContactMessageResponse> searchBySubject(String subject, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if(Objects.equals(type, "desc")){
            pageable=PageRequest.of(page, size, Sort.by(sort).ascending() );
        }
        return contactMessageRepository.findBySubjectEquals(subject, pageable).map(contactMessageMapper::contactMessageToResponse); //okuma kolayligi icin equals

    }

    public String deleteById(Long contactMessageId) {
    getContactMessageById(contactMessageId);
    contactMessageRepository.deleteById(contactMessageId);
        return Messages.CONTACT_MESSAGE_DELETED_SUCCESSFULLY;
    }

    public ContactMessage getContactMessageById(Long id){
    return contactMessageRepository.findById(id).orElseThrow(
            //Bunu biz olusturduk ve degistirmek icin bize esneklik veriyor
            ()-> new ResourceNotFoundException(Messages.NOT_FOUND_MESSAGE)
    );

    }

        //3 satir sectim try catch icine aldim
        public List<ContactMessage> searchByDateBetween(String beginDateString, String endDateString) {
            try {
                LocalDate beginDate = LocalDate.parse(beginDateString);
                LocalDate endDate = LocalDate.parse(endDateString);
                return contactMessageRepository.findMessagesBetweenDates(beginDate,endDate);
            } catch (DateTimeParseException e) {
                throw new ConflictException(Messages.WRONG_DATE_FORMAT);
            }
        }

    public List<ContactMessage> searchBetweenTimes(String startHour, String startMinute, String endHour, String endMinute) {

        try {
            // alfabetik ifadeleri intigera ceviremez. Bu duruma gore handle ediyoruz
            int startH = Integer.parseInt(startHour);
            int startM = Integer.parseInt(startMinute);
            int endH = Integer.parseInt(endHour);
            int endM = Integer.parseInt(endMinute);

            return contactMessageRepository.findMessagesBetweenTimes(startH,startM,endH,endM);
        } catch (NumberFormatException e) {
            throw new ConflictException(Messages.WRONG_TIME_FORMAT);
        }
    }
}
