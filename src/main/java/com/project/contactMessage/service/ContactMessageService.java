package com.project.contactMessage.service;

import com.project.contactMessage.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageService {
    //java sinif ismi verirken entpoint adi ve sonrasinda katman adi yazilir
    // autowired kullanmadik. requiredArgsConstructor ve final yapisi ile bunu sagliyoruz. final ile nesne olusurken deger vermek gerekiryor

private final ContactMessageRepository contactMessageRepository;

}
