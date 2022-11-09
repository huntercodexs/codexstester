package com.huntercodexs.postalcode.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "postalcode")
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
