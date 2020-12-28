import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class FL {
	private Map<String, FuzzySet> fuzzyIn;
	private FuzzySet fuzzyOut;
	private Map<String, Map<String, Float>> allRanges;
	private Vector<String> rules;
	private Inferencing inference;
	private Map<String, Float> output;

	public FL() {
		this.fuzzyIn = new HashMap<String, FuzzySet>(100);
		this.fuzzyOut = null;
		this.allRanges = new HashMap<String, Map<String, Float>>(100);
		this.rules = new Vector<>(100);
		this.output = new HashMap<String, Float>(100);
	}

	public void addInput(String setName) {
		FuzzySet fuzzySet = new FuzzySet(setName, (float) 0.0);
		this.fuzzyIn.put(setName, fuzzySet);
	}

	public void addOutput(String setName) {
		this.fuzzyOut = new FuzzySet(setName, (float) 0.0);
	}

	public void addRangeToFuzzySet(String setName, String rangeName, float[] range) {
		if (this.fuzzyIn.get(setName) != null) {
			this.fuzzyIn.get(setName).addSet(rangeName, range);

		} else if (this.fuzzyOut != null) {
			this.fuzzyOut.addSet(rangeName, range);
		}
	}

	public void addTarget(String setName, Float target) {
		this.fuzzyIn.get(setName).setTarget(target);
	}

	public void CalculateResult() {

		for (Map.Entry<String, FuzzySet> fuzzy : this.fuzzyIn.entrySet()) {
			String setName = fuzzy.getKey();
			Map<String, Float> range = fuzzy.getValue().getRange();
			this.allRanges.put(setName, range);

		}

	}

	public void printRanges() {
		String key = "";
		String name = "";
		for (int i = 0; i < this.allRanges.size(); i++) {
			name = (String) this.allRanges.keySet().toArray()[i];
			for (int j = 0; j < this.allRanges.get(name).size(); j++) {
				key = (String) this.allRanges.get(name).keySet().toArray()[j];
				System.out.println(name + " " + key + "  =  " + this.allRanges.get(name).get(key));
			}
		}
	}

	public void addRule(String rule) {
		this.rules.add(rule);
	}

	public void parseRules() {
		this.inference = new Inferencing(this.allRanges, this.fuzzyOut.getSetsNames(), this.rules,
				this.fuzzyOut.getName());
		if (this.inference.setOutput()) {
			this.output = this.inference.getOutput();
			System.out.println(output);

		} else {
			System.out.println("A problem occured. Please check the entered rules, it should be in the format.");

		}
	}

	public Map<String, Float> getOutput() {
		Map<String, Float> centroids = this.fuzzyOut.getCentroids();
		Map<String, Float> output = new HashMap<String, Float>(100);

		System.out.print("centroids: ");
		System.out.println(centroids);

		float crispValue = 0;
		String crispGroup = "";
		float sum = 0;
		Float max = (float) 0.0;

		for (Map.Entry<String, Float> out : this.output.entrySet()) {
			String name = out.getKey();
			Float value = out.getValue();
			Float outputValue = centroids.get(name);
			sum += value;
			
			// System.out.print("value: ");
			// System.out.println(value);
			// System.out.println(max < value);

			crispValue += (value * outputValue);

			if (max < value) {
				max = value;
				crispGroup = name;
				System.out.println("here");
			}
		}
		// System.out.print("sum: ");
		// System.out.println(sum);
		// System.out.print("crispValue: ");
		// System.out.println(crispValue);
		crispValue = crispValue / sum;
		// System.out.print("total: ");
		// System.out.println(crispValue);

		// System.out.print("crisp Group: ");
		// System.out.println(crispGroup);
		output.put(crispGroup, crispValue);
		return output;
	}
}
