package team240;

import battlecode.common.*;

public class SoldierControl{
	static RobotController rc;
	static int band;
	static int attempts[] = {0, 1, -1, 2, -2, 3, -3};
	static Direction dirs[] = Direction.values();
	public static void run(RobotController rci) throws Exception{
		rc = rci;
		band = 1;
		//basicMove();
		while(true){
			try{
				attemptAttack();
				obey();
				rc.yield();
			} catch (Exception e){
				e.printStackTrace();
			}
		}	
	}
	public static void obey() throws Exception{
		if (rc.isActive()) {
			int to = rc.readBroadcast(band*100);
			MapLocation dest = Util.decodeLocation(to);
			Direction dir = rc.getLocation().directionTo(dest);
			int dirnum = dir.ordinal();
			//moving somewhere is better than nowhere
			for (int i = 0; i<attempts.length; i++){
				if (rc.isActive() && rc.canMove(dirs[(dirnum+attempts[i]+8)%8])) {
					rc.move(dirs[(dirnum+attempts[i]+8)%8]);
				}
			}
		}
	}
	public static void basicMove() throws Exception{
		if (rc.isActive() && rc.canMove(Direction.EAST)) {
			rc.move(Direction.EAST);
		}
	}
	public static void attemptAttack() throws Exception{
		Robot enemies[] = rc.senseNearbyGameObjects(Robot.class, 35, rc.getTeam().opponent());
		MapLocation loc = null;
		if (enemies.length > 0 && rc.isActive()){
			 loc = rc.senseLocationOf(enemies[0]);
			if (rc.canAttackSquare(loc)) {
				rc.attackSquare(rc.senseLocationOf(enemies[0]));
			}
		}
		System.out.println(loc);
	}
}
