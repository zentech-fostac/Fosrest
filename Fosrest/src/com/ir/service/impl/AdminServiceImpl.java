package com.ir.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ir.bean.common.IntStringBean;
import com.ir.dao.AdminDAO;
import com.ir.form.ActivateAssessmentOfTraineeForm;
import com.ir.form.AdminUserManagementForm;
import com.ir.form.AssessmentQuestionForm;
import com.ir.form.AssessmentQuestionForm_old;
import com.ir.form.AssessorUserManagementForm;
import com.ir.form.ChangePasswordForm;
import com.ir.form.CityForm;
import com.ir.form.ContactTrainee;
import com.ir.form.DistrictForm;
import com.ir.form.FotestGenerateCertificateForm;
import com.ir.form.GenerateCertificateForm;
import com.ir.form.InvoiceInfoForm;
import com.ir.form.InvoiceMasterForm;
import com.ir.form.ManageCourse;
import com.ir.form.ManageCourseContentForm;
import com.ir.form.ManageTrainingCalendarForm;
import com.ir.form.ManageTrainingPartnerForm;
import com.ir.form.NominateTraineeForm;
import com.ir.form.RegionForm;
import com.ir.form.StateForm;
import com.ir.form.TraineeUserManagementForm;
import com.ir.form.TrainerUserManagementForm;
import com.ir.form.TrainingCalendarForm;
import com.ir.form.TrainingCenterUserManagementForm;
import com.ir.form.TrainingClosureForm;
import com.ir.form.TrainingScheduleForm;
import com.ir.form.UploadAssessmentForm;
import com.ir.form.ViewTrainingCalendarForm;
import com.ir.form.activateTrainingOfTraineeForm;
import com.ir.form.stateAdminForm;
import com.ir.form.verifyTraineeEnrollmentForm;
import com.ir.form.viewEnrolledCoursesForm;
import com.ir.model.AdminUserManagement;
import com.ir.model.AssessmentQuestions;
import com.ir.model.CityMaster;
import com.ir.model.CourseName;
import com.ir.model.CourseType;
import com.ir.model.CustomerDetails;
import com.ir.model.CustomerMaster;
import com.ir.model.District;
import com.ir.model.DistrictMaster;
import com.ir.model.EmployeeMonthlyCharges;
import com.ir.model.FeedbackMaster;
import com.ir.model.FotestFeedbackMaster;
import com.ir.model.HolidayMaster;
import com.ir.model.InvoiceMaster;
import com.ir.model.LoginDetails;
import com.ir.model.ManageCourseCarricullum;
import com.ir.model.ManageTraining;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.MappingMasterTrainer;
import com.ir.model.SubjectMaster;
import com.ir.model.NomineeTrainee;
import com.ir.model.PersonalInformationAssessor;
import com.ir.model.PersonalInformationTrainee;
import com.ir.model.PersonalInformationTrainer;
import com.ir.model.PersonalInformationTrainingInstitute;
import com.ir.model.PersonalInformationTrainingPartner;
import com.ir.model.PhotoGallery;
import com.ir.model.RegionMapping;
import com.ir.model.RegionMaster;
import com.ir.model.State;
import com.ir.model.StateAdmin;
import com.ir.model.StateMaster;
import com.ir.model.SubjectMaster;
import com.ir.model.TaxMaster;
import com.ir.model.TrainingCalendar;
import com.ir.model.TrainingPartner;
import com.ir.model.TrainingSchedule;
import com.ir.model.UnitMaster;
import com.ir.model.ViewResult;
import com.ir.model.admin.TrainerAssessmentSearchForm;
import com.ir.model.trainer.TrainerAssessmentEvaluation;
import com.ir.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	@Qualifier("adminDAO")
	AdminDAO adminDAO;

	@Override
	@Transactional
	public String stateMasterSave(StateForm stateForm) {
		// TODO Auto-generated method stub
		String stateMasterSave = adminDAO.stateMasterSave(stateForm);
		return stateMasterSave;
	}

	@Override
	@Transactional
	public List<State> stateList() {
		// TODO Auto-generated method stub
		List<State> stateList = adminDAO.stateList();
		return stateList;
	}

	@Override
	@Transactional
	public String districtMasterSave(DistrictForm districtForm) {
		// TODO Auto-generated method stub
		String districtMasterSave = adminDAO.districtMasterSave(districtForm);
		return districtMasterSave;
	}

	@Override
	@Transactional
	public String cityMasterSave(CityForm cityForm) {
		String cityMasterSave = adminDAO.cityMasterSave(cityForm);
		return cityMasterSave;
	}

	@Override
	@Transactional
	public String regionMasterSave(RegionForm regionForm) {
		String regionMasterSave = adminDAO.regionMasterSave(regionForm);
		return regionMasterSave;
	}

	@Override
	@Transactional
	public List<CourseName> courseNameList() {
		List<CourseName> courseNameList = adminDAO.courseNameList();
		return courseNameList;
	}

	@Override
	@Transactional
	public String manageCourse(ManageCourse manageCourse) {
		String manageCourse1 = adminDAO.manageCourse(manageCourse);
		return manageCourse1;
	}

	@Override
	@Transactional
	public List<CourseType> courseTypeList() {
		System.out.println("AdminDao");
		List<CourseType> courseTypeList = adminDAO.courseTypeList();
		return courseTypeList;
	}

	/*@Override
	@Transactional
	public String manageTrainingPartnerSave(ManageTrainingPartnerForm manageTrainingPartnerForm) {
		// TODO Auto-generated method stub
		String manageTrainingPartnerSave = adminDAO.manageTrainingPartnerSave(manageTrainingPartnerForm);
		return manageTrainingPartnerSave;
	}*/

	/*@Override
	@Transactional
	public String manageAssessmentAgencySave(ManageAssessmentAgencyForm manageAssessmentAgencyForm) {
		String manageAssessmentAgencySave = adminDAO.manageAssessmentAgencySave(manageAssessmentAgencyForm);
		return manageAssessmentAgencySave;
	}*/

	@Override
	@Transactional
	public List<PersonalInformationTrainee> traineeUserManagementSearch(
			TraineeUserManagementForm traineeUserManagementForm) {
		List<PersonalInformationTrainee> traineeUserManagementSearch = adminDAO
				.traineeUserManagementSearch(traineeUserManagementForm);
		return traineeUserManagementSearch;
	}

	@Override
	@Transactional
	public List<PersonalInformationTrainer> trainerUserManagementSearch(
			TrainerUserManagementForm trainerUserManagementForm) {
		List<PersonalInformationTrainer> trainerUserManagementSearch = adminDAO
				.trainerUserManagementSearch(trainerUserManagementForm);
		return trainerUserManagementSearch;
	}

	@Override
	@Transactional
	public List<PersonalInformationAssessor> assessorUserManagementSearch(
			AssessorUserManagementForm assessorUserManagementForm, Integer profileid, Integer userID) {
		List<PersonalInformationAssessor> assessorUserManagementSearch1 = adminDAO
				.assessorUserManagementSearch(assessorUserManagementForm, profileid, userID);
		return assessorUserManagementSearch1;
	}

	@Override
	@Transactional
	public List<PersonalInformationTrainingInstitute> trainingCenterUserManagementSearch(
			TrainingCenterUserManagementForm trainingCenterUserManagementForm, Integer profileid, Integer userID) {
		List<PersonalInformationTrainingInstitute> trainingCenterUserManagementSearch = adminDAO
				.trainingCenterUserManagementSearch(trainingCenterUserManagementForm, profileid, userID);
		return trainingCenterUserManagementSearch;
	}

	@Override
	@Transactional
	public List<AdminUserManagement> adminUserManagementSearch() {
		List<AdminUserManagement> adminUserManagementSearch = adminDAO.adminUserManagementSearch();
		return adminUserManagementSearch;
	}

	@Override
	@Transactional
	public String adminUserManagementSave(AdminUserManagementForm adminUserManagementForm) {
		String adminUserManagementSave = adminDAO.adminUserManagementSave(adminUserManagementForm);
		return adminUserManagementSave;
	}

	//stateAdmin
	@Override
	@Transactional
	public String addstateadmin(stateAdminForm p) {
		// TODO Auto-generated method stub
		return this.adminDAO.addstateadmin(p);
	}

	@Override
	@Transactional
	public void updatestateadmin(stateAdminForm p) {
		 this.adminDAO.updatestateadmin(p);
	}

	@Override
	@Transactional
	public List<StateAdmin> liststateadmin() {
		// TODO Auto-generated method stub
		return this.adminDAO.liststateadmin();
	}

	@Override
	@Transactional
	public StateAdmin getstateadminById(int id) {
		// TODO Auto-generated method stub
		return this.adminDAO.getstateadminById(id);
	}

	@Override
	@Transactional
	public void removestateadmin(int id) {
		// TODO Auto-generated method stub
		this.adminDAO.removestateadmin(id);
	}

	
	@Override
	@Transactional
	public String manageCourseContentSearch(ManageCourseContentForm manageCourseContentForm) {
		String manageCourseContentSearch = adminDAO.manageCourseContentSearch(manageCourseContentForm);
		return manageCourseContentSearch;
	}

	@Override
	@Transactional
	public List<ManageTrainingPartner> trainingPartnerList() {
		List<ManageTrainingPartner> trainingPartnerList = adminDAO.trainingPartnerList();
		return trainingPartnerList;
	}

	@Override
	@Transactional
	public List<PersonalInformationTrainer> trainingNameList() {
		List<PersonalInformationTrainer> trainingNameList = adminDAO.trainingNameList();
		return trainingNameList;
	}

	@Override
	@Transactional
	public String assessorUserManagementSave(AssessorUserManagementForm assessorUserManagementForm) {
		String assessorUserManagementSave = adminDAO.assessorUserManagementSave(assessorUserManagementForm);
		return assessorUserManagementSave;
	}

	@Override
	@Transactional
	public List<District> districtList() {
		List<District> districtList = adminDAO.districtList();
		return districtList;
	}

	/*@Override
	@Transactional
	public String trainingCalendarForm(TrainingCalendarForm trainingCalendarForm) {
		String trainingCalendar = adminDAO.trainingCalendarForm(trainingCalendarForm);
		return trainingCalendar;
	}*/

	@Override
	@Transactional
	public String manageAssessmentQuestionsSave(AssessmentQuestionForm assessmentQuestionForm) {
		String manageAssessmentQuestionsSave = adminDAO.manageAssessmentQuestionsSave(assessmentQuestionForm);
		return manageAssessmentQuestionsSave;
	}

	// Rishi
	@Override
	@Transactional
	public boolean changePasswordadminSave(ChangePasswordForm changePasswordForm, String id) {
		boolean changePasswordadmin = adminDAO.trainingadminForm(changePasswordForm, id);
		return changePasswordadmin;
	}

	@Override
	@Transactional
	public boolean changePasswordTPSave(ChangePasswordForm changePasswordForm, String id) {
		boolean changePasswordadmin = adminDAO.trainingPartnerPass(changePasswordForm, id);
		return changePasswordadmin;
	}

	@Override
	@Transactional
	public String contactTraningPTSave(ContactTrainee contactTrainee, String id) {
		String contactTrainingPTSave = adminDAO.contactTrainigPartnerSave(contactTrainee, id);
		return contactTrainingPTSave;
	}

	@Override
	@Transactional
	public String saveFeedbackMaster(FeedbackMaster feedbackMaster) {
		String saveFeedbackMaster = adminDAO.saveFeedbackMaster(feedbackMaster);
		return saveFeedbackMaster;
	}

	@Override
	@Transactional
	public List<IntStringBean> getTrainingCentersByCourse(int courseNameId) {
		List<IntStringBean> listTrainingCenters = adminDAO.getTrainingCentersByCourse(courseNameId);
		return listTrainingCenters;
	}

	@Override
	@Transactional
	public List<TrainerAssessmentSearchForm> searchTrainerForAssessmentValidation(int courseNameId,
			int trainingPartnerId) {
		List<TrainerAssessmentSearchForm> list = adminDAO.searchTrainerForAssessmentValidation(courseNameId,
				trainingPartnerId);
		return list;
	}

	@Override
	@Transactional
	public TrainerAssessmentSearchForm evaluateTrainerAssessment(TrainerAssessmentSearchForm trainerAssessmentForm) {

		int eligibility = adminDAO.getElegibilityForAssessment(trainerAssessmentForm.getCourseNameId());
		int rating = trainerAssessmentForm.getRating();
		if (eligibility > -1) {
			if (rating >= eligibility) {
				trainerAssessmentForm.setResult("Pass");
			} else {
				trainerAssessmentForm.setResult("Fail");
			}
		} else {
			trainerAssessmentForm.setResult("Eligibility yet to declare");
		}
		return trainerAssessmentForm;

	}

	@Override
	@Transactional
	public int saveTrainerAssessment(TrainerAssessmentEvaluation trainerAssessmentEvaluation) {
		int assessmentId = adminDAO.saveTrainerAssessment(trainerAssessmentEvaluation);
		return assessmentId;
	}
	// updateUser

	@Override
	public void updateUser(String userid, String tableName, String status) {
		adminDAO.updateUser(userid, tableName, status);

	}

	// searchManageCourse

	@Override
	@Transactional
	public List searchManageCourse(String data) {
		List courseData = adminDAO.searchManageCourse(data);
		return courseData;
	}

	// editManageCourseData

	@Override
	@Transactional
	public String editManageCourseData(String data) {
		String courseData = adminDAO.editManageCourseData(data);
		return courseData;
	}

	// editState

	@Override
	@Transactional
	public String editState(String data) {
		String courseData = adminDAO.editState(data);
		return courseData;
	}

	// CheckState
	@Override
	@Transactional
	public String CheckState(String data) {
		String courseData = adminDAO.CheckState(data);
		return courseData;
	}

	// searchState

	@Override
	@Transactional
	public List searchState(String data) {
		List<State> courseData = adminDAO.searchState(data);
		return courseData;
	}

	// onLoadDistrict

	@Override
	@Transactional
	public List onLoadDistrict(String data) {
		List courseData = adminDAO.onLoadDistrict(data);
		return courseData;
	}

	// changeStatusDistrict

	@Override
	@Transactional
	public String changeStatusDistrict(String data) {
		String courseData = adminDAO.changeStatusDistrict(data);
		return courseData;
	}

	// searchDistrict

	@Override
	@Transactional
	public List searchDistrict(String data) {
		List courseData = adminDAO.searchDistrict(data);
		return courseData;
	}

	// editCityData

	@Override
	@Transactional
	public String editCityData(String data) {
		String courseData = adminDAO.editCityData(data);
		return courseData;
	}

	// searchCity

	@Override
	@Transactional
	public List searchCity(String data) {
		List courseData = adminDAO.searchCity(data);
		return courseData;
	}

	// onLoadRegion

	@Override
	@Transactional
	public List onLoadRegion(String data) {
		List courseData = adminDAO.onLoadRegion(data);
		return courseData;
	}

	// editRegionData

	@Override
	@Transactional
	public String editRegionData(String data) {
		String courseData = adminDAO.editRegionData(data);
		return courseData;
	}

	// traineeAssessmentCalender

	@Override
	@Transactional
	public List traineeAssessmentCalender(String data) {
		List courseData = adminDAO.traineeAssessmentCalender(data);
		return courseData;
	}

	// getQuestions
	@Override
	@Transactional
	public List getQuestions(String data) {
		List courseData = adminDAO.getQuestions(data);
		return courseData;
	}

	// searchFeedbackMaster

	@Override
	@Transactional
	public List searchFeedbackMaster(String data) {
		List courseData = adminDAO.searchFeedbackMaster(data);
		return courseData;
	}

	// searchAssessmentAgencyList

	@Override
	@Transactional
	public List searchAssessmentAgencyList(String data) {
		List courseData = adminDAO.searchAssessmentAgencyList(data);
		return courseData;
	}

	// /searchAssessorDetail

	@Override
	@Transactional
	public List searchAssessorDetail(String data) {
		List courseData = adminDAO.searchAssessorDetail(data);
		return courseData;
	}

	// changeAssessor
	@Override
	@Transactional
	public String changeAssessor(String data) {
		String courseData = adminDAO.changeAssessor(data);
		return courseData;
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Holiday Master
	 */

	@Override
	@Transactional
	public String addHolidayMaster(HolidayMaster p) {
		return this.adminDAO.addHolidayMaster(p);

	}

	// updateHolidayMaster
	@Override
	@Transactional
	public void updateHolidayMaster(HolidayMaster p) {
		this.adminDAO.updateHolidayMaster(p);

	}

	// removeHolidayMaster

	@Override
	@Transactional
	public void removeHolidayMaster(int id) {
		this.adminDAO.removeHolidayMaster(id);

	}

	// /getHolidayMasterById
	@Override
	@Transactional
	public HolidayMaster getHolidayMasterById(int id) {
		return this.adminDAO.getHolidayMasterById(id);

	}

	// listHolidayMaster

	@Override
	@Transactional
	public List<HolidayMaster> listHolidayMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listHolidayMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Unit Master
	 */

	@Override
	@Transactional
	public String addUnitMaster(UnitMaster p) {
		return this.adminDAO.addUnitMaster(p);

	}

	// updateUnitMaster
	@Override
	@Transactional
	public void updateUnitMaster(UnitMaster p) {
		this.adminDAO.updateUnitMaster(p);

	}

	// removeUnitMaster

	@Override
	@Transactional
	public void removeUnitMaster(int id) {
		this.adminDAO.removeUnitMaster(id);

	}

	// /getUnitMasterById
	@Override
	@Transactional
	public UnitMaster getUnitMasterById(int id) {
		return this.adminDAO.getUnitMasterById(id);

	}

	// listUnitMaster

	@Override
	@Transactional
	public List<UnitMaster> listUnitMaster2() {
		// TODO Auto-generated method stub
		return this.adminDAO.listUnitMaster2();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Module Master
	 */

	@Override
	@Transactional
	public String addSubjectMaster(SubjectMaster p) {
		return this.adminDAO.addSubjectMaster(p);

	}

	// updateModuleMaster
	@Override
	@Transactional
	public void updateSubjectMaster(SubjectMaster p) {
		this.adminDAO.updateSubjectMaster(p);

	}

	// removeModuleMaster

	@Override
	@Transactional
	public void removeSubjectMaster(int id) {
		this.adminDAO.removeSubjectMaster(id);

	}

	// /getModuleMasterById
	@Override
	@Transactional
	public SubjectMaster getSubjectMasterById(int id) {
		return this.adminDAO.getSubjectMasterById(id);

	}

	// listModuleMaster

	@Override
	@Transactional
	public List<SubjectMaster> listSubjectMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listSubjectMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Subject Master
	 */

	/*@Override
	@Transactional
	public String addSubjectMaster(SubjectMaster p) {
		return this.adminDAO.addSubjectMaster(p);

	}

	// updateSubjectMaster
	@Override
	@Transactional
	public void updateSubjectMaster(SubjectMaster p) {
		this.adminDAO.updateSubjectMaster(p);

	}

	// removeSubjectMaster

	@Override
	@Transactional
	public void removeSubjectMaster(int id) {
		this.adminDAO.removeSubjectMaster(id);

	}

	// /getSubjectMasterById
	@Override
	@Transactional
	public SubjectMaster getSubjectMasterById(int id) {
		return this.adminDAO.getSubjectMasterById(id);

	}

	// listSubjectMaster

	@Override
	@Transactional
	public List<SubjectMaster> listSubjectMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listSubjectMaster();
	}*/

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For TrainingSchedule
	 */

	@Override
	@Transactional
	public void addTrainingSchedule(TrainingSchedule p) {
		this.adminDAO.addTrainingSchedule(p);

	}

	// updateTrainingSchedule
	@Override
	@Transactional
	public void updateTrainingSchedule(TrainingSchedule p) {
		this.adminDAO.updateTrainingSchedule(p);

	}

	// removeTrainingSchedule

	@Override
	@Transactional
	public void removeTrainingSchedule(int id) {
		this.adminDAO.removeTrainingSchedule(id);

	}

	// acceptTrainingSchedule
	@Override
	@Transactional
	public void acceptTrainingSchedule(int id, int profileId, int loginUser2, int userTableId, String operation) {
		this.adminDAO.acceptTrainingSchedule(id, profileId, loginUser2, userTableId, operation);

	}

	// getTrainingScheduleById
	@Override
	@Transactional
	public TrainingSchedule getTrainingScheduleById(int id) {
		return this.adminDAO.getTrainingScheduleById(id);

	}

	@Override
	@Transactional
	public List<PersonalInformationTrainingInstitute> listTrainingInstitude() {
		return this.adminDAO.listTrainingInstitude();

	}

	@Override
	@Transactional
	public List<TrainingSchedule> listTrainingSchedule(int id, int profileId) {
		return this.adminDAO.listTrainingSchedule(id, profileId);

	}

	@Override
	@Transactional
	public List<TrainingSchedule> listTrainingSchedule() {
		// TODO Auto-generated method stub
		return this.adminDAO.listTrainingSchedule();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For State Master
	 */

	@Override
	@Transactional
	public String addStateMaster(StateMaster p) {
		return this.adminDAO.addStateMaster(p);

	}

	// updateStateMaster
	@Override
	@Transactional
	public void updateStateMaster(StateMaster p) {
		this.adminDAO.updateStateMaster(p);

	}

	// removeStateMaster

	@Override
	@Transactional
	public void removeStateMaster(int id) {
		this.adminDAO.removeStateMaster(id);

	}

	// /getStateMasterById
	@Override
	@Transactional
	public StateMaster getStateMasterById(int id) {
		return this.adminDAO.getStateMasterById(id);

	}

	// listStateMaster

	@Override
	@Transactional
	public List<StateMaster> listStateMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listStateMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For District Master
	 */

	@Override
	@Transactional
	public String addDistrictMaster(DistrictMaster p) {
		return this.adminDAO.addDistrictMaster(p);

	}

	// updateDistrictMaster
	@Override
	@Transactional
	public void updateDistrictMaster(DistrictMaster p) {
		this.adminDAO.updateDistrictMaster(p);

	}

	// removeDistrictMaster

	@Override
	@Transactional
	public void removeDistrictMaster(int id) {
		this.adminDAO.removeDistrictMaster(id);

	}

	// /getDistrictMasterById
	@Override
	@Transactional
	public DistrictMaster getDistrictMasterById(int id) {
		return this.adminDAO.getDistrictMasterById(id);

	}

	// listDistrictMaster

	@Override
	@Transactional
	public List<DistrictMaster> listDistrictMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listDistrictMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For City Master
	 */

	@Override
	@Transactional
	public String addCityMaster(CityMaster p) {
		return this.adminDAO.addCityMaster(p);

	}

	// updateCityMaster
	@Override
	@Transactional
	public void updateCityMaster(CityMaster p) {
		this.adminDAO.updateCityMaster(p);

	}

	// removeCityMaster

	@Override
	@Transactional
	public void removeCityMaster(int id) {
		this.adminDAO.removeCityMaster(id);

	}

	// /getCityMasterById
	@Override
	@Transactional
	public CityMaster getCityMasterById(int id) {
		return this.adminDAO.getCityMasterById(id);

	}

	// listCityMaster

	@Override
	@Transactional
	public List<CityMaster> listCityMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listCityMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Region Master
	 */

	@Override
	@Transactional
	public String addRegionMaster(RegionMaster p) {
		return this.adminDAO.addRegionMaster(p);

	}

	// updateRegionMaster
	@Override
	@Transactional
	public void updateRegionMaster(RegionMaster p) {
		this.adminDAO.updateRegionMaster(p);

	}

	// removeRegionMaster

	@Override
	@Transactional
	public void removeRegionMaster(int id) {
		this.adminDAO.removeRegionMaster(id);

	}

	// /getRegionMasterById
	@Override
	@Transactional
	public RegionMaster getRegionMasterById(int id) {
		return this.adminDAO.getRegionMasterById(id);

	}

	// listRegionMaster

	@Override
	@Transactional
	public List<RegionMaster> listRegionMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listRegionMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Holiday Master
	 */

	/*@Override
	@Transactional
	public String addTrainingPartner(TrainingPartner p) {
		return this.adminDAO.addTrainingPartner(p);

	}

	// updateTrainingPartner
	@Override
	@Transactional
	public void updateTrainingPartner(TrainingPartner p) {
		this.adminDAO.updateTrainingPartner(p);

	}

	// removeTrainingPartner

	@Override
	@Transactional
	public void removeTrainingPartner(int id) {
		this.adminDAO.removeTrainingPartner(id);

	}

	// /getTrainingPartnerById
	@Override
	@Transactional
	public TrainingPartner getTrainingPartnerById(int id) {
		return this.adminDAO.getTrainingPartnerById(id);

	}
*/
/*	// listTrainingPartner

	@Override
	@Transactional
	public List<TrainingPartner> listTrainingPartner() {
		// TODO Auto-generated method stub
		return this.adminDAO.listTrainingPartner();
	}
*/
	// listGenerateCertificate

	/*@Override
	@Transactional
	public List<GenerateCertificateForm> listGenerateCertificate(GenerateCertificateForm generateCertificateForm) {
		// TODO Auto-generated method stub
		return this.adminDAO.listGenerateCertificate(generateCertificateForm);
	}*/
/*
	// listTrainingClosure
	@Override
	@Transactional
	public List<TrainingClosureForm> listTrainingClosure() {
		// TODO Auto-generated method stub
		return this.adminDAO.listTrainingClosure();
	}
*/
	/****************************************** Invoice ************************************/
	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Customer Master
	 */

	@Override
	@Transactional
	public void addCustomerMaster(CustomerMaster p) {
		this.adminDAO.addCustomerMaster(p);

	}

	// updateCustomerMaster
	@Override
	@Transactional
	public void updateCustomerMaster(CustomerMaster p) {
		this.adminDAO.updateCustomerMaster(p);

	}

	// removeCustomerMaster

	@Override
	@Transactional
	public void removeCustomerMaster(int id) {
		this.adminDAO.removeCustomerMaster(id);

	}

	// /getCustomerMasterById
	@Override
	@Transactional
	public CustomerMaster getCustomerMasterById(int id) {
		return this.adminDAO.getCustomerMasterById(id);

	}

	// listCustomerMaster

	@Override
	@Transactional
	public List<CustomerMaster> listCustomerMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listCustomerMaster();
	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Tax Master
	 */

	@Override
	@Transactional
	public void addTaxMaster(TaxMaster p) {
		this.adminDAO.addTaxMaster(p);

	}

	// updateTaxMaster
	@Override
	@Transactional
	public void updateTaxMaster(TaxMaster p) {
		this.adminDAO.updateTaxMaster(p);

	}

	// removeTaxMaster

	@Override
	@Transactional
	public void removeTaxMaster(int id) {
		this.adminDAO.removeTaxMaster(id);

	}

	// /getTaxMasterById
	@Override
	@Transactional
	public TaxMaster getTaxMasterById(int id) {
		return this.adminDAO.getTaxMasterById(id);

	}

	// listTaxMaster

	@Override
	@Transactional
	public List<TaxMaster> listTaxMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listTaxMaster();
	}

	// EmployeeMonthlyCharges

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For EmployeeMonthlyCharges
	 */

	@Override
	@Transactional
	public void addEmployeeMonthlyCharges(EmployeeMonthlyCharges p) {
		this.adminDAO.addEmployeeMonthlyCharges(p);

	}

	// updateEmployeeMonthlyCharges
	@Override
	@Transactional
	public void updateEmployeeMonthlyCharges(EmployeeMonthlyCharges p) {
		this.adminDAO.updateEmployeeMonthlyCharges(p);

	}

	// removeEmployeeMonthlyCharges

	@Override
	@Transactional
	public void removeEmployeeMonthlyCharges(int id) {
		this.adminDAO.removeEmployeeMonthlyCharges(id);

	}

	// /getEmployeeMonthlyChargesById
	@Override
	@Transactional
	public EmployeeMonthlyCharges getEmployeeMonthlyChargesById(int id) {
		return this.adminDAO.getEmployeeMonthlyChargesById(id);

	}

	// listEmployeeMonthlyCharges

	@Override
	@Transactional
	public List<EmployeeMonthlyCharges> listEmployeeMonthlyCharges() {
		// TODO Auto-generated method stub
		return this.adminDAO.listEmployeeMonthlyCharges();
	}

	@Override
	@Transactional
	public List<PersonalInformationTrainee> listEligibleuser(NominateTraineeForm nominateTraineeForm,String stateId) {
		// TODO Auto-generated method stub
		return this.adminDAO.listEligibleuser(nominateTraineeForm,stateId);
	}

	@Override
	@Transactional
	public String enrollUser(String data,int stateAdminId) {
		// TODO Auto-generated method stub
		return this.adminDAO.enrollUser(data,stateAdminId);
	}


	@Override
	public AssessmentQuestions getAssessmentQuestionById(int id) {
		// TODO Auto-generated method stub
		return this.adminDAO.getAssessmentQuestionById(id);

	}

	@Override
	@Transactional
	public String updateCertificate(String data) {
		// TODO Auto-generated method stub
		return this.adminDAO.updateCertificate(data);
	}

	@Override
	@Transactional
	public List<CustomerDetails> listCustomerDetails() {
		// TODO Auto-generated method stub
		return this.adminDAO.listCustomerDetails();
	}

	@Override
	@Transactional
	public List<CustomerDetails> getCustomerDetailsByInvoice(String invoice) {
		// TODO Auto-generated method stub
		return this.adminDAO.getCustomerDetailsByInvoice(invoice);
	}

	@Override
	@Transactional
	public String addCustomerDetails(String[] data1, String[] data2, String[] data4, String customer) {
		// TODO Auto-generated method stub
		return this.adminDAO.addCustomerDetails(data1, data2, data4, customer);
	}

	@Override
	@Transactional
	public void removeCustomerDetails(int id) {
		this.adminDAO.removeCustomerDetails(id);

	}

	/**
	 * @author Jyoti Mekal
	 *
	 *         Impl For Customer Master
	 */

	@Override
	@Transactional
	public void addInvoiceMaster(InvoiceMasterForm p) {
		this.adminDAO.addInvoiceMaster(p);

	}

	// updateInvoiceMaster
	@Override
	@Transactional
	public void updateInvoiceMaster(InvoiceMasterForm p) {
		this.adminDAO.updateInvoiceMaster(p);

	}

	// removeInvoiceMaster

	@Override
	@Transactional
	public void removeInvoiceMaster(int id) {
		this.adminDAO.removeInvoiceMaster(id);

	}

	// /getInvoiceMasterById
	@Override
	@Transactional
	public InvoiceMaster getInvoiceMasterById(int id) {
		return this.adminDAO.getInvoiceMasterById(id);

	}

	@Override
	@Transactional
	public InvoiceInfoForm getInvoiceInfo(String invoice) {
		return this.adminDAO.getInvoiceInfo(invoice);

	}

	// listInvoiceMaster

	@Override
	@Transactional
	public List<InvoiceMaster> listInvoiceMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listInvoiceMaster();
	}

	@Override
	@Transactional
	public List<InvoiceMaster> listCustomCustomerMaster() {
		// TODO Auto-generated method stub
		return this.adminDAO.listCustomCustomerMaster();
	}

	@Override
	public void addManageTraining(ManageTraining p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateManageTraining(ManageTraining p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManageTraining> listManageTraining() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageTraining getManageTrainingById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeManageTraining(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRegionMapping(RegionMapping p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RegionMapping> listRegionMapping() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionMapping getRegionMappingById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeRegionMapping(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRegionMapping(RegionMapping p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addManageCourseCarricullum(ManageCourseCarricullum p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateManageCourseCarricullum(ManageCourseCarricullum p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ManageCourseCarricullum> listManageCourseCarricullum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageCourseCarricullum getManageCourseCarricullumById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeManageCourseCarricullum(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ActivateAssessmentOfTraineeForm> listactivateAssessmentOfTrainee(ActivateAssessmentOfTraineeForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFeedbackMaster(FotestFeedbackMaster p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFeedbackMaster(FotestFeedbackMaster p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FotestFeedbackMaster> listFotestFeedbackMasterForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FotestFeedbackMaster getFeedbackMasterById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFeedbackMaster(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<verifyTraineeEnrollmentForm> listVerifyTraineeEnrollment(verifyTraineeEnrollmentForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<viewEnrolledCoursesForm> listviewEnrolledCourses(viewEnrolledCoursesForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ViewTrainingCalendarForm> listviewTrainingCalendar(ViewTrainingCalendarForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManageTrainingCalendarForm> listmanageTrainingCalendar(ManageTrainingCalendarForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<activateTrainingOfTraineeForm> listactivateTrainingOfTrainee(activateTrainingOfTraineeForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FotestGenerateCertificateForm> listfotestGenerateCertificate(FotestGenerateCertificateForm p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List fotestGetQuestions(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public List<StateAdmin> stateAdminsearch(
			stateAdminForm stateAdminForm) {
		List<StateAdmin> stateAdminsearch = adminDAO
				.stateAdminsearch(stateAdminForm);
		return stateAdminsearch;
	}
	/*//fulldetailsstateadmin
	@Override
	@Transactional
	public StateAdmin FullDetailStateAdmin(int loginId) {
		StateAdmin StateAdmin = adminDAO.FullDetailStateAdmin(loginId);
		return StateAdmin;
	}*/

	
@Override
	@Transactional
	public void deleteAssessmentQuestion(int id) {
		this.adminDAO.deleteAssessmentQuestion(id);

	}

@Override
@Transactional
public int getQuestionNumber(String data) {
	// TODO Auto-generated method stub
	return this.adminDAO.getQuestionNumber(data);
}

@Override
@Transactional
public List listCalendar(int profileId,int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.listCalendar(profileId,id);
}

@Override
@Transactional
public List<TrainingScheduleForm> listtrainingScheduleMaster() {
	// TODO Auto-generated method stub
	return this.adminDAO.listtrainingScheduleMaster();
}

@Override
@Transactional
public List<UnitMaster> listUnitMaster() {
	// TODO Auto-generated method stub
	return this.adminDAO.listUnitMaster();
}

@Override
@Transactional
public TreeMap<String, List<SubjectMaster>>  allUnitModules() {
	// TODO Auto-generated method stub
	return this.adminDAO.allUnitModules();
}
@Override
@Transactional
public String saveEditTrainingScheduleMaster(String subject[],String duration[],String day[],String startTime[],String endTime[],TrainingScheduleForm form) {
	// TODO Auto-generated method stub
	return this.adminDAO.saveEditTrainingScheduleMaster(subject,duration,day,startTime,endTime,form);
}
@Override
@Transactional
public String addTrainingCalendar(TrainingCalendar p) {
	// TODO Auto-generated method stub
	return this.adminDAO.addTrainingCalendar(p);
}

@Override
@Transactional
public String shareInitiativesave(ContactTrainee contactTrainee , String id) {
	String shareInitiativesave = adminDAO.shareInitiativesave(contactTrainee , id);
	return shareInitiativesave;
}


@Override
@Transactional
public List<PersonalInformationTrainingInstitute> listTrainingInstitude2(int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.listTrainingInstitude2(id);
}

@Override
@Transactional
public List<PersonalInformationTrainer> trainingNameList2(int profileId,int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.trainingNameList2(profileId,id);
}

@Override
@Transactional
public List<StateMaster> listStateMaster2(int sid) {
	// TODO Auto-generated method stub
	return this.adminDAO.listStateMaster2(sid);
}

@Override
@Transactional
public List<SubjectMaster> allSubjects() {
	// TODO Auto-generated method stub
	return this.adminDAO.allSubjects();
}

@Override
@Transactional
public List listCalendarSearch(TrainingCalendarForm form) {
	// TODO Auto-generated method stub
	return this.adminDAO.listCalendarSearch( form);
}

@Override
@Transactional
public String createTrainingCalendar(String[] days,String[] subjectDates,String[] trainers, String[] subjects, TrainingCalendarForm p,int profileId) {
	// TODO Auto-generated method stub
	return this.adminDAO.createTrainingCalendar( days,subjectDates,trainers,subjects,p,profileId);
}

@Override
@Transactional
public 		List <List> trainerMappingState(int id, String schCode) {
	// TODO Auto-generated method stub
	return this.adminDAO.trainerMappingState(id,schCode);
}

@Override
@Transactional
public List listSchCodeSubjects(String scheduleCode) {
	// TODO Auto-generated method stub
	return this.adminDAO.listSchCodeSubjects( scheduleCode);
}

@Override
@Transactional
public String calculateEndDate(String startDate,String duration,String institute,int tcId) {
	// TODO Auto-generated method stub
	return this.adminDAO.calculateEndDate(startDate, duration,institute,tcId);
}

@Override
@Transactional
public List<TrainingCalendar> listBatchCodeList() {
	// TODO Auto-generated method stub
	return this.adminDAO.listBatchCodeList();
}

@Override
@Transactional
public List<TrainingCalendar> listBatchcodeInfo(String batchCode) {
	// TODO Auto-generated method stub
	return this.adminDAO.listBatchcodeInfo(batchCode);
}

@Override
@Transactional
public List<PersonalInformationTrainer> listpendingTrainer(int id, int profileId) {
	return this.adminDAO.listpendingTrainer(id, profileId);

}

@Override
@Transactional
public List<PersonalInformationTrainingInstitute> listpendingTrainingInstitute(int id, int profileId) {
	// TODO Auto-generated method stub
	return this.adminDAO.listpendingTrainingInstitute(id, profileId);
}

@Override
@Transactional
public List editTrainingSchedule2(int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.editTrainingSchedule2(id);
}

@Override
@Transactional
public void removeTrainingSchedule2(int id) {
	// TODO Auto-generated method stub
	this.adminDAO.removeTrainingSchedule2(id);

}

@Override
@Transactional
public List editTrainingCalendar(int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.editTrainingCalendar(id);
}
@Override
@Transactional
public TrainingCalendarForm getTrainingCalendar(int id){
	// TODO Auto-generated method stub
	return this.adminDAO.getTrainingCalendar(id);
}

@Override
@Transactional
public String updateTrainingCalendar(int trainingCalendarId,String[] days,String[] subjectDates, String[] trainers, String trainingStartDate2,
		String trainingEndDate2) {
	// TODO Auto-generated method stub
	return this.adminDAO.updateTrainingCalendar(trainingCalendarId,days,subjectDates,trainers,trainingStartDate2,trainingEndDate2);
}

@Override
@Transactional
public List getTrainingCalendarMappingTrainer(int editId) {
	// TODO Auto-generated method stub
	return this.adminDAO.getTrainingCalendarMappingTrainer(editId);
}

@Override
@Transactional
public List<UploadAssessmentForm> listofTrainee(int trainingCalendarId) {
	// TODO Auto-generated method stub
	return this.adminDAO.listofTrainee(trainingCalendarId);
}

@Override
@Transactional
public List listofTraineeforResult(String data) {
	// TODO Auto-generated method stub
	return this.adminDAO.listofTraineeforResult(data);
}

@Override
@Transactional
public String saveTraineeResult(String data) {
	// TODO Auto-generated method stub
	return this.adminDAO.saveTraineeResult(data);
}

@Override
@Transactional
public StateAdmin FullDetailStateAdmin(int loginId) {
	StateAdmin stateAdmin = adminDAO.FullDetailStateAdmin(loginId);
	return stateAdmin;
}
@Override
@Transactional
public List<TrainingCalendar> listBatchCodeListNomineeTrainee(NominateTraineeForm nominateTraineeForm) {
	// TODO Auto-generated method stub
	return this.adminDAO.listBatchCodeListNomineeTrainee(nominateTraineeForm);
}
@Override
@Transactional
public List<TrainingCalendar> listBatchCodeListStateAdmin(int stateId) {
	// TODO Auto-generated method stub
	return this.adminDAO.listBatchCodeListStateAdmin(stateId);
}

@Override
@Transactional
public String addResetPassword(String pass,String loginid) {
	// TODO Auto-generated method stub
	return this.adminDAO.addResetPassword(pass,loginid);
}

@Override
@Transactional
public List getTrainingCalendarById(int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.getTrainingCalendarById(id);
}

@Override
@Transactional
public List getEnteredSubjectDates(int editId) {
	// TODO Auto-generated method stub
	return this.adminDAO.getEnteredSubjectDates(editId);
}

@Override
public String photogallery(String linkName) {
	// TODO Auto-generated method stub
	return this.adminDAO.photogallery(linkName);
}

@Override
@Transactional
public List<PhotoGallery> listPhotoGallery() {
	// TODO Auto-generated method stub
	return this.adminDAO.listPhotoGallery();
}
@Override
@Transactional
public void removePhotoGallery(int id) {
	this.adminDAO.removePhotoGallery(id);

}
/*@Override
@Transactional
public List<String> getAllEndDates(String trainingStartDate) {
	// TODO Auto-generated method stub
	return this.adminDAO.getAllEndDates(trainingStartDate);
}*/

@Override
@Transactional
public List listNominatedTrainee(int profileId, int id) {
	// TODO Auto-generated method stub
	return this.adminDAO.listNominatedTrainee(profileId,id);
}

@Override
@Transactional
public String Helpsave(ContactTrainee contactTrainee, String id) {
	// TODO Auto-generated method stub
	String Helpsave = adminDAO.Helpsave(contactTrainee , id);
	return Helpsave;
}

@Override
@Transactional
public List<String> getAllScheduleCode() {
	// TODO Auto-generated method stub
	return adminDAO.getAllScheduleCode();
}


@Override
@Transactional
public List getScheduleCodeDetails(String schCode) {
	// TODO Auto-generated method stub
	return adminDAO.getScheduleCodeDetails(schCode);
}

@Override
@Transactional
public List<AssessmentQuestions> listAllSubjectQuestion(int a) {
	// TODO Auto-generated method stub
	return adminDAO.listAllSubjectQuestion(a);
}

@Override
@Transactional
public void activateDeActivateTrainingCalendar(int trainingCalendarId, String tableName, String status) {
	// TODO Auto-generated method stub
	 adminDAO.activateDeActivateTrainingCalendar(trainingCalendarId, tableName, status);
}
}
