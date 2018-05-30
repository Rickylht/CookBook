package EntityPackage;

public class CommentsAndRanks {
	private double CommentsAndRanksID;
	private String Comments;
	private int Ranks;
	
	public CommentsAndRanks(){
		
	}
	public CommentsAndRanks(double CommentsAndRanksID,String Comments,int Ranks){
		this.CommentsAndRanksID = CommentsAndRanksID;
		this.Comments = Comments;
		this.Ranks = Ranks;
	}
	public double getCommentsAndRanksID() {
		return CommentsAndRanksID;
	}
	public void setCommentsAndRanksID(double commentsAndRanksID) {
		CommentsAndRanksID = commentsAndRanksID;
	}
	public String getComments() {
		return Comments;
	}
	public void setComments(String comments) {
		Comments = comments;
	}
	public int getRanks() {
		return Ranks;
	}
	public void setRanks(int ranks) {
		Ranks = ranks;
	}
	

}
