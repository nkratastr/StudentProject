package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //custom exception olusturduk. Hangi status kodu vermesi gerektigini burada belirttik
//custom hata siniflarindan burasini da setlemek gerekir
public class ResourceNotFoundException extends RuntimeException{
public ResourceNotFoundException(String message){
    super(message);
}

}
