import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Variables: ");
        Integer var = sc.nextInt();
        Vector<FuzzySet> fuzzy = new Vector<>();
        for(int i = 0 ; i < var ; i++) {
        	String fuzzySetName = sc.next();
        	Float fuzzySetOut = sc.nextFloat();
        	FuzzySet set = new FuzzySet(fuzzySetName, fuzzySetOut);
        	fuzzy.add(set);
        }
        float[] arr = {0, 0, 10, 30};
        fuzzy.elementAt(0).addSet("vl", arr );
        float[] arr2 = {10, 30, 40, 60};
        fuzzy.elementAt(0).addSet("l", arr2 );
        float[] arr3 = {40, 60, 70, 90};
        fuzzy.elementAt(0).addSet("m", arr3 );
        float[] arr4 = {70, 90, 100, 100};
        fuzzy.elementAt(0).addSet("h", arr4 );
        
        float[] arr5 = {0, 15, 30};
        fuzzy.elementAt(1).addSet("b", arr5 );
        float[] arr6 = {15, 30, 45};
        fuzzy.elementAt(1).addSet("i", arr6 );
        float[] arr7 = {30, 60, 60};
        fuzzy.elementAt(1).addSet("e", arr7 );
        
        
        //fuzzy.elementAt(0).printSets();
        //fuzzy.elementAt(1).printSets();
        Map<String, Map<String, Float>> allRanges = new HashMap<String, Map<String, Float>>();
        for(int i = 0 ; i < fuzzy.size(); i++) {
        	allRanges.put(fuzzy.elementAt(i).getName(), fuzzy.elementAt(i).getRange());
        }
        
        String key = "";
        String name = "";
        for(int i = 0 ; i < allRanges.size(); i++) {
        	name = (String) allRanges.keySet().toArray()[i];
        	for(int j = 0 ; j < allRanges.get(name).size(); j++) {
        		key = (String) allRanges.get(name).keySet().toArray()[j];
        		System.out.println(key + "  =  " + allRanges.get(name).get(key));
        	}
        }
        
        
        

    }
}