package vendingmachine;

import controller.VendingMachineController;
import dao.*;
import service.InsufficientFundsException;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

/**
 * Main app class for the application
 */
public class app
{
    /**
     * Instantiates required classes for the program to run and calls the controller's run() method
     * @param args
     * @throws VendingMachinePersistenceException
     * @throws VendingMachineDAOException
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     */
    public static void main(String[] args) throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDAO myDao = new VendingMachineDAOFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myView, myService);

        controller.run();
    }
}
