package controller;

import dao.NoItemInventoryException;
import dao.VendingMachineDAOException;
import dao.VendingMachinePersistenceException;
import dto.Coins;
import dto.Item;
import service.InsufficientFundsException;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Class which controls the program flow
 */
public class VendingMachineController
{
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    /**
     * Main constructor which sets the view and service layer objects
     * @param view - program view object
     * @param service - program service layer object
     */
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service)
    {
        this.view = view;
        this.service = service;
    }

    /**
     * Main program run method
     * @throws VendingMachinePersistenceException
     * @throws VendingMachineDAOException
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     */
    public void run() throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        // Calls methods to display main menu and get user inputs
        view.displayMainBanner();
        view.printMainMenu(service.getAllItems());

        BigDecimal amount = getAmount();
        Item itemSelection;
        if(amount != null)
        {
            itemSelection = getItemSelection();

            if(itemSelection != null)
            {
                vendItem(itemSelection, amount);
            }
        }

        view.displayEndBanner();
    }

    /**
     * Method which controls the process of displaying the main menu to the user and getting the user's money amount
     * @return user's amount of money
     * @throws VendingMachinePersistenceException
     * @throws VendingMachineDAOException
     */
    public BigDecimal getAmount() throws VendingMachinePersistenceException, VendingMachineDAOException
    {
        return view.getAmount();
    }

    /**
     * Method which controls the process of getting an item choice from the user
     * @return name of chosen item
     */
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

    /**
     * Method which controls the process of vending a selected item
     * @param item - item to vend
     * @param amount - user's amount of money
     * @throws VendingMachinePersistenceException
     * @throws VendingMachineDAOException
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     */
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
