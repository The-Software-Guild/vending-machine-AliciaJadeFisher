package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDAO;
import dao.VendingMachineDAOException;
import dao.VendingMachinePersistenceException;
import dto.Coins;
import dto.Item;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceLayerImplTest
{
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest()
    {
        VendingMachineDAO dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @BeforeEach
    void setUp()
    {
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    public void testGetAllItems() throws Exception{
        Map<String, Item> items = service.getAllItems();

        assertNotNull(items, "Items should not be null");
        assertEquals(2,items.size(), "Items should only have one item");
    }

    @Test
    public void testGetItem() throws Exception{
        Item item = service.getItem("A1");

        assertNotNull(item, "Retrieved item should not be null");
        assertEquals(item.getId(), "A1", "Item id should be A1");
        assertEquals(item.getName(), "CHOCOLATE BAR", "Item name should be CHOCOLATE BAR");
    }

    @Test
    public void testGetVendItemNoFunds() throws Exception{

        Item item = service.getItem("A1");
        Map< Coins, Integer>  change = service.vendItem(item, BigDecimal.valueOf(0.0));

        assertNull(change, "There should be no change as an item was not vended, do to not having any money");
    }

    @Test
    public void testGetVendItemHasFunds() throws Exception{

        Item item = service.getItem("A1");
        Map< Coins, Integer>  change = service.vendItem(item, BigDecimal.valueOf(1.15));

        assertNotNull(change, "There should be change as an item was vended");
        assertEquals(change.get(Coins.QUARTER), 0, "There should be no quarters in the change");
        assertEquals(change.get(Coins.DIME), 0, "There should be no dimes in the change");
        assertEquals(change.get(Coins.NICKEL),1, "There should be one nickel in the change");
        assertEquals(change.get(Coins.PENNY), 0, "There should be no pennies in the change");
    }

    @Test
    public void testGetVendItemNoInventory() throws Exception{

        Item item = service.getItem("A2");
        Map< Coins, Integer>  change = service.vendItem(item, BigDecimal.valueOf(1.15));

        assertNotNull(change, "There should be change as an item was not vended, due to it having no inventory");
    }


}