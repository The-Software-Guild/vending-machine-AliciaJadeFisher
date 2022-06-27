package dao;

import dto.Item;

import java.util.Map;

public interface VendingMachineDAO
{
    Map<String, Item> getAllItems() throws VendingMachineDAOException;

    Item itemVended(String name, Item item) throws VendingMachineDAOException;

    Item getItem(String name) throws VendingMachineDAOException, NoItemInventoryException;

}
