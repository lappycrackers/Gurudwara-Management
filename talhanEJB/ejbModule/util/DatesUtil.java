package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.Slot;




public class DatesUtil {
ArrayList<Slot> dt_al= new ArrayList<Slot>();
public ArrayList<Slot> inputSlot(int days, int beed){
	
try{
	for (int i=1;i<=beed;i++){
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		for(int j=1;j<=days;j++){
			c.add(Calendar.DATE, 1);
			Date dt = c.getTime();
			c.setTime(dt);
			if(c.get(Calendar.DAY_OF_WEEK)%2==0){
				Slot obj = new Slot();
				obj.setAvailDate(dt);
				obj.setBeedNumber(i);
				obj.setBlockStatus(0);
				obj.setStatus(0);
				obj.setToken(0);
				dt_al.add(obj);
			}
		}
	
		}
	return dt_al;
}catch(Exception e){
	System.out.println("Error " +e);
	return null;
}
}
}