package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Slot;

/**
 * Session Bean implementation class SlotDao
 */
@Stateless
@LocalBean
public class SlotDao implements SlotDaoLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public SlotDao() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addSlot(Slot sl) {
		// TODO Auto-generated method stub
		em.persist(sl);
	}

	@Override
	public void releaseMemory() {
		// TODO Auto-generated method stub
		em.flush();
		em.clear();
		
	}

	@Override
	public Date getMaxDate() {
		// TODO Auto-generated method stub
		Slot st;
		CriteriaBuilder cb =em.getCriteriaBuilder();
		CriteriaQuery<Slot> cq = cb.createQuery(Slot.class);
		Root<Slot> from = cq.from(Slot.class);
		cq.select(from);
		cq.orderBy(cb.desc(from.get("availDate")));
		st= em.createQuery(cq).setMaxResults(1).getSingleResult();
	//	System.out.println(st.getAvailDate());
		return st.getAvailDate();
		
	}

	@Override
	public Date getMinDate() {
		// TODO Auto-generated method stub
		Slot st;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Slot> cq = cb.createQuery(Slot.class);
		Root<Slot> from = cq.from(Slot.class);
		cq.select(from);
		cq.orderBy(cb.asc(from.get("availDate")));
		st = em.createQuery(cq).setMaxResults(1).getSingleResult();
	//	System.out.println(st.getAvailDate());
		return st.getAvailDate();
	}

	

	@Override
	public Date getTodayDate() {
		// TODO Auto-generated method stub
		//Date date = new Date();
		Calendar c = Calendar.getInstance();
		 
		return c.getTime();
	}

	@Override
	public Date getNextValidDate(Date dt) {
		// TODO Auto-generated method stub
		Calendar c1 = Calendar.getInstance();
		c1.setTime(dt);
		do{
			c1.add(Calendar.DATE, 1);
			
		}while(c1.get(Calendar.DAY_OF_WEEK)%2 !=0);
		return c1.getTime();
	}

	@Override
	public void update(Date dt, Date ndt) {
		// TODO Auto-generated method stub
		Query a = em.createNativeQuery("Update slot slot set slot.avail_date= :rd where slot.avail_date=:od");
		a.setParameter("rd", ndt);
		a.setParameter("od", dt);
		System.out.println("running");
		int i = a.executeUpdate();
		System.out.println("Value of i is :"+i);
	}

//	@Override
//	public void booking(Date dt){                                                                                                                                                                                                                                               te dt) {
//		// TODO Auto-generated method stub
//		Query a = em.createNativeQuery("Update slot slot set slot.staus ='1' where avail_date=ad");
//		a.setParameter("ad", dt);
//		a.executeUpdate();
//	}

	@Override
	public boolean isAvailable(Date dt) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public void booking(String dt) {
		// TODO Auto-generated method stub
		Query a = em.createNativeQuery("Update slot slot set slot.status ='1' where avail_date=:ad LIMIT 1");
		a.setParameter("ad", dt);
		int i=a.executeUpdate();
		System.out.println("Value of  i = "+i);
	}

	@Override
	public boolean isUpdateRequired() {
		// TODO Auto-generated method stub
		int comp;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//System.out.println("Initial value of comp : "+comp);
		comp= dateFormat.format(getMinDate()).compareTo(dateFormat.format(getTodayDate()));
		System.out.println("Value of comp : "+comp);
		System.out.println(dateFormat.format(getMinDate()));
		System.out.println(dateFormat.format(getTodayDate()));
		if(comp<=0)
		{
			System.out.println("true");
			return true;
		}else{
			System.out.println("false");
			return false;
		}
	}
	

}
