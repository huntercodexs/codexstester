package com.huntercodexs.postalcode.database.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postalcode")
public class PostalCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column
    public String cep;

    @Column
    public String logradouro;

    @Column
    public String complemento;

    @Column
    public String bairro;

    @Column
    public String localidade;

    @Column
    public String uf;

    @Column
    public String ibge;

    @Column
    public String gia;

    @Column
    public String ddd;

    @Column
    public String siafi;

}
