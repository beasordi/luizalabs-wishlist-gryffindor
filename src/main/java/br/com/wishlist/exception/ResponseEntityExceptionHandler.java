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
                                .message("Produto duplicado na Wish List")
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
                                .message("Wish List não foi encontrada")
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
                                .message("Não existem produtos na Wish List")
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
                                .message("Cliente não encontrado")
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
                                .message("Produto não encontrado")
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
                                .message("Limite de produtos excedidos (MAX 20)")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(DuplicatedSkuException.class)
    public ResponseEntity<ErroResponse> duplicatedSku() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("sku duplicado")
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build()
                );
    }

    @ExceptionHandler(EmptyListProductException.class)
    public ResponseEntity<ErroResponse> emptyList() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ErroResponse
                                .builder()
                                .message("Lista vazia")
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
                                .message("Produto associado à uma Wish List, não é possivel remover")
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
                                .message("Cliente já cadastrado")
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
                                .message("Lista de clientes vazia")
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



