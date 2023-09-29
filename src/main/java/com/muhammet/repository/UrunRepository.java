package com.muhammet.repository;

import com.muhammet.repository.entity.Urun;
import com.muhammet.utility.MyFactoryRepository;

public class UrunRepository extends MyFactoryRepository<Urun,Long> {

    public UrunRepository() {
        super(new Urun());
    }
}
