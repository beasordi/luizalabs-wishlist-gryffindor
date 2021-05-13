package br.com.wishlist.domain.service;

import br.com.wishlist.domain.dto.ClienteRequest;
import br.com.wishlist.domain.model.ClienteModel;
import br.com.wishlist.domain.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public ClienteModel addCliente(ClienteModel cliente) {
        return ClienteRepository.save(cliente);
    }

}


