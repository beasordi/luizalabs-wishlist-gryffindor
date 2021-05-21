package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ClientRequest;
import br.com.wishlist.controller.dto.ClientResponse;
import br.com.wishlist.controller.dto.ClientUpdateRequest;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.exception.ClientCodeDuplicatedException;
import br.com.wishlist.exception.ClientNotFoundException;
import br.com.wishlist.exception.EmptyListClientException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @SneakyThrows
    public void addClient(ClientRequest clientRequest) {

        ClientModel model = new ClientModel();
        model.setName(clientRequest.getName());
        model.setSurname(clientRequest.getSurname());
        model.setAddress(clientRequest.getAddress());
        model.setEmail(clientRequest.getEmail());
        model.setPhone(clientRequest.getPhone());
        model.setClientCode(clientRequest.getClientCode());

        validClientDuplicated(clientRequest.getClientCode());
        clientRepository.save(model);

    }

    @SneakyThrows
    public List<ClientResponse> listClients() {
        List<ClientModel> clientList = clientRepository.findAll();
        if (clientList.isEmpty()) {
            throw new EmptyListClientException();
        }
        return clientList.stream().map(ClientResponse::new).collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    public void deleteClient(String clientCode) {
        findClient(clientCode);
        try {
            clientRepository.deleteByClientCode(clientCode);
        } catch (Exception e) {
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    public void updateClient(String clientCode, ClientUpdateRequest request) {

        ClientModel model = clientRepository.findByClientCode(clientCode);
        clientRepository.save(validation(request, model));
    }

    private ClientModel validation(ClientUpdateRequest request, ClientModel model) {

        model.setAddress(request.getAddress() != null ? request.getAddress() : model.getAddress());
        model.setEmail(request.getEmail() != null ? request.getEmail() : model.getEmail());
        model.setPhone(request.getPhone() != null ? request.getPhone() : model.getPhone());
        model.setName(request.getName() != null ? request.getName() : model.getName());
        model.setSurname(request.getSurname() != null ? request.getSurname() : model.getSurname());

        return model;
    }

    @SneakyThrows
    private ClientModel findClient(String clientCode) {
        ClientModel clientModel = clientRepository.findByClientCode(clientCode);
        if (clientModel == null) {
            throw new ClientNotFoundException();
        }
        return clientModel;
    }

    @SneakyThrows
    private void validClientDuplicated(String clientCode) {
        ClientModel clientModel = clientRepository.findByClientCode(clientCode);
        if (clientModel != null) {
            throw new ClientCodeDuplicatedException();
        }
    }
}