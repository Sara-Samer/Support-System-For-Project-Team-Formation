public class TeamExperience{
	private float[] beginner = new float[]{0,15, 30}; 
	private float[] intermediate = new float[]{15, 30, 45}; 
	private float[] expert = new float[]{30, 60 ,60}; 
	private float targetTeamExperience;
	
	public TeamExperience() {
		targetTeamExperience = 0;
	}
	public TeamExperience(float projectFunding) {
		targetTeamExperience = projectFunding;
	}
	
	public float geta(float x1, float y1, float x2, float y2) {
		return ((y2 - y1) / (x2 - x1));
	}
	
	public float getb(float x , float y, float a) {
		return y - (a * x);
	}
	
	public float calculateY(float x, float[] line) {
		float y = 0;
		float x1, y1, x2, y2;
		if(x >= line[0] && x < line[1]) {
			x1 = line[0]; y1 = 0;
			x2 = line[1]; y2 = 1;
		}
		else if(x >= line[1] && x <= line[2]) {
			x1 = line[1]; y1 = 1;
			x2 = line[2]; y2 = 0;
		}
		else {
			return 0;
		}
		float a = geta(x1, y1, x2, y2);
		y = (a * x) + getb(x1, y1, a);
		return y;
	}
	
	
	public float[] getRange() {
		float[] lineIntersection = new float[]{0, 0, 0};
		if(targetTeamExperience <= beginner[0] && targetTeamExperience >= beginner[2]) {
			lineIntersection[0] = calculateY(targetTeamExperience, beginner);
		}
		if(targetTeamExperience <= intermediate[0] && targetTeamExperience >= intermediate[2]) {
			lineIntersection[1] = calculateY(targetTeamExperience, intermediate);
		}
		if(targetTeamExperience <= expert[0] && targetTeamExperience >= expert[2]) {
			lineIntersection[2] = calculateY(targetTeamExperience, expert);
		}
		
		return lineIntersection;
	} 
}