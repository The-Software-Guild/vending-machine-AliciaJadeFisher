package dao;

import dto.Item;

import java.util.Map;

public interface VendingMachineDAO
{
    Map<String, Item> getAllItems() throws VendingMachineDAOException;

    Item vendItem(Item item) throws VendingMachineDAOException;

    Item getItem(String id) throws VendingMachineDAOException, NoItemInventoryException;

}
