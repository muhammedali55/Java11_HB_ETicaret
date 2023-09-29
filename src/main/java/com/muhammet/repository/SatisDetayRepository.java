package com.muhammet.repository;

import com.muhammet.repository.entity.SatisDetay;
import com.muhammet.utility.MyFactoryRepository;

public class SatisDetayRepository extends MyFactoryRepository<SatisDetay,Long> {

    public SatisDetayRepository() {
        super(new SatisDetay());
    }
}
