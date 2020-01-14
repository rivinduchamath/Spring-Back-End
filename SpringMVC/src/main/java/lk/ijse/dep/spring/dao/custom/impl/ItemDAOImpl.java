package lk.ijse.dep.spring.dao.custom.impl;

import lk.ijse.dep.spring.dao.CrudDAOImpl;
import lk.ijse.dep.spring.dao.custom.ItemDAO;
import lk.ijse.dep.spring.entity.Item;
import org.springframework.stereotype.Repository;

//@Component venuvata @Repository annotation eka use kala yuthui..
@Repository
public class ItemDAOImpl extends CrudDAOImpl<Item, String> implements ItemDAO {


    @Override
    public String getLastItemCode()  {
       return (String) getSession().createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1").uniqueResult();

    }

//    @Override
//    public List<Item> findAll() throws Exception {
//        return session.createQuery("from Item i", Item.class).list();
//    }
//
//    @Override
//    public Item find(String itemCode) throws Exception {
//        return session.get(Item.class,itemCode);
//    }
//
//    @Override
//    public void save(Item item) throws Exception {
//        session.save(item);
//    }
//
//    @Override
//    public void update(Item item) throws Exception {
//    session.merge(item);
//      }
//
//    @Override
//    public void delete(String itemCode) throws Exception {
//        session.delete(session.load(Item.class,itemCode));
//    }
//
//    @Override
//    public void setSession(Session session) {
//        this.session =session;
//    }
}
