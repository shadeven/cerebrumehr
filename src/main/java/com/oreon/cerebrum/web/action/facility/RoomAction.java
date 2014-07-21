
	
package com.oreon.cerebrum.web.action.facility;
	

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.facility.Bed;

	
//@Scope(ScopeType.CONVERSATION)
@Name("roomAction")
public class RoomAction extends RoomActionBase implements java.io.Serializable{

	public void createRooms(){
		
		if(!isNew())
			return;
		
		Integer rooms = instance.getRoomType().getNumberOfBeds();
		
		if(rooms != null && rooms > 0 ){
			listBeds.clear();
			
			for (int i = 0; i < rooms; i++) {
				Bed bed = new Bed();
				bed.setName(instance.getName() + "-" +"B" + (i + 1));
				bed.setRoom(instance);
				listBeds.add(bed);
			}
		}
	}
}
	