package lk.ijse.dep.spring.business.custom;



import lk.ijse.dep.spring.business.SuperBO;
import lk.ijse.dep.spring.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {

    void saveItem(ItemDTO itemDTO) ;

    void updateItem(ItemDTO item) ;

    void deleteItem(String itemCode) ;

    List<ItemDTO> findAllItems() ;

    String getLastItemCode() ;

    ItemDTO findItem(String itemCode) ;

    List<String> getAllItemCodes() ;

}
