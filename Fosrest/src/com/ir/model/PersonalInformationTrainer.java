package com.ir.model;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;



@Entity
@Table(name="personalinformationtrainer")
public class PersonalInformationTrainer {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "PERSONALINFOTRAINEE_SEQ", allocationSize=1, initialValue=1)
	private int id;
	
	private boolean fssai_tot;

	private String knownSubjects;


	@OneToOne (cascade=CascadeType.ALL)  @JoinColumn(name="loginDetails")
	private LoginDetails loginDetails;
	
	
	public LoginDetails getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(LoginDetails loginDetails) {
		this.loginDetails = loginDetails;
	}

	private String empID;
	
	
	
	 public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	private String title;
	
	
	public String getKnownSubjects() {
		return knownSubjects;
	}

	public void setKnownSubjects(String knownSubjects) {
		this.knownSubjects = knownSubjects;
	}

	@NotNull
	
	@Size(min=1, max=50 , message="enter your FirstName")
	private String firstName;

	private String MiddleName;
	@NotNull
	@Size(min=1, max=50 , message="enter your LastName ")
	private String  LastName ;

	@NotNull
	@Size(min=1, max=100 , message="enter your father's name")
	private String FatherName;
	
	
	
	@NotNull
	@Size(min=12, max=12 , message="enter your AadharNumber ")
	private String  AadharNumber ; 
	 @Email
	private String Email;
	@NotNull
	private String dob;
	@NotNull
	private String radioTrainingInstitute;
	public String getRadioTrainingInstitute() {
		return radioTrainingInstitute;
	}

	public void setRadioTrainingInstitute(String radioTrainingInstitute) {
		this.radioTrainingInstitute = radioTrainingInstitute;
	}

	@Size(max=10 , message = "enter your mobile Number")
	private String mobile;
	@NotNull
	private String gender;
	@NotNull 
	private int profileCode;
	@NotNull
	@Size(min=1, max=100 , message="enter your ResidentialAddress1 ")
	private String ResidentialLine1; 
	/*@NotNull 
	@Size(min=1, max=100 , message="enter your ResidentialAddress2")*/
	private String ResidentialLine2;

	
	private String resState;

	
	private String resCity;
	
	@NotNull
	@Size(min=1, max=6 , message="enter your Pincode")
	private String resPincode;
	@NotNull
	@Size(min=1, max=100 , message="enter your correspondenceAddress1")
	private String correspondenceAddress1;
	/*@NotNull
	@Size(min=1, max=100 , message="enter your correspondenceAddress1")*/
	private String correspondenceAddress2;

	
	private String correspondenceState;
	
	private String correspondenceCity;
	@NotNull @Size(min=6, max=6 , message="* enter your correspondencePincode ")
	private String correspondencePincode;


	private String residentialDistrict;
	@NotNull


	private String correspondenceDistrict;

	

	public boolean isFssai_tot() {
		return fssai_tot;
	}

	public void setFssai_tot(boolean fssai_tot) {
		this.fssai_tot = fssai_tot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		this.MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	public String getFatherName() {
		return FatherName;
	}

	public void setFatherName(String fatherName) {
		this.FatherName = fatherName;
	}



	public String getAadharNumber() {
		return AadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.AadharNumber = aadharNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(int profileCode) {
		this.profileCode = profileCode;
	}

	public String getResidentialLine1() {
		return ResidentialLine1;
	}

	public void setResidentialLine1(String residentialLine1) {
		this.ResidentialLine1 = residentialLine1;
	}

	public String getResidentialLine2() {
		return ResidentialLine2;
	}

	public void setResidentialLine2(String residentialLine2) {
		this.ResidentialLine2 = residentialLine2;
	}

	public String getResState() {
		return resState;
	}

	public void setResState(String resState) {
		this.resState = resState;
	}

	public String getResCity() {
		return resCity;
	}

	public void setResCity(String resCity) {
		this.resCity = resCity;
	}

	public String getResPincode() {
		return resPincode;
	}

	public void setResPincode(String resPincode) {
		this.resPincode = resPincode;
	}

	public String getCorrespondenceAddress1() {
		return correspondenceAddress1;
	}

	public void setCorrespondenceAddress1(String correspondenceAddress1) {
		this.correspondenceAddress1 = correspondenceAddress1;
	}

	public String getCorrespondenceAddress2() {
		return correspondenceAddress2;
	}

	public void setCorrespondenceAddress2(String correspondenceAddress2) {
		this.correspondenceAddress2 = correspondenceAddress2;
	}

	public String getCorrespondenceState() {
		return correspondenceState;
	}

	public void setCorrespondenceState(String correspondenceState) {
		this.correspondenceState = correspondenceState;
	}

	public String getCorrespondenceCity() {
		return correspondenceCity;
	}

	public void setCorrespondenceCity(String correspondenceCity) {
		this.correspondenceCity = correspondenceCity;
	}

	public String getCorrespondencePincode() {
		return correspondencePincode;
	}

	public void setCorrespondencePincode(String correspondencePincode) {
		this.correspondencePincode = correspondencePincode;
	}

	public String getResidentialDistrict() {
		return residentialDistrict;
	}

	public void setResidentialDistrict(String residentialDistrict) {
		this.residentialDistrict = residentialDistrict;
	}

	public String getCorrespondenceDistrict() {
		return correspondenceDistrict;
	}

	public void setCorrespondenceDistrict(String correspondenceDistrict) {
		this.correspondenceDistrict = correspondenceDistrict;
	}

	
	
	private int steps;

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	private int expInYear;
	private int expInMonth;
	
	

	public int getExpInYear() {
		return expInYear;
	}

	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}

	public int getExpInMonth() {
		return expInMonth;
	}

	public void setExpInMonth(int expInMonth) {
		this.expInMonth = expInMonth;
	}

	private String AssociatedWithAnyTrainingInstitute;
	
	

	

	public String getAssociatedWithAnyTrainingInstitute() {
		return AssociatedWithAnyTrainingInstitute;
	}

	public void setAssociatedWithAnyTrainingInstitute(String associatedWithAnyTrainingInstitute) {
		AssociatedWithAnyTrainingInstitute = associatedWithAnyTrainingInstitute;
	}

	
	private int createdBy;
	public int getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(int createdBy) {
	this.createdBy = createdBy;
}

private String Status;
	
	

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
private String logId;
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
private String otherTrainingInstitute;

	public String getOtherTrainingInstitute() {
	return otherTrainingInstitute;
}
private String languages;
public String getLanguages() {
	return languages;
}

public void setLanguages(String languages) {
	this.languages = languages;
}
private String trainingState;
public String getTrainingState() {
	return trainingState;
}

public void setTrainingState(String trainingState) {
	this.trainingState = trainingState;
}

public void setOtherTrainingInstitute(String otherTrainingInstitute) {
	this.otherTrainingInstitute = otherTrainingInstitute;
}

private String TotalExp;
private String Graduation;
private String PostGraduation;
private boolean FoodSafetyAct;
private boolean POF;
private boolean NovelDev;
private boolean FoodNutrition;
private boolean Functional;
private boolean RiskAss;
private boolean FoodLab;
private boolean Labelling;
private boolean CaseStudy;
private boolean GlobalPerspective;
private boolean OtherExp;


	public String getTotalExp() {
	return TotalExp;
}

public void setTotalExp(String totalExp) {
	TotalExp = totalExp;
}



	public String getGraduation() {
	return Graduation;
}

public void setGraduation(String graduation) {
	Graduation = graduation;
}

public String getPostGraduation() {
	return PostGraduation;
}

public void setPostGraduation(String postGraduation) {
	PostGraduation = postGraduation;
}

	public boolean isFoodSafetyAct() {
	return FoodSafetyAct;
}

public void setFoodSafetyAct(boolean foodSafetyAct) {
	FoodSafetyAct = foodSafetyAct;
}

public boolean isPOF() {
	return POF;
}

public void setPOF(boolean pOF) {
	POF = pOF;
}

public boolean isNovelDev() {
	return NovelDev;
}

public void setNovelDev(boolean novelDev) {
	NovelDev = novelDev;
}

public boolean isFoodNutrition() {
	return FoodNutrition;
}

public void setFoodNutrition(boolean foodNutrition) {
	FoodNutrition = foodNutrition;
}

public boolean isFunctional() {
	return Functional;
}

public void setFunctional(boolean functional) {
	Functional = functional;
}

public boolean isRiskAss() {
	return RiskAss;
}

public void setRiskAss(boolean riskAss) {
	RiskAss = riskAss;
}

public boolean isFoodLab() {
	return FoodLab;
}

public void setFoodLab(boolean foodLab) {
	FoodLab = foodLab;
}

public boolean isLabelling() {
	return Labelling;
}

public void setLabelling(boolean labelling) {
	Labelling = labelling;
}

public boolean isCaseStudy() {
	return CaseStudy;
}

public void setCaseStudy(boolean caseStudy) {
	CaseStudy = caseStudy;
}

public boolean isGlobalPerspective() {
	return GlobalPerspective;
}

public void setGlobalPerspective(boolean globalPerspective) {
	GlobalPerspective = globalPerspective;
}

public boolean isOtherExp() {
	return OtherExp;
}

public void setOtherExp(boolean otherExp) {
	OtherExp = otherExp;
}

	@Override
	public String toString() {
		return "PersonalInformationTrainee [id=" + id + ", FirstName=" + firstName + ", mobile=" + mobile +  ", Email="+Email +" , AadharNumber="+AadharNumber+   "]";
	}

	
	
}
