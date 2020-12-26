import java.util.Map;
import java.util.Vector;

public class FuzzySet{
	private String name;
	private float target;
	private Map<String, Vector<Float>> sets;
	
	public FuzzySet() {
		name = "";
		target = 0;
		sets = null;
	}
	
	public FuzzySet(String name, Float target) {
		this.name = name;
		this.target = target;
	}
	
	public void addSet(String set, Vector<Float> points) {
		sets.put(set, points);
	}
	
}