package br.com.wishlist.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_cliente")
@AllArgsConstructor
@NoArgsConstructor

public class ClienteModel {


    @Id
    @Column(unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "surname", length = 255, nullable = false)
    private String surname;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adress")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "clientCode", nullable = false, unique = true)
    private String clientCode;

    //pessoas = produtos
    //notebooks = clientes
    //clientes e seus produtos requisitados
    //Nesse caso, produtos esta sendo o lado dominante = lado com o may to many
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "clients_products", joinColumns = //definição do nome da nova tabela que sera criada com os joins
    @JoinColumn(name = "clients_id"), inverseJoinColumns = // definição das novas colunas que serão inseridas nessa tabela nova -- dominante
    @JoinColumn(name = "products_id")) // --dominado
    private List<ProductModel> productsList;
}





