package com.ir.service;

import java.util.List;

import com.ir.bean.common.IntStringBean;
import com.ir.form.AssessmentQuestionForm;
import com.ir.form.ContactTrainee;
import com.ir.model.AssessmentQuestions;
import com.ir.model.City;
import com.ir.model.CourseName;
import com.ir.model.CourseType;
import com.ir.model.Designation;
import com.ir.model.District;
import com.ir.model.KindOfBusiness;
import com.ir.model.Languages;
import com.ir.model.ManageAssessmentAgency;
import com.ir.model.ManageCourseContent;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.SubjectMaster;
import com.ir.model.State;
import com.ir.model.Title;
import com.ir.model.TrainingCalendar;
import com.ir.model.TrainingPhase;
import com.ir.model.TrainingType;
import com.ir.model.Utility;

public interface PageLoadService {
	
	public List<State> loadState();
	public List<String> loadCaste();
	public List<City> loadCity(int stat);
	public List<Title> loadTitle();
	public List<KindOfBusiness> loadKindOfBusiness();
	public List<CourseName> loadCourseName();
	public List<District> loadDistrict();
	public List<CourseName> basicCourseName();
	public List<CourseName> advanceCourseList();
	public List<CourseName> specialCourseList();
	public List<ManageTrainingPartner> tpList();
	public List<ManageAssessmentAgency> aaList();
	public List basicCourseList();
	public List<CourseName> getCouserNameList(int coursetypeid);
	public List<String> getTrainingPartnerNameList();
	public List<ManageCourseContent> getManageCourseContentList(int i);
	public List<IntStringBean> getTrainingPartnerList(int courseTypeId);
	public List<Object[]> loadTrainingDetails(Utility utility);
	public String getNextCombinationId(String prefix , String tableName , String pattern);
	List<CourseType> courseTypeList();
	
	
	//onchange 
	
	public List<District> loadDistrict(String stateId);
	public List<City> loadCity(String districtId);
	//public List<AssessmentQuestions> loadModuleName(String val);
	public List loadTrainer(String instituteId);

	public List trainingInstituteList();
	public List listTrainer();

	public List<SubjectMaster> learningResource();
	public List<Designation> loadDesignation();
	public List<TrainingType> loadTrainingType();
	public List<TrainingPhase> loadTrainingPhase();
	public List<Languages> loadLanguages();
	public List  totCalendarlist();
	//public List masterTrainer();
	//public Object masterTrainer();
	public String feedbacksave(ContactTrainee contactTrainee, String id);
	List disPhotoGallery();


}
