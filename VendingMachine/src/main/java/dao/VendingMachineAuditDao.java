package dao;

/**
 * Interface which represents the program's audit data access object
 */
public interface VendingMachineAuditDao
{
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
