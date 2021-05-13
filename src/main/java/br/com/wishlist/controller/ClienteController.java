package br.com.wishlist.controller;

import br.com.wishlist.domain.model.ClienteModel;
import br.com.wishlist.domain.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //Adicionar cliente a base de dados
    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    public void addCliente(@RequestBody ClienteModel cliente) {
        clienteService.addCliente(cliente);
    }

    //Deletar cliente da base de dados
    @RequestMapping(value = "/cliente", method = RequestMethod.DELETE)
    public void deleteCliente(@RequestBody Long id) {
        clienteService.deleteCliente(id);
    }

    //Listar clientes da base de dados
    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public List<ClienteModel> listCliente(){
        return clienteService.listCliente();
    }

    //Alterar os clientes da base de dados
    @RequestMapping(value = "/cliente", method = RequestMethod.PUT)
    public updateCliente(@RequestBody ClienteModel cliente){
        clienteService.updateCliente(cliente);
    }


}

