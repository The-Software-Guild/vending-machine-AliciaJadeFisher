package vendingmachine;

import controller.VendingMachineController;
import dao.NoItemInventoryException;
import dao.VendingMachineDAOException;
import dao.VendingMachinePersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.InsufficientFundsException;

public class app
{
    public static void main(String[] args) throws VendingMachinePersistenceException, VendingMachineDAOException, InsufficientFundsException, NoItemInventoryException
    {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = appContext.getBean("controller", VendingMachineController.class);
        controller.run();


//        VendingMachineController controller = appContext.getBean("vendingMachineController", VendingMachineController.class);
//        controller.run();


//        UserIO myIo = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIo);
//        VendingMachineDAO myDao = new VendingMachineDAOFileImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
//        VendingMachineController controller = new VendingMachineController(myView, myService);
//
//        controller.run();
    }
}
