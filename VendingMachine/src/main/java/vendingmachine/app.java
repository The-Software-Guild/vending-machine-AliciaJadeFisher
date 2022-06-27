package vendingmachine;

import controller.VendingMachineController;
import dao.*;
import service.InsufficientFundsException;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class app
{
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
