package br.com.wishlist.service;

import br.com.wishlist.controller.dto.ClientRequest;
import br.com.wishlist.controller.dto.ClientUpdateRequest;
import br.com.wishlist.controller.dto.ProductUpdateRequest;
import br.com.wishlist.domain.model.ClientModel;
import br.com.wishlist.domain.model.ProductModel;
import br.com.wishlist.domain.repository.ClientRepository;
import br.com.wishlist.exception.ClientCodeDuplicatedException;
import br.com.wishlist.exception.ClientNotFoundException;
import br.com.wishlist.exception.EmptyListClientException;
import br.com.wishlist.exception.ProductNotFoundException;
import com.mysql.cj.xdevapi.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService target;

    @Mock
    private ClientRepository clientRepository;

    @Test(expected = ClientCodeDuplicatedException.class)
    public void addWhenClientCodeDuplicatedTest() {

        ClientRequest request = ClientRequest
                .builder()
                .address("Av São Joao, 1000")
                .clientCode("35107781896")
                .email("joao@email.com")
                .name("Joao")
                .surname("Silva")
                .phone("11999999999")
                .build();

        when(clientRepository.findByClientCode(any())).thenReturn(new ClientModel());
        target.addClient(request);

        verify(clientRepository, times(1)).findByClientCode(any());
    }

    @Test
    public void addClientWhenSucessTest() {

        ClientRequest request = ClientRequest
                .builder()
                .address("Av São Joao, 1000")
                .clientCode("35107781896")
                .email("joao@email.com")
                .name("Joao")
                .surname("Silva")
                .phone("11999999999")
                .build();

        when(clientRepository.findByClientCode(any())).thenReturn(null);
        target.addClient(request);

        verify(clientRepository, times(1)).save(any());
    }

    @Test
    public void ListClientWhenSuccessTest() {
        List<ClientModel> list = new ArrayList<>();
        list.add(new ClientModel(1L, "Joao", "Silva", "11999999999", "Av Sao Joao", "joao@email.com", "35107781896"));

        when(clientRepository.findAll()).thenReturn(list);
        target.listClients();

        verify(clientRepository, times(1)).findAll();
    }

    @Test(expected = EmptyListClientException.class)
    public void ListClientWhenErrorTest() {
        List<ClientModel> list = new ArrayList<>();

        when(clientRepository.findAll()).thenReturn(list);
        target.listClients();

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void DeleteClientWhenSuccessTest() throws SQLIntegrityConstraintViolationException {
        when(clientRepository.findByClientCode(any())).thenReturn(ClientModel.builder().build());
        target.deleteClient(any());

        verify(clientRepository, times(1)).deleteByClientCode(any());
    }

    @Test
    public void UpdateClientWhenSuccessTest() {
        ClientUpdateRequest request = ClientUpdateRequest
                .builder()
                .address("Av São Joao, 1000")
                .email("joao@email.com")
                .name("Joao")
                .surname("Silva")
                .phone("11999999999")
                .build();
        ClientModel client = new ClientModel();

        when(clientRepository.findByClientCode(any())).thenReturn(client);
        target.updateClient(any(), request);

        verify(clientRepository, times(1)).save(any());
    }

    @Test(expected = ClientNotFoundException.class)
    public void UpdateClientErrorFindClientTest() {
        ClientUpdateRequest request = ClientUpdateRequest
                .builder()
                .address("Av São Joao, 1000")
                .email("joao@email.com")
                .name("Joao")
                .surname("Silva")
                .phone("11999999999")
                .build();

        when(clientRepository.findByClientCode(any())).thenReturn(null);
        target.updateClient(any(), request);

        verify(clientRepository, times(1)).save(any());
    }
}