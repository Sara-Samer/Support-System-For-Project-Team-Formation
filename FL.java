import java.util.Vector;

public class FL {
    private float targetProjectFunding;
    private float targetTeamExperience;
	
    public FL() {
    	targetProjectFunding = 0;
    	targetTeamExperience = 0;
		getRanges();
    }
    
	public FL(float pFunding, float tExperience) {
		targetProjectFunding = pFunding;
		targetTeamExperience = tExperience;
		getRanges();
	}
	public Vector<float[]> getRanges() {
		ProjectFunding projectFunding = new ProjectFunding(targetProjectFunding);
		TeamExperience teamExperience = new TeamExperience(targetTeamExperience);
		Vector<float[]> allRanges = new Vector<float[]>();
		float[] pFundingResults = projectFunding.getRange();
		float[] tExperienceResults = teamExperience.getRange();
		allRanges.add(pFundingResults);
		allRanges.add(tExperienceResults);
		return allRanges;
	}
	
	
}
