package br.com.wishlist.controller;

import br.com.wishlist.controller.dto.ClienteRequest;
import br.com.wishlist.domain.model.ClienteModel;
import br.com.wishlist.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //Adicionar cliente a base de dados
    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    public void addCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        clienteService.addCliente(clienteRequest);
    }


}

