package com.project.payload.response;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpStatus;

@Data //Getter Setter Tostring vb. hepsini kapsiyor.
@AllArgsConstructor
@NoArgsConstructor
@Builder (toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)// asagidakilerden hangisini setlerse jsonda onu gosterir
public class ResponseMessage<E> {
    //Dondurulecek tum data tipleriyle calismasi icin Generic yapicaz
    private E object; //object burada degisken adi. Object degil yani
    private String message;
    private HttpStatus status;
    // degiskeni uygulamanin tamaminda kullanacaksam o seviyedeki katmanda olustururum. Bu yuzden bu
    //sinifi daha genel bir yerde yazdik
}
