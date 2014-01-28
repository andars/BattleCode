package team240;

import battlecode.common.*;

public class SoldierControl{
	static RobotController rc;
	static int band;
	static Direction dirs[] = Direction.values();
	public static void run(RobotController rci) throws Exception{
		rc = rci;
		band = 1;
		//basicMove();
		while(true){
			obey();
			attemptAttack();
			rc.yield();
		}	
	}
	public static void obey() throws Exception{
		if (rc.isActive()) {
			int to = rc.readBroadcast(band*100);
			System.out.println(to);
			MapLocation dest = Util.decodeLocation(to);
			Direction dir = rc.getLocation().directionTo(dest);
			if (rc.canMove(dir)) {
				rc.move(dir);
			}
		}
	}
	public static void basicMove() throws Exception{
		if (rc.isActive() && rc.canMove(Direction.EAST)) {
			rc.move(Direction.EAST);
		}
	}
	public static void attemptAttack() throws Exception{
		
	}
}
