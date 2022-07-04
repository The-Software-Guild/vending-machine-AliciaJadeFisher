package dao;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao
{
    public static final String AUDIT_FILE = "AuditItems.txt";

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
