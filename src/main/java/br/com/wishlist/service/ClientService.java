package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ClientRequest;
import br.com.wishlist.controller.dto.ClientResponse;
import br.com.wishlist.controller.dto.ClientUpdateRequest;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service

public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public void addClient(ClientRequest clientRequest) {
        ClientModel model = new ClientModel();
        model.setName(clientRequest.getName());
        model.setSurname(clientRequest.getSurname());
        model.setAddress(clientRequest.getAddress());
        model.setEmail(clientRequest.getEmail());
        model.setPhone(clientRequest.getPhone());
        model.setClientCode(clientRequest.getClientCode());

        clientRepository.save(model);
    }

    public List<ClientResponse> listClients() {
        List<ClientModel> clientList = clientRepository.findAll();
        return clientList.stream().map(ClientResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteClient( String clientCode) {
        clientRepository.deleteByClientCode(clientCode);
    }

    public void updateClient (String clientCode, ClientUpdateRequest request){

        ClientModel model = clientRepository.findByClientCode(clientCode);
        clientRepository.save(validation(request, model));
        }

    private ClientModel validation (ClientUpdateRequest request, ClientModel model) {

        model.setAddress(request.getAddress() != null ? request.getAddress() : model.getAddress());
        model.setEmail(request.getEmail() != null ? request.getEmail() : model.getEmail());
        model.setPhone(request.getPhone() != null ? request.getPhone() : model.getPhone());
        model.setName(request.getName() != null ? request.getName() : model.getName());
        model.setSurname(request.getSurname() != null ? request.getSurname() : model.getSurname());

        return model;

    }

}



