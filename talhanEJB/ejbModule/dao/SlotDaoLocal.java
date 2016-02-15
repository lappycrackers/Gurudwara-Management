package dao;

import java.util.Date;

import javax.ejb.Local;

import model.Slot;

@Local
public interface SlotDaoLocal {
public void addSlot(Slot sl);
public void releaseMemory();
public Date getMaxDate();
public Date getMinDate();
public Date getTodayDate();
public Date getNextValidDate(Date dt);
public void update(Date dt,Date ndt);
public void booking(String dt);
public boolean isAvailable(Date dt);
public boolean isUpdateRequired();
}
