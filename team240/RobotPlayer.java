package team240;

import java.util.Random;

import battlecode.common.*;



public class RobotPlayer{
    static Random rand = new Random();



    public static void run(RobotController rc) throws GameActionException{
    	
    	if (rc.getType() == RobotType.SOLDIER){
			try{
				SoldierControl.run(rc);
  			} catch(Exception e) {
				e.printStackTrace();
   			}
   		}
   		else if (rc.getType() == RobotType.HQ){
			try{
				HQControl.run(rc);
   			} catch(Exception e) {
				e.printStackTrace();
    		}
		 }
	}
}
