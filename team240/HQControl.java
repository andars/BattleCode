package team240;
import battlecode.common.*;
import java.util.*;
public class HQControl{
	//
	static RobotController rc;
	static MapLocation pastrs[];
	public static void run(RobotController rci) throws Exception{
		rc = rci;
		pastrs = rc.sensePastrLocations(rc.getTeam().opponent());

		while(true){
			if (Clock.getRoundNum()%10 == 0) {
				pastrs = rc.sensePastrLocations(rc.getTeam().opponent());
			}
			System.out.println(Arrays.toString(pastrs));
			command();
			attemptSpawn();
			rc.yield();
		}	
	}
	public static void attemptSpawn() throws Exception{
		if (rc.isActive() && rc.canMove(Direction.NORTH)) {
			rc.spawn(Direction.NORTH);
		}
	}
	/*
		Gives commands/locations to the different squadrons
		Squad 1 goes from channel 100 to 199, 2 from 200 to 299, etc.
		Negative numbers are control commands (switch squad, etc.)
	*/
	public static void command() throws Exception{
		int dest = 505;
		if (pastrs.length > 0){
			MapLocation nearest = Util.nearest(pastrs, rc.getLocation());
			dest = Util.encodeLocation(nearest);
		}
		
		rc.broadcast(100,dest);
	}
}
