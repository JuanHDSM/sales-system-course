package com.JuanHDSM.Vendas.domain.entities;

import com.JuanHDSM.Vendas.domain.dtos.RequestClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_clients")
@Table(name = "tb_clients")
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Campo nome é obrigatório")
    private String name;
    @Column(length = 11, unique = true)
    private String cpf;
    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Client(RequestClientDTO obj) {
        this.name = obj.name();
        this.cpf = obj.cpf();
    }

}
