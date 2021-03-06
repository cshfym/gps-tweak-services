package com.gpstweak.services.db

import com.gpstweak.domain.GPSData
import com.gpstweak.domain.Persistable
import org.hibernate.HibernateException
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.AnnotationConfiguration
import org.springframework.stereotype.Component

@Component
class BaseEntityManager {

  private static SessionFactory factory

  public BaseEntityManager() {
    try {
      factory = new AnnotationConfiguration().configure().buildSessionFactory()
    } catch (Throwable ex) {
      System.err.println("Failed to create sessionFactory object." + ex)
      throw new ExceptionInInitializerError(ex)
    }
  }

  /* Find Entity */
  public <T> T find(Class<T> type, Long id) {

    Persistable obj = null
    Session session = factory.openSession()

    try {
      obj = session.get(type, id)
    } catch (HibernateException e) {
      throw e
    } finally {
      session.close()
    }

    obj
  }

  /* Find Entities */
  public <T extends Persistable> List<T> findAll(String type) {

    List<T> result = []

    Session session = factory.openSession()
    Transaction tx = null

    try {
      tx = session.beginTransaction()
      List data = session.createQuery("FROM " + type).list()
      data.each {
        result << it
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx != null) {
        tx.rollback()
      }
      throw e
    } finally {
      session.close();
    }
    result
  }

  /* Create Entity */
  public <T> T create(Persistable obj) {

    Session session = factory.openSession()
    Transaction tx = null

    try {
      tx = session.beginTransaction()
      session.save(obj)
      tx.commit()
    } catch (HibernateException e) {
      if (tx != null) tx.rollback()
      throw e
    } finally {
      session.close()
    }
    obj
  }
}
