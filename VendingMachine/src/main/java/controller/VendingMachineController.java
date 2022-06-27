package controller;

import dao.NoItemInventoryException;
import dao.VendingMachineDAOException;
import dao.VendingMachinePersistenceException;
import dto.Coins;
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
        BigDecimal amount = new BigDecimal("0.00");
        String itemSelection = "";

        amount = getAmount();
        itemSelection = getItemSelection();
        vendItem(itemSelection, amount);
        view.displayEndBanner();
    }

    public BigDecimal getAmount() throws VendingMachinePersistenceException, VendingMachineDAOException
    {
        view.printMainMenu(service.getAllItems());
        return view.getAmount();
    }

    public String getItemSelection()
    {
        return view.getItemChoice();
    }

    public void vendItem(String item, BigDecimal amount) throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        Map<Coins, Integer> change = service.vendItem(item, amount);
        view.displayChange(change);
    }

}
