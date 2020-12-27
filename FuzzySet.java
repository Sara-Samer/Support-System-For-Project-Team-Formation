import java.util.HashMap;
import java.util.Map;

public class FuzzySet{
	private String name;
	private float target;
	private Map<String, float[]> sets;
	
	public FuzzySet() {
		name = "";
		target = 0;
		sets = new HashMap<>();
	}
	
	public FuzzySet(String name, Float target) {
		this.name = name;
		this.target = target;
		sets = new HashMap<>();
	}
	
	public void addSet(String set, float[] points) {
		sets.put(set, points);
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
		for(int i = 0; i < line.length; i++) {
			if(i + 1 < line.length && x >= line[i] && x <= line[i + 1] ) {
				x1 = line[i];
				x2 = line[i+1];
				if(i == 0) {
					y1 = 0;
					y2 = 1;
				}
				else if(i + 1 == line.length - 1) {
					y1 = 1;
					y2 = 0;
				}
				else {
					y1 = 1;
					y2 = 1;
				}
				float a = geta(x1, y1, x2, y2);
				y = (a * x) + getb(x1, y1, a);
			}
		}
		return y;
	}
	
	public Map<String, Float> getRange(){
		Map<String, Float> lineIntersection = new HashMap<String, Float>();
		String key = "";
		for(int i = 0 ; i < sets.size(); i++) {
			key = (String) sets.keySet().toArray()[i];
			if(target >= sets.get(key)[0] && target <= sets.get(key)[sets.get(key).length - 1]) {
				lineIntersection.put(key, calculateY(target, sets.get(key)));
			}
			else {
				lineIntersection.put(key, (float) 0);
			}
		}
		return lineIntersection;
	}
	
	public String getName() {
		return name;
	}
	
	public void printSets() {
		System.out.println(name);
		System.out.println(target);
		System.out.println("-------------:");
		for(int i = 0 ; i < sets.size(); i++) {
			System.out.print(sets.keySet().toArray()[i] + "  {");
			for(int j = 0 ; j < sets.get(sets.keySet().toArray()[i]).length; j++) {
				System.out.print(sets.get(sets.keySet().toArray()[i])[j] + " , ");
			}
			System.out.println("}");
		}
		System.out.println("-------------------------------------");
		
	}
}