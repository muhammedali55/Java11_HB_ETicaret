package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Named Query,
 * önbellekleme yapabiliyor. bu nedenle aynı orgu tekrar çağırtıldığında ön bellekten değer döndüğü için daha hızlı cevap verir.
 * HQL, JPQL, NativeSQL
 * NativeSQL -> Native Structured Query Language    -> select * from tblmusteri -> RDB
 * JPQL -> Java Persistence Query Language          -> select m from Musteri m
 * HQL -> Hibernate Query Language                  -> from Musteri
 */
@NamedQueries({
        @NamedQuery(name = "Urun.findAll", query = "SELECT u from Urun u"),
        @NamedQuery(name = "Urun.findByAd",query = "SELECT u from Urun u where u.ad like :urunadi"),// select * from tbl_urun where ad like 'dasdas'
        @NamedQuery(name = "Urun.getTotalPrice", query = "SELECT SUM(u.fiyat) as totalprice from Urun u")
}) // {23232,23,23,3}

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_urun")
public class Urun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    String barkod;
    String marka;
    String model;
    BigDecimal fiyat;
    /**
     *
     * int stok -> default 0
     * Integer stok -> default null
     *
     */
    Integer stok;
    @Embedded
    BaseEntity baseEntity;

}
