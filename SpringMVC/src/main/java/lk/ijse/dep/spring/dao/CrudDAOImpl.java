package lk.ijse.dep.spring.dao;


import lk.ijse.dep.spring.entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID>{

    @Autowired
    protected SessionFactory sessionFactory;
    private Class<T> entity;

    public CrudDAOImpl(){
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<T> findAll()  {
        return getSession().createQuery("FROM " + entity.getName()).list();
    }

    @Override
    public T find(ID id)  {
        return getSession().get(entity, id);
    }

    @Override
    public void delete(ID id)  {
        getSession().delete(getSession().load(entity, id));
    }

    @Override
    public void save(T entity)  {
        getSession().save(entity);
    }

    @Override
    public void update(T entity)  {
        getSession().merge(entity);
    }
}
