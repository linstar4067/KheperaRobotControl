import edu.wsu.KheperaSimulator.RobotController;


public class BangBangController extends RobotController {
	
	private SensorManager sensorMgr;
	
	
	private double baseSpeed = 5;
	
	
	/**
	 * motor speed left
	 */
	private int mL = 0;
	
	/**
	 * motor speed right
	 */
	private int mR = 0;
	
	

	public BangBangController() {
		init();
	}
	
	private void init() {
		sensorMgr = new SensorManager(this);
		setWaitTime(20);		
	}


	@Override
	public void doWork() throws Exception {
		sensorMgr.update();
		calculateOutputs();
		writeOutputs();
	}

	private void writeOutputs() {
		System.out.println("Set motor speed to " + mL + "|" + mR);
		setMotorSpeeds(mL, mR);
	}

	private void calculateOutputs() {
		Sensor min = sensorMgr.getLowestLightValue();
		System.out.println("min light value has sensor " + min.getName());
		
		if(min.isFront()) {
			if(0 <= min.getSide()) {
				// is on right side
				mL = (int) baseSpeed;
				mR = (int)(baseSpeed * min.getSide());
			} else {
				// is on left side
				mR = (int) baseSpeed;
				mL = (int)(baseSpeed * -min.getSide());
			}
		} else {
			// drive backward or turn
			mL = 0;
			mR = (int)baseSpeed;
//			mL = (int)(-baseSpeed * min.getSide());
//			mR = (int)(-baseSpeed * -min.getSide());
		}
	}


	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

	public double getBaseSpeed() {
		return baseSpeed;
	}

	private void setBaseSpeed(double baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

}
