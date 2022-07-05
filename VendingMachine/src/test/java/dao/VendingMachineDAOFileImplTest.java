package dao;

import dto.Item;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDAOFileImplTest
{

    VendingMachineDAO testDao;

    public VendingMachineDAOFileImplTest()
    {
    }

    @BeforeAll
    static void setUpClass()
    {
    }

    @AfterAll
    static void tearDownClass()
    {
    }

    @BeforeEach
    void setUp() throws Exception
    {
        String testFile = "TestVending.txt";
        PrintWriter writer = new PrintWriter(new FileWriter(testFile));
        writer.println("A1::CHOCOLATE BAR::1.10::5");
        writer.println("A2::CRISPS::0.95::10");
        writer.println("A3::CHEWING GUM::1.00::0");
        writer.println("A4::CAN OF COKE::1.25::5");
        writer.flush();
        testDao = new VendingMachineDAOFileImpl(testFile);
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    public void testGetAllItems() throws VendingMachineDAOException
    {
        Map<String, Item> items = testDao.getAllItems();

        assertNotNull(items, "The list of items should not be null");

        assertEquals(4, items.size());
    }

    @Test
    public void testGetItem() throws NoItemInventoryException, VendingMachineDAOException
    {
        Item item = testDao.getItem("A1");

        assertNotNull(item, "Retrieved item should not be null");
        assertEquals(item.getId(), "A1", "Item id should be A1");
        assertEquals(item.getName(), "CHOCOLATE BAR", "Item name should be CHOCOLATE BAR");
    }

    @Test
    public void testVendItem() throws NoItemInventoryException, VendingMachineDAOException
    {
        Item item = testDao.getItem("A1");
        Item vendedItem = testDao.vendItem(item);

        assertNotNull(vendedItem, "Vended Item should not be null");
        assertEquals(item.getId(), vendedItem.getId(), "The item IDs should match");
        assertEquals(item.getName(), vendedItem.getName(), "The item names should match");
        assertEquals(item.getPrice(), vendedItem.getPrice(), "The item prices should match");
        assertEquals(item.getQty(), vendedItem.getQty(), "The item quantity should match");


    }
}