package com.muhammet.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_sepet")
public class Sepet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    /**
     * Bu bilgi müüşteri tablosunda ki müşteri id ile ilişkilendirilecek.
     */
    Long musteriId;
    /**
     * K.-> Ürün id si
     * V-> Adedi
     */
    @ElementCollection
    Map<Long,Integer> urunIds;

    /**
     * Ürün var ama kaç adet var belli değil.
     *
     */


}
