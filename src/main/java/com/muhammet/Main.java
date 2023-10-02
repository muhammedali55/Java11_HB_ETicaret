package com.muhammet;
import com.muhammet.criteriaornekleri.CriteriaOrnekleri;
import com.muhammet.enums.ECinsiyet;
import com.muhammet.repository.UrunRepository;
import com.muhammet.repository.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        /**
         * int[] dizi = {12,12,12,12,1}
         * String[] ifadeler = {"dfsfsf","sdfsdf","sdfsdf"}
         * Object[] objeler = {1,"sdfsdf",12.2}
         */
//        List<Object[]> result = new CriteriaOrnekleri().selectManyColumn();
//        result.forEach(x->{ //Object[] {1,"Şeker",200}
//            System.out.println("id: "+ x[0]);
//            System.out.println("ad: "+ x[1]);
//            System.out.println("fiyat: "+ (Double.parseDouble(x[2].toString())*3));
//
//        });


        CriteriaOrnekleri cr = new CriteriaOrnekleri();
        System.out.println("Toplam ürün fiyat bilgisi.....: "+cr.getTotalPrice());

    }
}