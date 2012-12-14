
public class DriveAlongWallProportionalAction extends Action {

	
	private float[][] controlMatrix = new float[][] { 
			new float[] { 0.0f,	 6.50f, 60f, 	60f, 	60f, 	60f, 	-60f, 	-60f	},
			new float[] { 6.25f, 0.00f,	-6.50f, -6.50f, -6.50f, -6.50f, 0.00f, 	0f 		} 
			}; 
	private float PROP_BASE_SPEED = 5;
	private final int MAX_DISTANCE_VALUE = 1023;
	
	public DriveAlongWallProportionalAction(ActionContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doAction() {
		float left = 0;
		float right = 0;
		
		float[] sensors = getSensorManager().getSensorDistanceVector();

		for (int i = 0; i < sensors.length; i++) {
			left += controlMatrix[0][i] * sensors[i];
			right += controlMatrix[1][i] * sensors[i];
		}

		left = left / MAX_DISTANCE_VALUE;
		right = right / MAX_DISTANCE_VALUE;
		
		getMotionManager().setMotorSpeeds((int)(PROP_BASE_SPEED * 0 + (left) * PROP_BASE_SPEED), (int)(PROP_BASE_SPEED * 0 + (right)* PROP_BASE_SPEED));
		

	}

}