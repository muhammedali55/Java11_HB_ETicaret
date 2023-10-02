package com.muhammet.criteriaornekleri;

import com.muhammet.repository.entity.Musteri;
import com.muhammet.repository.entity.Urun;
import com.muhammet.repository.views.VwUrun;
import com.muhammet.utility.HibernateUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

public class CriteriaOrnekleri {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    public CriteriaOrnekleri(){
        /**
         * Sorgu işlemlerinde criterler belirlendikten sonra DB üzerinde işlenmek üzere çalıştırılır.
         * Bu işlemlerin yöetimini EntityManager yapar.
         */
        entityManager = HibernateUtility.getSessionFactory().createEntityManager();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }
    /**
     * select * from tblmusteri ->
     */
    public List<Musteri> findAll(){
        // Kriter belirlerken kullanılacak sınıfın adını vererek tanımlama yapıyorsunuz.
        // Bir sınıfın içinde bulunan alanları sorgularda kullanılbilmesi için ve o alanları sorgulara eklenmesi için tersine mühendislik
        // Java Reflection kullanılarak yapılmaktadır. Bu nedenle Query oluştururken kullanılacak sınıf parametre olarak verilir.
        CriteriaQuery<Musteri> criteria = criteriaBuilder.createQuery(Musteri.class);
        // Select işlemi için kullanılacak tablo belirlenir. Ve tablodaki alanların hangilerinin kullanılacağı tanımlanır.
        Root<Musteri> root = criteria.from(Musteri.class);
        // select *  işlemi tanımlanıyor.
        criteria.select(root);
        // Sorgu çalıştırılıyor.
        List<Musteri> musteriList = entityManager.createQuery(criteria).getResultList();
        musteriList.forEach(System.out::println);
        return musteriList;
    }

    /**
     * select ad,fiyat,marka,model from tblmusteri
     * select * from tblurun ->
     */
    public List<String> selectOneColumn(){
        // sorgu çalıştığında String listesi dönecek
        CriteriaQuery<String> criteria = criteriaBuilder.createQuery(String.class);
        Root<Urun> root = criteria.from(Urun.class);
        // select ad
        criteria.select(root.get("ad"));
        List<String> resultList = entityManager.createQuery(criteria).getResultList();
        resultList.forEach(System.out::println);
        return resultList;
    }

    /**
     * select ad tblurun where id = ?
     */
    public List<String> selectOneColumnById(Long id){
        // sorgu çalıştığında String listesi dönecek
        CriteriaQuery<String> criteria = criteriaBuilder.createQuery(String.class);
        Root<Urun> root = criteria.from(Urun.class);
        // select ad
        criteria.select(root.get("model"));
        criteria.where(criteriaBuilder.equal(root.get("id"),id)); // where id = ?
        List<String> resultList = entityManager.createQuery(criteria).getResultList();
        resultList.forEach(System.out::println);
        return resultList;
    }

    /**
     * Bir tabloda select * from tblurun where id = 3 yazdığımda
     * geriye dönen değer kaç tanedir. Bu değeri javada ne olacak
     * dönmeliyiz?
     */
    public Urun findById(Long id){
        CriteriaQuery<Urun> criteria = criteriaBuilder.createQuery(Urun.class);
        Root<Urun> root = criteria.from(Urun.class);
        criteria.select(root);
        criteria.where(criteriaBuilder.equal(root.get("id"),id));
        Urun result = entityManager.createQuery(criteria).getSingleResult();
        System.out.println(result);
        return result;
    }

    /**
     *
     * select id, ad,fiyat from tblurun
     *
     */
    public List<Object[]> selectManyColumn(){
        CriteriaQuery<Object[]> criteria = criteriaBuilder.createQuery(Object[].class);
        Root<Urun> root = criteria.from(Urun.class);

        Path<Long> id = root.get("id");
        Path<String> ad = root.get("ad");
        Path<BigDecimal> fiyat = root.get("fiyat");

        criteria.select(criteriaBuilder.array(id,ad,fiyat));

        List<Object[]> result = entityManager.createQuery(criteria).getResultList();
       // result.forEach(System.out::println);
        return result;
    }

    /**
     * select * from tbl_urun where ad like '%a%' and fiyat>600
     * @param urunAdi
     * @param fiyat
     * @return
     */
    public List<Urun> findAllByNameLikeAndFiyatGte(String urunAdi, BigDecimal fiyat){
        CriteriaQuery<Urun> criteria = criteriaBuilder.createQuery(Urun.class);
        Root<Urun> root = criteria.from(Urun.class);
        criteria.select(root);

        Predicate s1 = criteriaBuilder.like(root.get("ad"),urunAdi);
        Predicate s2 = criteriaBuilder.greaterThan(root.get("fiyat"),fiyat);

        criteria.where(criteriaBuilder.and(s1,s2));

        List<Urun> result = entityManager.createQuery(criteria).getResultList();
        return result;
    }

    /**
     *
     * Java Persistence Api - JPA Eski
     * Jakarta Persistence Api - JPA Yeni
     * Native Query - SQL komutları üzerinden JPA ile sorgulama yapmak.
     */
    public List<Urun> findAllNativeQuery(){
        /**
         * Eğer özellikle belirtilmez ise dönülen değer Object[]
         */
        List<Urun> uruns =  entityManager.createNativeQuery("select * from tbl_urun", Urun.class).getResultList();
        return uruns;
    }

    public List<VwUrun> findAllNameNativeQuery(){
        /**
         * create view vwurun
         * as
         * select id, ad, m.ad from tbl_urun as ur
         * left join tblmusteri as m on m.id= ur.musteri_id
         * Views
         * procedure
         */
        List<VwUrun> result = entityManager.createNativeQuery("select id, ad from tbl_urun", VwUrun.class).getResultList();
        return result;
    }



    public List<Urun> findAllNamedQuery(){
//        TypedQuery<Urun> namedQuery =  entityManager.createNamedQuery("Urun.findAll", Urun.class);
//        return namedQuery.getResultList();
        return entityManager.createNamedQuery("Urun.findAll", Urun.class).getResultList();
    }

    public List<Urun> findAllByAd(String urunadi){
        TypedQuery<Urun> namedQuery =  entityManager.createNamedQuery("Urun.findByAd", Urun.class);
        namedQuery.setParameter("urunadi",urunadi);
        return namedQuery.getResultList();
    }

    public BigDecimal getTotalPrice(){
        TypedQuery<BigDecimal> namedQuery = entityManager.createNamedQuery("Urun.getTotalPrice", BigDecimal.class);
        return namedQuery.getSingleResult();
    }

}
