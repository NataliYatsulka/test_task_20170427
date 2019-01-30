package com.opinta.dao;

import com.opinta.entity.ParcelItem;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ParcelItemDaoImpl implements ParcelItemDao {
  private final SessionFactory sessionFactory;

  @Autowired
  public ParcelItemDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<ParcelItem> getAll() {
    Session session = sessionFactory.getCurrentSession();
    return session.createCriteria(ParcelItem.class)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .list();
  }

  @Override
  public ParcelItem getById(long id) {
    Session session = sessionFactory.getCurrentSession();
    return (ParcelItem) session.get(ParcelItem.class, id);
  }

  @Override
  public ParcelItem save(ParcelItem parcelItem) {
    Session session = sessionFactory.getCurrentSession();
    return (ParcelItem) session.merge(parcelItem);
  }

  @Override
  public void update(ParcelItem parcelItem) {
    Session session = sessionFactory.getCurrentSession();
    session.update(parcelItem);
  }

  @Override
  public void delete(ParcelItem parcelItem) {
    Session session = sessionFactory.getCurrentSession();
    session.delete(parcelItem);
  }
}
