package br.com.wishlist.domain.repository;

import br.com.wishlist.domain.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    ClientModel findByClientCode(String clientCode);

    void deleteByClientCode(String clientCode);
}
