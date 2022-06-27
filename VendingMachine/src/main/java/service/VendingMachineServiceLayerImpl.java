package service;

import dao.*;
import dto.Coins;
import dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer
{
    VendingMachineDAO dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDAO dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    @Override
    public Map<String, Item> getAllItems() throws VendingMachineDAOException
    {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String name) throws VendingMachineDAOException, NoItemInventoryException
    {
        return dao.getItem(name);
    }

    @Override
    public Map<Coins, Integer> vendItem(String name, BigDecimal amount) throws VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        Item item = getItem(name);
        BigDecimal price = item.getPrice();

        int compareAmount = amount.compareTo(price);
        if(compareAmount < 0)
        {
            throw new InsufficientFundsException("-_- Insufficient funds, cannot vend this item:" + amount);
        }

        Change change = new Change(amount, price);
        return change.getChange();
    }
}
