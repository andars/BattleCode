package team240;
import battlecode.common.*;

public class Util{
	public static RobotInfo[] getInfo(Robot bots[], RobotController rc) throws Exception{
		if (bots.length < 1){
			return new RobotInfo[]{};
		}
		RobotInfo[] info = new RobotInfo[bots.length];

		for (int i = 0; i<bots.length; i++){
			info[i] = rc.senseRobotInfo(bots[i]);
		}

		return info;
	}
	/*
		El Cheapo
		This method costs 10 bc on the object, so here goes
	*/
	public static int distanceSquared(MapLocation l1, MapLocation l2){
		int d =  (l2.x - l1.x)*(l2.x - l1.x) + (l2.y - l1.y)*(l2.y - l1.y);  
		return d;
	}

	public static MapLocation nearest(MapLocation[] locs, MapLocation center){
		int minidx = 0;
		int curr = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i<locs.length; i++){
			curr = distanceSquared(locs[i], center);
			if (curr < min){
				min = curr;
				minidx = i;
			}
		}
		return locs[minidx];

	}
	/*
		Encodes a map location as an integer for broadcasting
	*/	
	public static int encodeLocation(MapLocation loc){
		return loc.x*100 + loc.y;
	}

	/*
		Decodes an integer representing a map location
	*/
	public static MapLocation decodeLocation(int encoded){
		return new MapLocation(encoded/100, encoded%100);
	}
}
