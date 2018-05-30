package EntityPackage;

public class Recipe {
	private String RecipeID;
	private String Name;
	private String Description;
	private boolean Privacy;
	private int ServeNumber;
	private double PrepareTime;
	private double CookTime;
	private String Category;
	
	public Recipe(){
		
	}

	public Recipe(String recipeID, String title, String description, boolean privacy, int serveNumber,
			double prepareTime, double cookTime, String category) {
		this.RecipeID = recipeID;
		this.Name = title;
		this.Description = description;
		this.Privacy = privacy;
		this.ServeNumber = serveNumber;
		this.PrepareTime = prepareTime;
		this.CookTime = cookTime;
		this.Category = category;
	}

	public String getRecipeID() {
		return RecipeID;
	}

	public void setRecipeID(String string) {
		RecipeID = string;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public boolean isPrivacy() {
		return Privacy;
	}

	public void setPrivacy(boolean privacy) {
		Privacy = privacy;
	}

	public int getServeNumber() {
		return ServeNumber;
	}

	public void setServeNumber(int serveNumber) {
		ServeNumber = serveNumber;
	}

	public double getPrepareTime() {
		return PrepareTime;
	}

	public void setPrepareTime(double prepareTime) {
		PrepareTime = prepareTime;
	}

	public double getCookTime() {
		return CookTime;
	}

	public void setCookTime(double cookTime) {
		CookTime = cookTime;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	@Override
	public String toString() {
		return ("ID:" + this.RecipeID + "name:" + this.Name + "Description"+this.Description+"privicy"+this.Privacy+"serveNumber"+this.ServeNumber+"PrepareTime"+this.PrepareTime+"CookTime"+this.CookTime);
	}
}
