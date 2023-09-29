package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Iletisim {
    String cepTelefonu;
    String evTelefonu;
    String isTelefonu;
    String email;
    String webSite;
    /**
     * adres bilgisini detaylandırralım
     */
    @Column(length = 500)
    String adres;

}
