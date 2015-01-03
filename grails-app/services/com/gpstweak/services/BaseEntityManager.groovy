package com.gpstweak.services

import com.gpstweak.domain.Persistable
import org.hibernate.HibernateException
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

  /* Create Entity */
  public <T> T create(Persistable obj) {

    Integer id = -1

    Session session = factory.openSession()
    Transaction tx = null

    try {
      tx = session.beginTransaction()
      id = (Integer) session.save(obj)
      tx.commit()
    } catch (HibernateException e) {
      if (tx != null) tx.rollback()
      throw e
    } finally {
      session.close()
    }

    obj.id = id

    obj
  }
}
