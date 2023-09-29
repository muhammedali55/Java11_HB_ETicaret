package com.muhammet.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Bu sınıf hibernate.cfg.cml dosyasını kullanrak connection
 * oluşturmak için kullanılacaktır
 * İstenirse hibernate dosyasının konumu burada belrtilerek
 * farklı lokasyonlardan okunabilir.
 */
public class HibernateUtility {
    private static final SessionFactory SESSION;

    static{
        try{
            //SESSION = new Configuration().configure("c:\\config\\hibernate.cfg.xml").buildSessionFactory();
            SESSION = new Configuration().configure().buildSessionFactory();
        }catch (Exception exception){
            System.out.println("Hibernate başlatılırken hata oluştu:"+ exception);
            throw new ExceptionInInitializerError(exception);
        }
    }

    public static SessionFactory getSessionFactory(){
        return SESSION;
    }
}
