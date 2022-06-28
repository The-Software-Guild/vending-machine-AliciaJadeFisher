package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Class which handles writing to the audit file
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao
{
    public static final String AUDIT_FILE = "AuditItems.txt";

    /**
     * Appends new audit entry to the file
     * @param entry - entry for file
     * @throws VendingMachinePersistenceException
     */
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException
    {
        PrintWriter writer;

        try
        {
            writer = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }
        catch(IOException e)
        {
            throw new VendingMachinePersistenceException("-_- Could not persist audit information.", e);
        }

        LocalDateTime timeStamp = LocalDateTime.now();
        writer.println(timeStamp.toString() + " : " + entry);
        writer.flush();
        writer.close();
    }
}
