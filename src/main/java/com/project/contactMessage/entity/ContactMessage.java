package com.project.contactMessage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Normalized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true) //10 tane degiskenden 2 tanesi zorunlu Id name ve email
// builder constructor icin parametre sayilarini arka planda ayarlar
public class ContactMessage implements Serializable {
  // serialization kullanimi. Degerleri setlenmis nesleler agda bir yere gidiyorsa
    //agda gidebilmesi icin serialization olmalidir. json formati da buna bir ornektir.

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    //DTO sinifiyla karsilayacagimiz icin email annotation kullanmadik
    private String email;

    @NotNull
    private String subject;

    @NotNull
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm",timezone = "US")
    private LocalDateTime dateTime;
}
