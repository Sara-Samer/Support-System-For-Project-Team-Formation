/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FL; // To be modified <=====

/**
 *
 * @author maria
 */
public class Inference {

    private double[] project_funding = new double[4];
    private double[] team_experience_level = new double[3];
    private double[] risk = new double[3];

    public Inference(double veryLow_pf, double low_pf, double medium_pf, double high_pf,
            double beginner_tel, double intermediate_tel, double expert_tel) {
        project_funding[0] = veryLow_pf;
        project_funding[1] = low_pf;
        project_funding[2] = medium_pf;
        project_funding[3] = high_pf;
        team_experience_level[0] = beginner_tel;
        team_experience_level[1] = intermediate_tel;
        team_experience_level[2] = expert_tel;
        risk = setRisk();
    }

    public Inference(double[] pf, double[] tel) {
        for (int i = 0; i < 3; i++) {
            project_funding[i] = pf[i];
            team_experience_level[i] = tel[i];

        }

        project_funding[3] = pf[3];
        risk = setRisk();
    }

    private double and(double a, double b) {
        return Math.min(a, b);
    }

    private double or(double a, double b) {
        return Math.max(a, b);
    }

    private double[] setRisk() {
        risk[2] = or(project_funding[3], team_experience_level[2]);
        risk[1] = or(and(project_funding[2], team_experience_level[1]), team_experience_level[0]);
        risk[0] = or(project_funding[0], and(project_funding[1], team_experience_level[0]));
        return risk;

    }
    public double [] getRisk()
    {
        return risk;
    }
}
