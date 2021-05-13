package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ClienteRequest;
import br.com.wishlist.domain.model.ClienteModel;
import br.com.wishlist.domain.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public void addCliente(ClienteRequest clienteRequest) {
        ClienteModel model = new ClienteModel();
        model.setName(clienteRequest.getName());
        model.setSurname(clienteRequest.getSurname());
        model.setAddress(clienteRequest.getAddress());
        model.setEmail(clienteRequest.getEmail());
        model.setLogin(clienteRequest.getLogin());
        model.setPassword(clienteRequest.getPassword());
        model.setTelephone(clienteRequest.getTelephone());
        model.setClientCode(clienteRequest.getClientCode());

        clienteRepository.save(model);
    }

}



