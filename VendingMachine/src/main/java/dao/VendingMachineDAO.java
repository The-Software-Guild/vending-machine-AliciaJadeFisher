package dao;

import dto.Item;

import java.util.Map;

/**
 * Interface which represents the program's data access object
 */
public interface VendingMachineDAO
{
    Map<String, Item> getAllItems() throws VendingMachineDAOException;

    Item vendItem(Item item) throws VendingMachineDAOException;

    Item getItem(String name) throws VendingMachineDAOException, NoItemInventoryException;

}
