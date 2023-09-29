package com.muhammet.repository.entity;

import com.muhammet.enums.ECinsiyet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tblmusteri")
@Data // toString, getter, setter, reqConst.
@AllArgsConstructor // tüm alanlar için constructor
@NoArgsConstructor // boş parametresiz constructor
@Builder // builder pattern, bu ilgili sınıftan nesne yaratma için kullanılır.
public class Musteri {
    /**
     * Entity oulşturulacak ise mutlaka ve mutlka ID olma zorundadır.
     * Tablolarımızda benzersiz bir alan olan ID nasıl tanımlanır.
     * 1- Otomatik olarak Id oluşturma (IDENTITY, TABLE, SEQUENCE, AUTO)
     * 2- Elle SQ yazılabilir.
     * 3- UUID Kullanılabilir.
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sq_tblmusteri_id -> initial = 1, increment = 1
    Long id;

    //@Id
    @SequenceGenerator(name = "seq_musteri_id", sequenceName = "seq_musteri_id",initialValue = 100, allocationSize = 5) // SQ tanımı yaptım
    @GeneratedValue(generator = "seq_musteri_id") // tanmımlanmış olan SQ i ilgili alana atamasını yapıyorum.
    Long m_id;

    //@Id
    @GenericGenerator(name="uuid_musteri",strategy = "uuid2")
    @GeneratedValue(generator = "uuid_musteri")
    UUID uuid;

    /**
     * TAblolarda biliyorsunuz ki, kolonlar kullanılmakta olan alanı ifade eder ve siz SQL ile bir kolon oluşturukenb
     * bazı özellikle taınlarsıonız.
     * ad varchar(50) not null default '' gibi.
     * Veritabanında bir kolon yani tablodaki bir alanın özelliklerini
     * belirlemek için @Column anotasyonu kullanılır. Bu anaotasyon ile birlikte
     * oluşacak kolonun özellikleri belirlenebilir.
     *
     * NOT: updatable ve insertable ifadeleri sadece ilgili alan için geçerlidir. Yani DB deki bir kayıt güncelleniyorken güncellenemez
     * denilen kolondaki veri güncellenemez. Diğer kolonlar güncellenebilir.
     */
    @Column(
            name= "musteri_adi", // kolonun DB deki adı özellşetiriliyor.
            length = 60, // kolonun boyutunu belirliyorum.
            nullable = false,  // bu alanın boş ggeçilip geçilmeyeceğini belirliyorum. false -> null geçilemez. true ise -> null geçilebilir
            unique = false, // bu alanın benzersiz olup olmadığını belirliyorusunuz. true ise -> bu kayıttan bir tane daha kayıt edilemez demektir.
            insertable = true, // bu alana ekleme yapılamasını kısıtlamak için kullanıırız. eğer false ise bu alana kayıt ekleyemezsiniz.
            updatable = false // bu alanın güncellemesi durumunu kısıtlıyoruz. eğer false ise güncelleme yapamazsınız.
    )
    String ad;
    @Column(length = 100)
    String soyad;

    /**
     * DİKKAT!!!!!
     * mesela veri tabanında olan belli alanların birleştirilmesi çarpılıp bölünmesi gibi durumlar olabilir.
     * ancak DB nin şişmemesi için bu alanların DB de tutulması istenilmez. bunu yapabilmek adına bir
     * anaotasyon kullanılır. @Transient
     */
    @Transient
    String adsoyad;

    /**
     * Zaman tanımlamaları özel tanımlanan alanlardır.
     * Tarih[DATE] -> 2021-01-01
     * Zaman[TIME] -> 12:00:00
     * Tarih ve Zaman ZamanDamgası[TIMESTAMP] -> 2021-01-01 12:00:00.000000 +3(TIME ZONE)
     */
    @Temporal(TemporalType.DATE)
    Date dogumTarihi;
    @Temporal(TemporalType.DATE)
    Date iseGirisTarihi;
    @Temporal(TemporalType.TIMESTAMP)
    Date kayitTarihi;
    /**
     * DİKKAT!!!!!!!
     * tarih ve zaman ve zaman damgası için ben kullandığım ve tavsiye ettiğim veri tipi
     * Long time, date v.s. dir.
     * zamanı milisaniye cinsinden tutar.
     *
     */


    /**
     * Bir kullanıcıya ait birden fazla telefon numarası olabilir. Bu nedenle burada tek bir
     * değer alamak yerine bir liste talep edebiliriz. Bu liste işlemi ORM aracı için özel
     * bir tanımdır bu nedenle özel olarak işaretlenmelidir.
     * NOT: Eğer entity içinde bir collction tanılayacaksanız mutlaka @ElementCollection anoatasyonu eklemelisiniz.
     */
    // [ev, 0 312] - [cep, 0555]
    //Map<String, String>
    @ElementCollection
    List<String> telefonListesi;

    /**
     * Cinsiyet bilgisi enum olsun
     * Enum -> Değeri int -> görünen değeri -> String tir.
     */
    //@Enumerated(EnumType.ORDINAL) // 0, 1, 2
    @Enumerated(EnumType.STRING) // ERKEK, KADIN, BELIRTMEK_ISTEMIYORUM
    ECinsiyet cinsiyet;


    /**
     * Her tabloda olması gerekli 3 alan bulunur bunların üzerinde duralım.
     */
    @Embedded
    BaseEntity baseEntity;
    /**
     * DİKKAT!!!!!!!!
     * Embed ile eklediğiniz alanların entity içinde olmamasına dikkat edin. Çünkü
     * zaten tanımladığınız alanların tekardan tanımlanması hata oluşmasına
     * neden olacaktır.
     */
    @Embedded
    Iletisim iletisim;
}
