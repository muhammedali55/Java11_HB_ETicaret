package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_satis_detay")
public class SatisDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long satisId;
    Long urunId;
    Integer adet;
    BigDecimal fiyat;
    BigDecimal toplamFiyat;
    @Embedded
    BaseEntity baseEntity;
}
