package service;

import dao.NoItemInventoryException;
import dao.VendingMachineDAOException;
import dao.VendingMachinePersistenceException;
import dto.Coins;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public interface VendingMachineServiceLayer
{
    Map<String, Item> getAllItems() throws VendingMachinePersistenceException, VendingMachineDAOException;

    Item getItem(String id) throws VendingMachinePersistenceException, VendingMachineDAOException, NoItemInventoryException;

    Map<Coins, Integer> vendItem(Item item, BigDecimal amount) throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException;
}
