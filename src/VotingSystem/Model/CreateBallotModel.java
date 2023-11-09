package VotingSystem.Model;

import java.io.FileInputStream;

public class CreateBallotModel {
	private int candidateID;
	private String candidateName;
	private int candidateAge;
	private String candidateSurname;
	private String candidatePosition;
	private String candidateProgram;
	private int idNumber;
	private FileInputStream candidateImage;
	
	public CreateBallotModel(int idNumber,String candidateName,String candidateSurname,  int candidateAge,
							String candidatePosition, String candidateProgram, FileInputStream candidateImage) {
		
		this.candidateImage = candidateImage;
		this.idNumber = idNumber;
		this.candidateName = candidateName;
		this.candidateAge = candidateAge;
		this.candidateSurname = candidateSurname;
		this.candidatePosition = candidatePosition;
		this.candidateProgram = candidateProgram;
	}
	
	
}
