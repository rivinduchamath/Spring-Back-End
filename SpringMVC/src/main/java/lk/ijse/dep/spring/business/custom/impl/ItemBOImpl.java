package lk.ijse.dep.spring.business.custom.impl;


import lk.ijse.dep.spring.business.custom.ItemBO;
import lk.ijse.dep.spring.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.spring.dao.custom.ItemDAO;
import lk.ijse.dep.spring.dao.custom.OrderDetailDAO;
import lk.ijse.dep.spring.dto.ItemDTO;
import lk.ijse.dep.spring.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class ItemBOImpl implements ItemBO {

    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO item)  {

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            session.beginTransaction();
            itemDAO.save(new Item(item.getCode(),
                    item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
 //           session.getTransaction().commit();
  //      }
    }

    @Override
    public void updateItem(ItemDTO item)  {
//         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            session.beginTransaction();
            itemDAO.update(new Item(item.getCode(),
                    item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
//            session.getTransaction().commit();
//        }
    }

    @Override
    public void deleteItem(String itemCode)  {

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            orderDetailDAO.setSession(session);
//            session.beginTransaction();
            if (orderDetailDAO.existsByItemCode(itemCode)){
                throw new RuntimeException("Item already exists in an order, hence unable to delete");
            }
            itemDAO.delete(itemCode);
//            session.getTransaction().commit();
//
//        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDTO> findAllItems() {


//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            session.beginTransaction();
            List<Item> allItems = itemDAO.findAll();
            List<ItemDTO> dtos = new ArrayList<>();
            for (Item item : allItems) {
                dtos.add(new ItemDTO(item.getCode(),
                        item.getDescription(),
                        item.getQtyOnHand(),
                        item.getUnitPrice()));
            }

//            session.getTransaction().commit();

            return dtos;


//        }

    }

    @Transactional(readOnly = true)
    @Override
    public String getLastItemCode() {

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            session.beginTransaction();
            String lastItemCode = itemDAO.getLastItemCode();
 //           session.getTransaction().commit();

            return lastItemCode;

     //   }
    }

    @Transactional(readOnly = true)
    @Override
    public ItemDTO findItem(String itemCode)  {

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            session.beginTransaction();
            Item item = itemDAO.find(itemCode);
 //           session.getTransaction().commit();

            return new ItemDTO(item.getCode(),
                    item.getDescription(),
                    item.getQtyOnHand(),
                    item.getUnitPrice());

//        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllItemCodes() {


//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            itemDAO.setSession(session);
//            session.beginTransaction();
            List<Item> allItems = itemDAO.findAll();
            List<String> codes = new ArrayList<>();
            for (Item item : allItems) {
                codes.add(item.getCode());
            }

 //           session.getTransaction().commit();

            return codes;

 //       }
    }
}
