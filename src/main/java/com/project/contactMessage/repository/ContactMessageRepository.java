package com.project.contactMessage.repository;

import com.project.contactMessage.dto.ContactMessageResponse;
import com.project.contactMessage.entity.ContactMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
    Page<ContactMessage> findByEmailEquals(String email, Pageable pageable);

    Page<ContactMessage> findBySubjectEquals(String subject, Pageable pageable);

    //turetemedik o yuzden jpql yazicaz
    //FUNCTION ile tum tarih bilgilerinden sadece date yani gun ay yil istedik
    // sql yerine jpql kullanmak arkada veritabani degisirse mantiklidir.
    @Query("SELECT c FROM ContactMessage c WHERE FUNCTION('DATE', c.dateTime) BETWEEN ?1 and ?2")
    List<ContactMessage> findMessagesBetweenDates(LocalDate beginDate, LocalDate endDate);

    @Query("SELECT c FROM ContactMessage c WHERE " +
            "(EXTRACT(HOUR FROM c.dateTime) BETWEEN :startH AND :endH) AND " +
            "(EXTRACT(HOUR FROM c.dateTime) != :startH OR EXTRACT(MINUTE FROM c.dateTime) >= :startM) AND " +
            "(EXTRACT(HOUR FROM c.dateTime) != :endH OR EXTRACT(MINUTE FROM c.dateTime) <= :endM)")
    List<ContactMessage> findMessagesBetweenTimes(@Param("startH") int startH, @Param("startM") int startM,
                                                  @Param("endH") int endH,@Param("endM") int endM);
}