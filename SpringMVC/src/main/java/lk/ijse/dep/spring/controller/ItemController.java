package lk.ijse.dep.spring.controller;

import lk.ijse.dep.spring.business.custom.CustomerBO;
import lk.ijse.dep.spring.business.custom.ItemBO;
import lk.ijse.dep.spring.dto.CustomerDTO;
import lk.ijse.dep.spring.dto.ItemDTO;
import lk.ijse.dep.spring.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/items")
@CrossOrigin
@RestController
public class ItemController {
    @Autowired
    private ItemBO itemBO;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemBO.findAllItems();
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
    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO getItem(@PathVariable String id) {
        try {
            return itemBO.findItem(id);
        } catch (NullPointerException e) {
            throw new NotFoundException(e);
        }
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNotFoundException(Exception e) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @GetMapping(params = "q=last")
    public String getLAstItem() {
        return itemBO.getLastItemCode();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveCustomer(@RequestBody ItemDTO itemDTO) {
        itemBO.saveItem(itemDTO);
        return "\"" +itemDTO.getCode() + "\"";
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteItem(@PathVariable String id) {
        itemBO.deleteItem(id);
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
   @PutMapping("/{code}")
   public void updateItem(@PathVariable String id,
                              @RequestBody ItemDTO item){
     item.setCode(id);
     itemBO.updateItem(item);
   }

   @GetMapping(params = "category=code")
   public List<String> getAllItemIDs(){
       return itemBO.getAllItemCodes();
   }
}











