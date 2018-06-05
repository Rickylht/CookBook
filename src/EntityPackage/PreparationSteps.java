package EntityPackage;

public class PreparationSteps {
	private double StepsID;
	private String Description;
	
	public PreparationSteps(){
		
	}
	
	public PreparationSteps(double stepsID, String description) {
		this.StepsID = stepsID;
		this.Description = description;
	}
	
	public double getStepsID() {
		return StepsID;
	}
	
	public void setStepsID(double stepsID) {
		StepsID = stepsID;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String description) {
		Description = description;
	}
	
}
