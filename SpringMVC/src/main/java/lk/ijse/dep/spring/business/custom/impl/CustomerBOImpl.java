package lk.ijse.dep.spring.business.custom.impl;


import lk.ijse.dep.spring.business.custom.CustomerBO;
import lk.ijse.dep.spring.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.spring.dao.custom.CustomerDAO;
import lk.ijse.dep.spring.dao.custom.OrderDAO;
import lk.ijse.dep.spring.dto.CustomerDTO;
import lk.ijse.dep.spring.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private OrderDAO orderDAO;

    @Override
    public void saveCustomer(CustomerDTO customer)  {
            customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
    }

    @Override
    public void updateCustomer(CustomerDTO customer) {
            customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
  }

    @Override
    public void deleteCustomer(String customerId)  {
            if (orderDAO.existsByCustomerId(customerId)) {
                throw new RuntimeException("Customer already exists in an order, hence unable to delete");
            }
            customerDAO.delete(customerId);
}

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers()  {
            List<Customer> alCustomers = customerDAO.findAll();
            List<CustomerDTO> dtos = new ArrayList<>();
            for (Customer customer : alCustomers) {
                dtos.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress()));
            }
            return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public String getLastCustomerId()  {
            String lastCustomerId = customerDAO.getLastCustomerId();
            return lastCustomerId;
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomer(String customerId)  {
            Customer customer = customerDAO.find(customerId);
            return
            new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress());
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllCustomerIDs(){

            List<Customer> customers = customerDAO.findAll();
            List<String> ids = new ArrayList<>();
            for (Customer customer : customers) {
                ids.add(customer.getCustomerId());
            }
            return ids;
    }
}
