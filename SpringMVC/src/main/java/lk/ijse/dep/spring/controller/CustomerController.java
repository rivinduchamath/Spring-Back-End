package lk.ijse.dep.spring.controller;

import lk.ijse.dep.spring.business.custom.CustomerBO;
import lk.ijse.dep.spring.dto.CustomerDTO;
import lk.ijse.dep.spring.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customers")
@CrossOrigin
@RestController
public class CustomerController {
    @Autowired
    private CustomerBO customerBO;

    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerBO.findAllCustomers();
    }


    /*   @GetMapping("/{id}")
       public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String id) {
           try {
               CustomerDTO customer = customerBO.findCustomer(id);
               return new ResponseEntity<>(customer, HttpStatus.OK);
                } catch (NullPointerException e) {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       }*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO getCustomer(@PathVariable String id) {
        try {
            return customerBO.findCustomer(id);
        } catch (NullPointerException e) {
            throw new NotFoundException(e);
        }
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity handleNotFoundException(Exception e) {
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }


    @GetMapping(params = "q=last")
    public String getLastCustomerID() {
        return customerBO.getLastCustomerId();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCustomer(@RequestBody CustomerDTO customer) {
        customerBO.saveCustomer(customer);
        return "\"" +customer.getId() + "\"";
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerBO.deleteCustomer(id);
    }

   /* @PutMapping("/{id}")
    public ResponseEntity updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customer){
       if(id.equals(customer.getId())){
           customerBO.updateCustomer(customer);
           return new ResponseEntity(HttpStatus.NO_CONTENT);
       } else {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }*/
   @PutMapping("/{id}")
   public void updateCustomer(@PathVariable String id,
                              @RequestBody CustomerDTO customer){
     customer.setId(id);
     customerBO.updateCustomer(customer);
   }

   @GetMapping(params = "category=id")
   public List<String> getAllCustomersId(){
       return customerBO.getAllCustomerIDs();
   }

//   @GetMapping
//    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
//       List<CustomerDTO> allCustomers = customerBO.findAllCustomers();
//
//   }
}











