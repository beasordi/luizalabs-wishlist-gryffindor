package br.com.wishlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ResponseEntityExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> invalidArgument(MethodArgumentNotValidException e, HttpServletRequest request) {

        String nMsg = tratamentoDeMensagem(e);

        ErroResponse err = new ErroResponse(
                nMsg,
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private String tratamentoDeMensagem(MethodArgumentNotValidException e) {
        String[] textoSeparado = e.getMessage().split(";");
        long count = Arrays.stream(textoSeparado).count();
        String msg = Arrays.stream(textoSeparado).skip(count - 1).findFirst().get();
        String nMsg = msg.replace(" default message [", "").replace("]]", "");
        return nMsg;
    }
}



