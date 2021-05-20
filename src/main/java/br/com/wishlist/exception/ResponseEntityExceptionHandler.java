package br.com.wishlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
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

    @ExceptionHandler(DuplicatedProductInWishListException.class)
    public ResponseEntity<ErroResponse> duplicatedProduct(DuplicatedProductInWishListException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Duplicated product in wishlist")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(WishListNotFoundException.class)
    public ResponseEntity<ErroResponse> duplicatedProduct(WishListNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("WishList not found ")
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
                                .message("No products found in wishList")
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
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(DuplicatedSku.class)
    public ResponseEntity<ErroResponse> duplicatedSku() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Duplicated sku")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(EmptyList.class)
    public ResponseEntity<ErroResponse> emptyList() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("List is empty")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErroResponse> integrateError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Product is linked to a wishlist, it is not possible to remove")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(ClientCodeDuplicatedException.class)
    public ResponseEntity<ErroResponse> clientCodeDuplicated() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Client already registered")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(EmptyListClientException.class)
    public ResponseEntity<ErroResponse> emptyListClient() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Client List is empty")
                                .status(HttpStatus.BAD_REQUEST.value())
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



