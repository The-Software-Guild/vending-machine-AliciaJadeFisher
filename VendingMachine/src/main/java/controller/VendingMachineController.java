package controller;

import dao.NoItemInventoryException;
import dao.VendingMachineDAOException;
import dao.VendingMachinePersistenceException;
import dto.Coins;
import dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.InsufficientFundsException;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineController
{
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service)
    {
        this.view = view;
        this.service = service;
    }

    public void run() throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        view.displayMainBanner();
        view.printMainMenu(service.getAllItems());

        BigDecimal amount = getAmount();

        if(amount != null)
        {
            Item itemSelection = getItemSelection();

            if(itemSelection != null)
            {
                vendItem(itemSelection, amount);
            }
        }

        view.displayEndBanner();
    }

    public BigDecimal getAmount() throws VendingMachinePersistenceException, VendingMachineDAOException
    {
        return view.getAmount();
    }

    public Item getItemSelection() throws NoItemInventoryException, VendingMachinePersistenceException, VendingMachineDAOException
    {
        Item item;
        do
        {
            String choice = view.getItemChoice();

            if(choice.equals("0"))
            {
               return null;
            }

            item = service.getItem(choice);

        } while (item == null);

        return item;
    }

    public void vendItem(Item item, BigDecimal amount) throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        Map<Coins,Integer> change;

        while(true)
        {
            change = service.vendItem(item, amount);
            if(change == null)
            {
                getItemSelection();
            }
            else
            {
                break;
            }
        }

        view.displayChange(change);
    }
}