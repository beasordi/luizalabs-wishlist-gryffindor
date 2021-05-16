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
    public ResponseEntity<ErroResponse> invalidArgument(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message(tratamentoDeMensagem(e))
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(DuplicatedProductInWishList.class)
    public ResponseEntity<ErroResponse> duplicatedProduct(DuplicatedProductInWishList e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Duplicated Product in wishlist")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(NoProductsFoundInWishListExecption.class)
    public ResponseEntity<ErroResponse> noProductsFoundInWishList(NoProductsFoundInWishListExecption e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("No Products Found In WishList")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErroResponse> clientNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("CLIENT NOT FOUND")
                                .status(HttpStatus.NOT_FOUND.value())
                                .build()
                );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErroResponse> productNotFound() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("PRODUCT NOT FOUND")
                                .status(HttpStatus.NOT_FOUND.value())
                                .build()
                );
    }

    @ExceptionHandler(WishListLimitExcededException.class)
    public ResponseEntity<ErroResponse> wishListLimitExcededException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("PRODUCTS LIMIT EXCEEDED (MAX 20) ")
                                .status(HttpStatus.NOT_FOUND.value())
                                .build()
                );
    }

    private String tratamentoDeMensagem(MethodArgumentNotValidException e) {
        String[] textoSeparado = e.getMessage().split(";");
        long count = Arrays.stream(textoSeparado).count();
        String msg = Arrays.stream(textoSeparado).skip(count - 1).findFirst().get();
        String nMsg = msg.replace(" default message [", "").replace("]]", "");
        return nMsg;
    }
}



