package br.com.wishlist.controller;

import br.com.wishlist.controller.dto.ClientRequest;
import br.com.wishlist.controller.dto.ClientResponse;
import br.com.wishlist.controller.dto.ClientUpdateRequest;
import br.com.wishlist.service.ClientService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Adicionar cliente")
    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public ResponseEntity<String> addClient(@Valid @RequestBody ClientRequest clientRequest) {
        clientService.addClient(clientRequest);
        return new ResponseEntity<>("Cliente adicionado com sucesso", HttpStatus.CREATED);

    }

    @ApiOperation(value = "Listar cliente")
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ResponseEntity<List<ClientResponse>> listClients() {
        List<ClientResponse> response = clientService.listClients();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletar cliente")
    @RequestMapping(value = "/client/{clientCode}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteClient(@PathVariable("clientCode") String clientCode) {
        clientService.deleteClient(clientCode);
        return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.OK);
    }

    @ApiOperation(value = "Atualizar cliente")
    @PutMapping(value = "/clientCode/{clientCode}")
    public ResponseEntity<String> updateClient(
            @PathVariable("clientCode") String clientCode,
            @Valid @RequestBody ClientUpdateRequest request
    ) {
        clientService.updateClient(clientCode, request);
        return new ResponseEntity<>("Cliente atualizado com sucesso!", HttpStatus.OK);
    }
}
