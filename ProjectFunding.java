public class ProjectFunding{
	private float[] veryLow = new float[]{0, 0, 10, 30}; 
	private float[] low = new float[]{10, 30, 40, 60}; 
	private float[] medium = new float[]{40, 60, 70, 90}; 
	private float[] high = new float[]{70, 90, 100, 100}; 
	private float targetProjectFunding;
	
	public ProjectFunding() {
		targetProjectFunding = 0;
	}
	public ProjectFunding(float projectFunding) {
		targetProjectFunding = projectFunding;
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
		else if(x >= line[1] && x < line[2]) {
			x1 = line[1]; y1 = 1;
			x2 = line[2]; y2 = 1;
		}
		else if(x >= line[2] && x <= line[3]) {
			x1 = line[2]; y1 = 1;
			x2 = line[3]; y2 = 0;
		}
		else {
			return 0;
		}
		float a = geta(x1, y1, x2, y2);
		y = (a * x) + getb(x1, y1, a);
		return y;
	}
	
	
	public float[] getRange() {
		float[] lineIntersection = new float[]{0, 0, 0, 0};
		if(targetProjectFunding <= veryLow[0] && targetProjectFunding >= veryLow[3]) {
			lineIntersection[0] = calculateY(targetProjectFunding, veryLow);
		}
		if(targetProjectFunding <= low[0] && targetProjectFunding >= low[3]) {
			lineIntersection[1] = calculateY(targetProjectFunding, low);
		}
		if(targetProjectFunding <= medium[0] && targetProjectFunding >= medium[3]) {
			lineIntersection[2] = calculateY(targetProjectFunding, medium);
		}
		if(targetProjectFunding <= high[0] && targetProjectFunding >= high[3]) {
			lineIntersection[3] = calculateY(targetProjectFunding, high);
		}
		return lineIntersection;
	} 
}