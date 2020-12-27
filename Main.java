/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        for (int i = 0; i < var; i++) {
            String fuzzySetName = sc.next();
            Float fuzzySetOut = sc.nextFloat();
            FuzzySet set = new FuzzySet(fuzzySetName, fuzzySetOut);
            fuzzy.add(set);
        }
        float[] arr = {0, 0, 10, 30};
        fuzzy.elementAt(0).addSet("very_low", arr);
        float[] arr2 = {10, 30, 40, 60};
        fuzzy.elementAt(0).addSet("low", arr2);
        float[] arr3 = {40, 60, 70, 90};
        fuzzy.elementAt(0).addSet("medium", arr3);
        float[] arr4 = {70, 90, 100, 100};
        fuzzy.elementAt(0).addSet("high", arr4);

        float[] arr5 = {0, 15, 30};
        fuzzy.elementAt(1).addSet("beginner", arr5);
        float[] arr6 = {15, 30, 45};
        fuzzy.elementAt(1).addSet("intermediate", arr6);
        float[] arr7 = {30, 60, 60};
        fuzzy.elementAt(1).addSet("expert", arr7);

        //fuzzy.elementAt(0).printSets();
        //fuzzy.elementAt(1).printSets();
        Map<String, Map<String, Float>> allRanges = new HashMap<String, Map<String, Float>>();
        for (int i = 0; i < fuzzy.size(); i++) {
            allRanges.put(fuzzy.elementAt(i).getName(), fuzzy.elementAt(i).getRange());
        }

        String key = "";
        String name = "";
        for (int i = 0; i < allRanges.size(); i++) {
            name = (String) allRanges.keySet().toArray()[i];
            for (int j = 0; j < allRanges.get(name).size(); j++) {
                key = (String) allRanges.get(name).keySet().toArray()[j];
                System.out.println(name+" "+key + "  =  " + allRanges.get(name).get(key));
            }
        }
        
        Vector<String> rulesVec = new Vector<>();
        rulesVec.add("If project_funding is high or team_experience_level is expert then risk is low");
        rulesVec.add("If project_funding is medium and team_experience_level is intermediate or team_experience_level is beginner then risk is normal");
        rulesVec.add("If project_funding is very_low then risk is high");
        rulesVec.add("If project_funding is low and team_experience_level is beginner then risk is high");
        Map<String, Float> outp = new HashMap<String, Float>();
        outp.put("high",Float.parseFloat("0"));
        outp.put("normal",Float.parseFloat("0"));
        outp.put("low",Float.parseFloat("0"));
        
        Inferencing i = new Inferencing(allRanges, outp, rulesVec, "risk");
        if(i.setOutput())
        {
           Map<String, Float> output=i.getOutput();
          System.out.println(output);
            
        }
        else{
            System.out.println("A problem occured. Please check the entered rules, it should be in the format.");
            
        }

    }
}
