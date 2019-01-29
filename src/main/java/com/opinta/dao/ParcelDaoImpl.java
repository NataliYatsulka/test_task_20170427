package com.opinta.dao;

import com.opinta.entity.Parcel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ParcelDaoImpl implements ParcelDao {
  private final SessionFactory sessionFactory;

  @Autowired
  public ParcelDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  List<Parcel> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createCriteria(Parcel.class)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .list();
  }

  Parcel getById(long id) {
    Session session = sessionFactory.getCurrentSession();
    return (Parcel) session.get(Parcel.class, id);
  }

  Parcel save(Parcel parcel) {
    Session session = sessionFactory.getCurrentSession();
    return (Parcel) session.merge(parcel);
  }

  void update(Parcel parcel) {
    Session session = sessionFactory.getCurrentSession();
    session.update(parcel);
  }

  void delete(Parcel parcel) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(parcel);
  }
}
