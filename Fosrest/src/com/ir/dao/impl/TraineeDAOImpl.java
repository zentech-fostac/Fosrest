package com.ir.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ir.constantes.Constantes;
import com.ir.constantes.TableLink;
import com.ir.dao.AdminDAO;
import com.ir.dao.TraineeDAO;
import com.ir.form.AssessmentQuestionForm;
import com.ir.form.CertificateForm;
import com.ir.form.ChangePasswordForm;
import com.ir.form.ContactTrainee;
import com.ir.form.CourseEnrolledUserForm;
import com.ir.form.FotestFeedbackForm;
import com.ir.form.FotestCertificationForm;
import com.ir.form.GetScoreCardForm;
import com.ir.form.MyTrainingForm;
import com.ir.form.GenerateCertificateForm;
import com.ir.form.MarkAttendanceForm;
import com.ir.form.NominateTraineeForm;
import com.ir.form.OnlineTrainingForm;
import com.ir.form.RegistrationFormTrainee;
import com.ir.form.TrainingRequestForm;
import com.ir.form.fotestonlineTrainingForm;
import com.ir.form.TrainingClosureForm;
import com.ir.model.AdmitCardForm;
import com.ir.model.CertificateInfo;
import com.ir.model.CheckAadhar;
import com.ir.model.City;
import com.ir.model.ContactTraineee;
import com.ir.model.CourseEnrolledUser;
import com.ir.model.CourseName;
import com.ir.model.CourseTrainee;
import com.ir.model.CourseType;
import com.ir.model.District;
import com.ir.model.FeedbackForm;
import com.ir.model.FeedbackMaster;
import com.ir.model.KindOfBusiness;
import com.ir.model.LoginDetails;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.MappingMasterTrainer;
import com.ir.model.SubjectMaster;
import com.ir.model.NomineeTrainee;
import com.ir.model.PersonalInformationTrainee;
import com.ir.model.PersonalInformationTrainer;
import com.ir.model.PersonalInformationTrainingInstitute;
import com.ir.model.State;
import com.ir.model.Title;
import com.ir.model.TrainerSubjectMapping;
import com.ir.model.TrainingCalendar;
import com.ir.model.TrainingSchedule;
import com.ir.model.UnitMaster;
import com.ir.model.Utility;
import com.ir.service.PageLoadService;
import com.ir.util.ChangePasswordUtility;
import com.ir.util.EncryptionPasswordANDVerification;
import com.ir.util.PasswordGenerator;
import com.ir.util.SendContectMail;
import com.zentech.logger.ZLogger;

@Service
public class TraineeDAOImpl implements TraineeDAO {

	@Autowired
	@Qualifier("sessionFactory")
	public SessionFactory sessionFactory;

	@Autowired
	@Qualifier("changePasswordUtility")
	public ChangePasswordUtility changePasswordUtility;
	
	@Autowired
	@Qualifier("pageLoadService")
	PageLoadService pageLoadService;

	
	
	@Autowired
	public CourseEnrolledUser courseEnrolledUser;

	@Autowired
	@Qualifier("state")
	private State state;
	@Autowired
	@Qualifier("kindOfBusiness")
	private KindOfBusiness kindOfBusiness;
	@Autowired
	@Qualifier("district")
	private District district;
	@Autowired
	@Qualifier("city")
	private City city;
	@Autowired
	@Qualifier("title")
	private Title title;

	@Autowired
	@Qualifier("adminDAO")
	AdminDAO adminDAO;

	@Override
	public KindOfBusiness getKid(int id) {
		Session ss = sessionFactory.getCurrentSession();
		KindOfBusiness kid = (KindOfBusiness) ss.load(KindOfBusiness.class, id);
		return kid;
	}

	@Override
	public State getState(int id) {
		Session s = sessionFactory.getCurrentSession();
		State ss = (State) s.load(State.class, id);
		return ss;
	}

	@Override
	public City getCity(int id) {
		Session s = sessionFactory.getCurrentSession();
		City cc = (City) s.load(City.class, id);
		return cc;
	}

	@Override
	public District getDistrict(int id) {
		Session s = sessionFactory.getCurrentSession();
		District dd = (District) s.load(District.class, id);
		return dd;
	}

	@Override
	public Title getTitle(int id) {
		Session s = sessionFactory.getCurrentSession();
		Title tt = (Title) s.load(Title.class, id);
		return tt;
	}


	@Override
	public String contactTraineeSave(ContactTrainee contactTrainee, String id) {
		SendContectMail traineeMaail = new SendContectMail();
		Session session = sessionFactory.getCurrentSession();
		ContactTraineee contactTraineeModel = new ContactTraineee();
		String email = contactTrainee.getEmailAddress();
		String msg = contactTrainee.getMessageDetails();
		new ZLogger("contactTraineeSave", "user id in dao impl  :::::" + id, "TraineeDAOImpl.java");
		traineeMaail.mailProperty(msg, email, id);
		contactTraineeModel.setEmailAddress(email);
		contactTraineeModel.setMessageDetails(msg);
		contactTraineeModel.setUserId(id);
		contactTraineeModel.setDescription("Hello my  Id is  :- " + id
				+ "  My EmailId is :- " + email + " My message to You:-  "
				+ msg);
		
		Integer contactTraineeModelId = (Integer) session
				.save(contactTraineeModel);
		if (contactTraineeModelId > 0 && contactTraineeModelId != null) {
			return "created";
		} else {
			return "error";
		}
	}

	@Override
	public List<CourseName> courseNameList() {
		Session session = sessionFactory.getCurrentSession();
		List<CourseName> courseNameList = null;
		try{
			Query query = session
					.createQuery("from CourseName where coursetypeid = '" + 1 + "'");
			 courseNameList = query.list();	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return courseNameList;
	}

	@Override
	public List<CourseName> courseNameListByType(int courseType) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from CourseName where coursetypeid = "
						+ courseType);
		List<CourseName> courseNameList = query.list();
		return courseNameList;
	}
	
	
	@Override
	public CourseTrainee getCourseTrainingByCourseTypeID(int typeId) {
		Session session = sessionFactory.getCurrentSession();
		CourseTrainee courseTrainee = new CourseTrainee();
		StringBuffer sql = new StringBuffer();
		sql.append("Select D.coursenameid,D.coursename,D.courseduration ");
		sql.append(" ,concat(E.firstname , ' ' , E.middlename , ' ' , E.lastname ) ,F.assessmentagencyname,G.contentnameinput, G.contentlinkinput, G.contenttypeinput, C.coursetype");
		sql.append(" ,D.courseCode from courseenrolleduser A");
		sql.append(" inner join trainingcalendar B on(A.trainingcalendarid=B.trainingcalendarid)");
		sql.append(" inner join coursetype C on(B.coursetype=C.coursetypeid)");
		sql.append(" inner join coursename D on(B.coursename=D.coursenameid)");
		sql.append(" left outer join personalinformationassessor E on(B.assessor=E.personalinformationassessorid)");
		sql.append(" left outer join manageassessmentagency F on(E.assessmentagencyname=F.manageassessmentagencyid)");
		sql.append(" left outer join managecoursecontent G on(D.coursenameid=G.coursenameinput)");
		sql.append(" Where A.logindetails = "+typeId+" and A.status = 'N'");
		
		Query query = session.createSQLQuery(sql.toString());
		List<Object[]> courseTraineeList = (List<Object[]>) query.list();
		if (courseTraineeList.size() > 0) {
			Object[] o = courseTraineeList.get(0);
			courseTrainee.setCourseNameID(o[0] == null ? "" : o[0].toString());
			courseTrainee.setCourseName(o[1] == null ? "" : o[1].toString());
			courseTrainee.setCourseDuration(o[2] == null ? "" : o[2].toString());
			courseTrainee.setAssessor(o[3] == null ? "" : o[3].toString());
			courseTrainee.setAssessorAgency(o[4] == null ? "" : o[4].toString());
			courseTrainee.setContentNameInput(o[5] == null ? "" : o[5].toString());
			courseTrainee.setContentLinkInput(o[6] == null ? "" : o[6].toString());
			courseTrainee.setContentNameInput(o[7] == null ? "" : o[7].toString());
			courseTrainee.setCourseTypeId(o[8] == null ? "" : o[8].toString());
			courseTrainee.setCourseCode(o[9] == null ? "" : o[9].toString());
		
			
		} 
		return courseTrainee;

	}


	@Override
	public List<CourseTrainee> getCourseTrainingByCourseTypeIDList(int typeId) {
		Session session = sessionFactory.getCurrentSession();
		
		List<CourseTrainee>  listCourseTrainee =  new ArrayList<CourseTrainee>();
		StringBuffer sql = new StringBuffer();
		sql.append("Select distinct D.coursenameid,D.coursename,D.courseduration ");
		sql.append(" ,concat(E.firstname , ' ' , E.middlename , ' ' , E.lastname ) ,F.assessmentagencyname,G.contentnameinput, G.contentlinkinput, G.contenttypeinput, C.coursetype");
		sql.append(" ,D.courseCode from courseenrolleduser A");
		sql.append(" inner join trainingcalendar B on(A.trainingcalendarid=B.trainingcalendarid)");
		sql.append(" inner join coursetype C on(B.coursetype=C.coursetypeid)");
		sql.append(" inner join coursename D on(B.coursename=D.coursenameid)");
		sql.append(" left outer join personalinformationassessor E on(B.assessor=E.personalinformationassessorid)");
		sql.append(" left outer join manageassessmentagency F on(E.assessmentagencyname=F.manageassessmentagencyid)");
		sql.append(" left outer join managecoursecontent G on(D.coursenameid=G.coursenameinput)");
		sql.append(" Where A.logindetails = "+typeId+" and A.status = 'N'");
		
		Query query = session.createSQLQuery(sql.toString());
		List<Object[]> courseTraineeList = (List<Object[]>) query.list();
		for( int i = 0 ; i < courseTraineeList.size() ; i++) {
			Object[] o = courseTraineeList.get(i);
			CourseTrainee courseTrainee = new CourseTrainee();
			courseTrainee.setCourseNameID(o[0] == null ? "" : o[0].toString());
			courseTrainee.setCourseName(o[1] == null ? "" : o[1].toString());
			courseTrainee.setCourseDuration(o[2] == null ? "" : o[2].toString());
			courseTrainee.setAssessor(o[3] == null ? "" : o[3].toString());
			courseTrainee.setAssessorAgency(o[4] == null ? "" : o[4].toString());
			courseTrainee.setContentNameInput(o[5] == null ? "" : o[5].toString());
			courseTrainee.setContentLinkInput(o[6] == null ? "" : o[6].toString());
			System.out.println("o[5] "+o[5] + " o[6] "+o[6]);
			courseTrainee.setContentType(o[7] == null ? "" : o[7].toString());
			courseTrainee.setCourseTypeId(o[8] == null ? "" : o[8].toString());
			courseTrainee.setCourseCode(o[9] == null ? "" : o[9].toString());
			System.out.println("before adding "+courseTrainee.getContentNameInput());
			listCourseTrainee.add(courseTrainee);
			
		} 
		System.out.println("size "+listCourseTrainee.size());
		return listCourseTrainee;

	}

	@Override
	public CourseName getCourseName(int loginId) {
		CourseName courseName = new CourseName();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select cn.coursename, cn.coursenameid, cn.courseduration "
				+ "from courseenrolleduser  ceu "
				+ "inner join trainingcalendar tc on tc.trainingcalendarid =   ceu.trainingcalendarid "
				+ "inner join coursename cn on cn.coursenameid = tc.coursename where ceu.status = 'N' AND ceu.logindetails = "
				+ loginId;
		Query query = session.createSQLQuery(sql);
		List<Object[]> courseNameList = (List<Object[]>) query.list();
		if (courseNameList.size() > 0) {
			Object[] o = courseNameList.get(0);
			courseName.setCoursename(o[0].toString());
			courseName.setCoursenameid(Integer.parseInt(o[1].toString()));
			courseName.setCourseduration(o[2].toString());
			return courseName;
		} else {
			return courseName;
		}

	}

	@Override
	public CourseName getCourseDetails(int loginId) {
		CourseName courseName = new CourseName();
		Session session = sessionFactory.getCurrentSession();
		String sql = "select cn.coursename,cn.coursenameid,cn.courseduration from coursename cn,courseenrolled cnrld where cn.coursenameid=cnrld.coursenameid and cnrld.logindetails="
				+ loginId;
		
		Query query = session.createSQLQuery(sql);
		List<Object[]> courseNameList = (List<Object[]>) query.list();
		if (courseNameList.size() > 0) {
			Object[] o = courseNameList.get(0);
			courseName.setCoursename(o[0].toString());
			courseName.setCoursenameid(Integer.parseInt(o[1].toString()));
			courseName.setCourseduration(o[2].toString());
			return courseName;
		} else {
			return courseName;
		}

	}

	@Override
	public List<FeedbackMaster> getFeedMasterList(int profileId) {
		Session session = sessionFactory.getCurrentSession();
		String profile = "";
		if (profileId == 3) {
			profile = Constantes.TRAINEE_LABEL;
		} else if (profileId == 4) {
			profile = Constantes.TRAINER_LABEL;
		}

		Query query = session
				.createSQLQuery("select feedbacktypeid,feedback from feedbackmaster where upper(coursetype)='"
						+ profile + "'");
		List<FeedbackMaster> feedbackMasters = query.list();
		return feedbackMasters;
	}

	@Override
	public List<ManageTrainingPartner> trainingPartnerList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ManageTrainingPartner");
		List<ManageTrainingPartner> trainingPartnerList = query.list();
		return trainingPartnerList;
	}

	@Override
	public List<State> trainingCenterStateList() {
		List<State> st = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select s.stateid, s.statename  from  state as s  "
				+ " inner join personalinformationtrainingpartner as pitp on pitp.trainingpartnerpermanentstate = s.stateid "
				+ " inner join managetrainingpartner as mtp on mtp.managetrainingpartnerid  = pitp.trainingpartnername  ";
		Query query = session.createSQLQuery(sql);
		List<Object[]> trainingCenterStateList = (List<Object[]>) query.list();
		st = new ArrayList<>();
		for (int i = 0; i < trainingCenterStateList.size(); i++) {
			State stt = new State();

			Object[] o = trainingCenterStateList.get(i);
			stt.setStateId((int) o[0]);
			stt.setStateName(o[1].toString());
			st.add(stt);

		}
		System.out.println(st);
		new ZLogger("trainingCenterStateList", "  " + st, "TraineeDAOImpl.java");
		return st;
	}

	@Override
	public String updateTrainee(
			RegistrationFormTrainee registrationFormTrainee, Integer ss) {

		Session session = sessionFactory.getCurrentSession();

		new ZLogger("updateTrainee", "  " + ss, "TraineeDAOImpl.java");
		State ps = getState(registrationFormTrainee.getResState());
		State cs = getState(registrationFormTrainee.getResState());
		State bs = getState(registrationFormTrainee.getBussState());
		District pd = getDistrict(registrationFormTrainee
				.getResidentialDistrict());
		District cd = getDistrict(registrationFormTrainee
				.getResidentialDistrict());
		District bd = getDistrict(registrationFormTrainee.getBussDistrict());
		City pc = getCity(registrationFormTrainee.getResCity());
		City cc = getCity(registrationFormTrainee.getCorrespondenceCity());
		City bc = getCity(registrationFormTrainee.getBussCity());
		KindOfBusiness kob = getKid(registrationFormTrainee.getKindOfBusiness());
		PersonalInformationTrainee personalInformationTrainee = (PersonalInformationTrainee) session
				.load(PersonalInformationTrainee.class, ss);

		new ZLogger("updateTrainee","this is pin code    "+ registrationFormTrainee.getResPincode(), "TraineeDAOImpl.java");
		new ZLogger("updateTrainee","this is state     "+ registrationFormTrainee.getResState(), "TraineeDAOImpl.java");
		System.out.println(registrationFormTrainee.getBusinessAddressLine1());
		boolean correspondADD = registrationFormTrainee.isCheckCorrespondence();
		boolean checkCompany = registrationFormTrainee.isCheckCompany();

		personalInformationTrainee.setGender(registrationFormTrainee
				.getGender());


		if (registrationFormTrainee.getKindOfBusiness() == 6) {
			/*personalInformationTrainee.setKindOfBusiness(kob);
			personalInformationTrainee.setDesignation(null);
			personalInformationTrainee.setCompanyName(null);
			personalInformationTrainee.setRegistrationNo(null);
			personalInformationTrainee.setBusinessAddressLine1(null);
			personalInformationTrainee.setBusinessAddressLine2(null);
			personalInformationTrainee.setBussCity(null);
			personalInformationTrainee.setBussDistrict(null);
			personalInformationTrainee.setBussState(null);
			personalInformationTrainee.setBussPincode(null);*/

		} else {
			new ZLogger("updateTrainee","Else Kind of business", "TraineeDAOImpl.java");
			/*personalInformationTrainee.setCompanyName(registrationFormTrainee
					.getCompanyName());
			personalInformationTrainee.setDesignation(registrationFormTrainee
					.getDesignation());
			personalInformationTrainee
					.setRegistrationNo(registrationFormTrainee
							.getRegistrationNo());
			personalInformationTrainee.setKindOfBusiness(kob);*/

			if (checkCompany) {
				/*personalInformationTrainee
						.setBusinessAddressLine1(registrationFormTrainee
								.getCorrespondenceAddress1());
				personalInformationTrainee
						.setBusinessAddressLine2(registrationFormTrainee
								.getCorrespondenceAddress2());
				personalInformationTrainee.setBussCity(cc);
				personalInformationTrainee.setBussDistrict(cd);
				personalInformationTrainee.setBussState(cs);
				personalInformationTrainee
						.setBussPincode(registrationFormTrainee
								.getCorrespondencePincode());
				personalInformationTrainee.setCheckCompany("true");*/

			} else {
				/*personalInformationTrainee
						.setBusinessAddressLine1(registrationFormTrainee
								.getBusinessAddressLine1());
				personalInformationTrainee
						.setBusinessAddressLine2(registrationFormTrainee
								.getBusinessAddressLine2());
				personalInformationTrainee
						.setDesignation(registrationFormTrainee
								.getDesignation());

				personalInformationTrainee.setBussState(bs);
				personalInformationTrainee.setBussCity(bc);
				personalInformationTrainee.setBussDistrict(bd);
				personalInformationTrainee
						.setBussPincode(registrationFormTrainee
								.getBussPincode());
				personalInformationTrainee.setCheckCompany("false");*/
			}
		}

		personalInformationTrainee
				.setCorrespondenceAddress1(registrationFormTrainee
						.getCorrespondenceAddress1());
		personalInformationTrainee
				.setCorrespondenceAddress2(registrationFormTrainee
						.getCorrespondenceAddress2());
		/*personalInformationTrainee.setCorrespondenceState(cs);
		personalInformationTrainee.setCorrespondenceDistrict(cd);
		personalInformationTrainee.setCorrespondenceCity(cc);*/
		personalInformationTrainee.setEmail(registrationFormTrainee.getEmail());
		personalInformationTrainee.setMobile(registrationFormTrainee
				.getMobile());
		personalInformationTrainee
				.setCorrespondencePincode(registrationFormTrainee
						.getCorrespondencePincode());
		if (correspondADD) {
			/*personalInformationTrainee
					.setResidentialLine1(registrationFormTrainee
							.getCorrespondenceAddress1());
			personalInformationTrainee
					.setResidentialLine2(registrationFormTrainee
							.getCorrespondenceAddress2());
			personalInformationTrainee.setResState(cs);
			personalInformationTrainee.setResCity(cc);
			personalInformationTrainee.setResidentialDistrict(cd);
			personalInformationTrainee.setResPincode(registrationFormTrainee
					.getCorrespondencePincode());
			personalInformationTrainee.setCheckPermanent("true");*/
		} else {
			personalInformationTrainee
					.setResidentialLine1(registrationFormTrainee
							.getResidentialAddressLine1());
			personalInformationTrainee
					.setResidentialLine2(registrationFormTrainee
							.getResidentialAddressLine2());
			/*personalInformationTrainee.setResState(ps);
			personalInformationTrainee.setResCity(pc);
			personalInformationTrainee.setResidentialDistrict(pd);
			personalInformationTrainee.setResPincode(registrationFormTrainee
					.getResPincode());
			personalInformationTrainee.setCheckPermanent("false");*/
		}

		session.update(personalInformationTrainee);
		return null;
	}

	/*
	 * @Override public String changePasswordTraineeSave(ChangePasswordForm
	 * changePasswordForm, int id) { System.out.println("change pwd dai impl");
	 * Session session =sessionFactory.openSession();
	 * System.out.println(changePasswordForm.getLoginid());
	 * PersonalInformationTrainee
	 * personalInformationTrainee=(PersonalInformationTrainee)
	 * session.load(PersonalInformationTrainee.class,
	 * Integer.parseInt(changePasswordForm.getLoginid()));
	 * System.out.println("id  "+ id); LoginDetails ld = new LoginDetails();
	 * ld.setPassword(changePasswordForm.getNewPassword());
	 * ld.setLoginId(changePasswordForm.getLoginidd()); ld.setProfileId(3);
	 * ld.setStatus("A"); session.update(personalInformationTrainee);
	 * session.beginTransaction().commit(); session.close(); return "created";
	 * 
	 * }
	 */

	@Override
	public boolean changePasswordTraineeSave(
			ChangePasswordForm changePasswordForm, String id) {
		boolean confirm = Boolean.FALSE;
		Session session = sessionFactory.getCurrentSession();
	
		try{
			String oldPassword = changePasswordForm.getOldPassword();
			String newPassword = changePasswordForm.getNewPassword();
			String conPswd=changePasswordForm.getConfirmPwd();
			if(newPassword.equals(conPswd)){
				System.out.println("new"+newPassword.length());
				if(newPassword.length()>6&&newPassword.length()<16){
					
					confirm = changePasswordUtility.changePasswordUtil(oldPassword,
							newPassword, id, session);
				}
			}
			
			new ZLogger("updateTrainee","new pass   " + oldPassword, "TraineeDAOImpl.java");
		

		}catch(Exception e){
			e.printStackTrace();
		}
		
		return confirm;
	}

	@Override
	public String basicSave(CourseEnrolledUserForm courseEnrolledUserForm,
			int loginid, int tableID, Integer profileID) {

		new ZLogger("basicSave","basicSave", "TraineeDAOImpl.java");
		Session session = sessionFactory.getCurrentSession();
		int maxId = 0 ;
		String sql = "select coalesce(max(rollseqNo) + 1,1) from courseenrolleduser";
		Query maxIDList = session.createSQLQuery(sql);
		List list = maxIDList.list();
		new ZLogger("basicSave","list.size() "+list.size(), "TraineeDAOImpl.java");
		if(list.size() > 0){
			maxId = (int) (list.get(0) == null ? "1" : list.get(0));
		}
		TrainingCalendar tc = (TrainingCalendar) session.load(TrainingCalendar.class, courseEnrolledUserForm.getTrainingCalendarId());
		//CourseName ct = (CourseName) session.load(CourseName.class, tc.getCourseName());
		//CourseType ctype = (CourseType) session.load(CourseType.class, tc.getCourseType());
		String rollNo = "";
		/*rollNo = ct.getCourseCode()+""+StringUtils.leftPad(String.valueOf(maxId), 5, "0");
		if(ctype != null && ctype.getCourseType().toUpperCase().equals("TOT")){
			rollNo = "T"+rollNo;
		}*/
		courseEnrolledUser.setRollno(rollNo);
		courseEnrolledUser.setRollSeqNo(maxId);
		courseEnrolledUser.setLoginDetails(loginid);
		courseEnrolledUser.setTrainingCalendarId(courseEnrolledUserForm
				.getTrainingCalendarId());
		courseEnrolledUser.setStatus("N");
		courseEnrolledUser.setPaymentstatus("Pending");
		courseEnrolledUser.setEnrolledby("Trainee");
		courseEnrolledUser.setProfileId(profileID);

		// Integer ce =0;
		Integer ce = (Integer) session.save(courseEnrolledUser);
		if (ce != null && ce.intValue() > 0) {
		}
		return rollNo;
	}

	@Override
	public long advanceTraineeSave(
			CourseEnrolledUserForm courseEnrolledUserForm, int loginid,
			int tableID, Integer profileID) {
		new ZLogger("advanceTraineeSave","advanceTraineeSave", "TraineeDAOImpl.java");
		Session session = sessionFactory.getCurrentSession();
		long date = System.currentTimeMillis();
		new ZLogger("advanceTraineeSave","TrainingCalendarId()   :"+ courseEnrolledUserForm.getTrainingCalendarId(), "TraineeDAOImpl.java");
		new ZLogger("basicSave","tableID "+tableID, "TraineeDAOImpl.java");
		
		courseEnrolledUser.setLoginDetails(loginid);
		courseEnrolledUser.setProfileId(3);
		courseEnrolledUser.setTrainingCalendarId(courseEnrolledUserForm
				.getTrainingCalendarId());
		courseEnrolledUser.setStatus("N");
		courseEnrolledUser.setPaymentstatus("Pending");
		if (profileID != null && profileID == 3) {
			courseEnrolledUser.setEnrolledby("Trainee");
			courseEnrolledUser.setProfileId(profileID);
		} else if (profileID != null && profileID == 4) {
			courseEnrolledUser.setEnrolledby("Trainer");
			courseEnrolledUser.setProfileId(profileID);
		}
		// Integer ce =0;
		session.save(courseEnrolledUser);
		return date;
	}

	@Override
	public long specialTraineeSave(
			CourseEnrolledUserForm courseEnrolledUserForm, int loginid,
			int tableID, Integer profileID) {
		Session session = sessionFactory.getCurrentSession();
		long date = System.currentTimeMillis();
		courseEnrolledUser.setLoginDetails(loginid);
		courseEnrolledUser.setProfileId(3);
		courseEnrolledUser.setTrainingCalendarId(courseEnrolledUserForm
				.getTrainingCalendarId());
		//courseEnrolledUser.setRollno(date);
		courseEnrolledUser.setStatus("N");
		if (profileID != null && profileID == 3) {
			courseEnrolledUser.setEnrolledby("Trainee");
			courseEnrolledUser.setProfileId(profileID);
		} else if (profileID != null && profileID == 4) {
			courseEnrolledUser.setEnrolledby("Trainer");
			courseEnrolledUser.setProfileId(profileID);
		}
		courseEnrolledUser.setPaymentstatus("Pending");

		// Integer ce =0;
		session.save(courseEnrolledUser);
		return date;
	}

	@Override
	public boolean changePasswordAssesorSave(
			ChangePasswordForm changePasswordForm, String id) {
		String oldPassword = changePasswordForm.getOldPassword();
		String newPassword = changePasswordForm.getNewPassword();
		// String idd=changePasswordForm.getLoginid();
		System.out.println("new pass   " + oldPassword);
		new ZLogger("changePasswordAssesorSave", "", "TraineeDAOImpl.java");

		boolean confirm = changePasswordUtility.changePasswordUtil(oldPassword,
				newPassword, id);

		return confirm;
	}

	public AdmitCardForm generateAdmitCard(int loginId, int profileId) {
			String str_query = "select cn.coursename as courseName,"
				+ " ctype.coursetype as category, "
				+ " pit.fathername as fatherName, titlename as title, pit.firstname ||' '|| pit.middlename ||' '|| pit.lastname  as name ,"
				+ " tcal.trainingcenter as trainingCenterCode,"
				+ " pitp.trainingpartnerpermanentline1||','|| pitp.trainingpartnerpermanentline2 as address,"
				+ " cast(ce.rollno as varchar(100)) as rollNo  , cty.cityname  "
				+ ", district.districtname as district , coalesce(cn.coursecode, '') as coursecode , state.statename  , tcal.trainingdate as trainingstartdate , tcal.trainingtime as trainingenddate , cn.courseduration as courseduration , pitp.firstname || ' ' || pitp.middlename || ' ' || pitp.lastname as trainingcentername  , case when gender='M' then 'MALE' else 'FEMALE' end , pit.mobile"
				+ " from courseenrolleduser ce "
				+ " inner join personalinformationtrainee pit on pit.logindetails = ce.logindetails   "
				+ " inner join title on title.titleId = pit.title "
				+ " inner join trainingcalendar tcal on tcal.trainingcalendarid = ce.trainingcalendarid "
				+ " inner join personalinformationtrainingpartner pitp on pitp.personalinformationtrainingpartnerid = tcal.trainingcenter "
				+ " inner join state state on (state.stateid = pit.correspondencestate) "
				+ " inner join district district on district.districtid = pit.correspondencedistrict "
				+ " inner join city cty on (cty.cityid = pit.correspondencecity)  "
				+ " inner join coursename cn on cn.coursenameid = tcal.coursename "
				+ " inner join coursetype ctype on ctype.coursetypeid = cn.coursetypeid" 
				+ " where ce.status = 'N' AND ce.logindetails = " + loginId;
		AdmitCardForm admitcard = new AdmitCardForm();
		try {
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createSQLQuery(str_query);
		// List records = query.list();
		List<Object[]> records = (List<Object[]>) query.list();
			if (records.size() > 0) {

				Object[] obj = records.get(0);
				
				admitcard.setCourseName(obj[0] == null ? "" : obj[0].toString());
				admitcard.setCategory(obj[1].toString());
				admitcard.setFatherName(obj[2].toString());
				admitcard.setTitle(obj[3].toString());
				admitcard.setName(obj[4].toString());
				admitcard.setTrainingCenterCode((int) obj[5]);
				admitcard.setAddress(obj[6].toString());
				//BigInteger rollNo = (BigInteger) obj[7];
				admitcard.setRollNo(obj[7] == null ? "" : obj[7].toString());
				admitcard.setCity(obj[8].toString());
				admitcard.setDistrict(obj[9].toString());
				admitcard.setCourseCode(obj[10].toString());
				admitcard.setState(obj[11].toString());
				admitcard.setTrainingStartDate(obj[12].toString());
				admitcard.setTrainingEndDate(obj[13].toString());
				admitcard.setCourseDuration(obj[14].toString());
				admitcard.setTrainingCenterName(obj[15].toString());
				admitcard.setGender(obj[16].toString());
				admitcard.setMobile(obj[17].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			new ZLogger("generateAdmitCard","Exception while  :"+e.getMessage(), "TraineeDAOImpl.java");
		}

		new ZLogger("generateAdmitCard","Exception while  :"+admitcard, "TraineeDAOImpl.java");
		return admitcard;
	}

	@Override
	public List<FeedbackForm> getFeedbackDetails(Utility utility) {
		
		new ZLogger("getFeedbackDetails", "", "TraineeDAOImpl.java");

		String str_query = "select fbd.feedbackId as feedBackFormId ,fdm.feedback as feedbackId,fbd.feedbackrating as feedbackRating,fbd.userid as userid from  feedbackdetail fbd inner join personalinformationtrainingpartner pitp on pitp.personalinformationtrainingpartnerid="
				+ utility.getFeedbackId()
				+ " and "
				+ " pitp.logindetails=CAST(CAST (fbd.userid AS NUMERIC(19,4)) AS INT) inner join trainingcalendar tc on";
		if (utility.getTrainingDate() != null
				&& utility.getTrainingDate() != "") {
			str_query += " tc.trainingdate='" + utility.getTrainingDate()
					+ "' and ";
		}
		str_query += " tc.coursetype="
				+ utility.getCourseTypeId()
				+ " and "
				+ "coursename="
				+ utility.getCourseNameId()
				+ " "
				+ "inner join feedbackmaster fdm on fdm.feedbacktypeid=CAST(CAST (fbd.feedbackId AS NUMERIC(19,4)) AS INT)";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(str_query);
		List<FeedbackForm> list = query.list();
		return list;
	}

	@Override
	public List<Integer> getCurrentSubjectId(int loginId) {
		
		new ZLogger("getCurrentSubjectId", "", "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		/**
		 * TODO - add training status change training status as 'A' while course
		 * enrollment
		 **/
		//String sql = "select subjectid from trainingcalendarmapping where batchcode=(select batchcode from trainingcalendar where trainingcalendarid=(select nt.trainingcalendarid  from nomineetrainee nt inner join trainingcalendar ts on (nt.trainingcalendarid = ts.trainingcalendarid)  where nt.status='N' and nt.logindetails="+loginId+"))";
		String sql = "select subjectMaster from assessmentQuestions where isactive='Y' and subjectmaster in(select subjectid from trainingcalendarmapping where batchcode=(select batchcode from trainingcalendar where trainingcalendarid=(select nt.trainingcalendarid  from nomineetrainee nt inner join trainingcalendar ts on (nt.trainingcalendarid = ts.trainingcalendarid)  where nt.status='N' and nt.logindetails= "+loginId+"))) order by assessmentquestionid";

		Query query = session. createSQLQuery(sql);
		List<Integer> subId = query.list();
		/*if (subId.size() > 0) {
			return  listCourseNameId.get(0);
		}
		return -1;*/
		return subId;
	}

	@Override
	public AdmitCardForm generateTrainerAdmitCard(int loginId, int profileId) {

		String str_query = "select cn.coursename as courseName,"
				+ " ctype.coursetype as category, "
				+ " pit.fathername as fatherName, titlename as title, pit.firstname ||' '|| pit.middlename ||' '|| pit.lastname  as name ,"
				+ " tcal.trainingcenter as trainingCenterCode,"
				+ " pitp.trainingpartnerpermanentline1||','|| pitp.trainingpartnerpermanentline2 as address,"
				+ " ce.rollno as rollNo "
				+ ", district.districtname as city"
				+

				" from courseenrolleduser ce "
				+

				" inner join personalinformationtrainer pit on pit.logindetails = ce.logindetails   "
				+ " inner join title on title.titleId = pit.title "
				+ " inner join trainingcalendar tcal on tcal.trainingcalendarid = ce.trainingcalendarid "
				+ " inner join personalinformationtrainingpartner pitp on pitp.personalinformationtrainingpartnerid = tcal.trainingcenter "
				+ " inner join district district on district.districtid = pitp.trainingpartnerpermanentdistrict "
				+ " inner join coursename cn on cn.coursenameid = tcal.coursename "
				+ " inner join coursetype ctype on ctype.coursetypeid = cn.coursetypeid "
				+ "where ce.logindetails = " + loginId;

		Session session = sessionFactory.getCurrentSession();
		AdmitCardForm admitcard = new AdmitCardForm();
		Query query = session.createSQLQuery(str_query);
		// List records = query.list();
		List<Object[]> records = (List<Object[]>) query.list();
		try {
			if (records.size() > 0) {

				Object[] obj = records.get(0);
				admitcard.setCourseName(obj[0].toString());
				admitcard.setCategory(obj[1].toString());
				admitcard.setFatherName(obj[2].toString());
				admitcard.setTitle(obj[3].toString());
				admitcard.setName(obj[4].toString());
				admitcard.setTrainingCenterCode((int) obj[5]);
				admitcard.setAddress(obj[6].toString());
				//BigInteger rollNo = (BigInteger) obj[7];
				admitcard.setRollNo(obj[7].toString());
				admitcard.setCity(obj[8].toString());
			}
		} catch (Exception e) {
			System.out
					.println("Exception while retrieving admit card details : "
							+ e.getMessage());
			new ZLogger("generateTrainerAdmitCard","Exception while generateTrainerAdmitCard :"+e.getMessage(), "TraineeDAOImpl.java");
		}
		return admitcard;
	}

	@Override
	public String getDefaultMailID(int loginId, int profileId) {
		// TODO Auto-generated method stub
		TableLink data = TableLink.getByprofileID(profileId);
		new ZLogger("getDefaultMailID","getDefaultMailID :"+data, "TraineeDAOImpl.java");
		System.out.println("Table Name == " + data.tableName());
		Session session = sessionFactory.getCurrentSession();
		String sql = "select " + data.email() + " from " + data.tableName()
				+ " where logindetails = " + loginId;
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		new ZLogger("getDefaultMailID","list.size():"+list.size(), "TraineeDAOImpl.java");
		return (String) list.get(0);
	}

	@Override
	public PersonalInformationTrainee fullDetail(int loginId) {
		new ZLogger("PersonalInformationTrainee","PersonalInformationTrainee :"+loginId, "TraineeDAOImpl.java");
		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		Query query = session
				.createQuery("from PersonalInformationTrainee where loginDetails = '"
						+ i + "'");
		List<PersonalInformationTrainee> list = query.list();
		PersonalInformationTrainee personalInformationTrainee = null;
		for (PersonalInformationTrainee personalInformationTrainee1 : list) {
			personalInformationTrainee = personalInformationTrainee1;
		}
		return personalInformationTrainee;
	}

	@Override
	public int getTableIdForEnrolmentID(int loginId, int profileId) {
		// TODO Auto-generated method stub
		new ZLogger("getTableIdForEnrolmentID","loginId :"+loginId + " profileId "+profileId , "TraineeDAOImpl.java");
		
		TableLink data = TableLink.getByprofileID(profileId);
		Session session = sessionFactory.getCurrentSession();
		String sql = "";
		if (profileId == 3) {
			sql = "select id from "
					+ data.tableName() + " where logindetails = " + loginId;
		} else if (profileId == 4) {
			sql = "select id from "
					+ data.tableName() + " where logindetails = " + loginId;
		} else if (profileId == 5 ) {
			sql = "select id from "
					+ data.tableName() + " where logindetails = " + loginId;
		}
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		return (Integer) list.get(0);
	}

	@Override
	public Boolean updateSteps(int id , int steps) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		PersonalInformationTrainee personalInformationTrainee = (PersonalInformationTrainee) session
				.load(PersonalInformationTrainee.class, id);
		new ZLogger("updateSteps","tableID :"+id  , "TraineeDAOImpl.java");
		personalInformationTrainee.setSteps(steps);
		session.update(personalInformationTrainee);
		return true;
	}

	@Override
	public String isCourseOnline(int userID) {
		// TODO Auto-generated method stub
		
		new ZLogger("isCourseOnline", "userId"+userID, "TraineeDAOImpl.java");

		String status = "";
		Session session = sessionFactory.getCurrentSession();
		String sql = "select C.classroom||C.online course from courseenrolleduser A inner join trainingcalendar B on(A.trainingcalendarid=B.trainingcalendarid) inner join coursename C on(B.coursename=C.coursenameid)"
				+ " where A.status = 'N' AND A.logindetails = " + userID;
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		if(list.size() > 0){
			System.out.println(list.size());
			new ZLogger("isCourseOnline","list.size() "+ list.size(), "TraineeDAOImpl.java");
			status = (String) list.get(0);
		}
		return status;
	}

	@Override
	public Boolean closeCourse(int userId,  String status) {
		// TODO Auto-generated method stub
		int id = 0;
		new ZLogger("closeCourse", "userId"+userId, "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		String sql = "select id from nomineetrainee where status = 'N' AND logindetails  = "
				+ userId;
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		if(list.size() > 0){
			id = (Integer) (list.get(0) == null ? 0 : list.get(0));
			if(id > 0){
				NomineeTrainee nomineeTrainee = (NomineeTrainee) session
						.load(NomineeTrainee.class, id);
				nomineeTrainee.setStatus(status);
				session.update(nomineeTrainee);
			}
		}
		
		return true;
	}

	@Override
	public String isTraineeEligible(int userID) {
		// TODO Auto-generated method stub
				String eligible = "";
				Session session = sessionFactory.getCurrentSession();
				
				
				String sql = "select result from courseenrolleduser where logindetails = "+ userID+" and status = 'N'";
				Query query = session.createSQLQuery(sql);
				List list = query.list();
				new ZLogger("isTraineeEligible","list.size() "+ list.size(), "TraineeDAOImpl.java");
				if(list.size() > 0){
					String result  = (String) list.get(0);
					if(result != null && result.toUpperCase().equals("P")){
						eligible = "Y";
					}
					//eligible = (String) list.get(0);
				}
				
				/*if(isOnline != null && isOnline.equals("ONLINE")){
					String sql = "select A.logindetails  from assessmentevaluationtrainee A"
							+ " where A.totalscore >= (select AA.eligibility from assessmenteligibility AA where AA.coursenameid=A.coursenameid) and A.logindetails = " + userID;
					Query query = session.createSQLQuery(sql);
					List list = query.list();
					new ZLogger("isTraineeEligible","list.size() "+ list.size(), "TraineeDAOImpl.java");
					if(list.size() > 0){
						eligible = "Y";
						//eligible = (String) list.get(0);
					}
				}else{
					String sql = "select result from courseenrolleduser where logindetails = "+ userID+" and status = 'N'";
					Query query = session.createSQLQuery(sql);
					List list = query.list();
					new ZLogger("isTraineeEligible","list.size() "+ list.size(), "TraineeDAOImpl.java");
					if(list.size() > 0){
						String result  = (String) list.get(0);
						if(result != null && result.toUpperCase().equals("P")){
							eligible = "Y";
						}
						//eligible = (String) list.get(0);
					}
				}*/
				
				return eligible;
	}
	
	@Override
	public CertificateInfo getCertificateID(int userID, 
			String certificateID) {
		// TODO Auto-generated method stub
		CertificateInfo certificateInfo = new CertificateInfo();
		try {
			Session session = sessionFactory.getCurrentSession();
			// Get Next Seq

			int maxIdSeq = 0;
			String whereCondition = "Where 1=1";
			if (certificateID != null && certificateID.length() > 5) {
				whereCondition = whereCondition + " AND A.certificateid = '"
						+ certificateID + "'";
				
			} else {
				String sqlSeq = "select coalesce(max(certificateseqno) + 1,1) from nomineetrainee";
				Query maxIDListSeq = session.createSQLQuery(sqlSeq);
				List list = maxIDListSeq.list();
				new ZLogger("getCertificateID","list.size() "+ list.size(), "TraineeDAOImpl.java");
				if (list.size() > 0) {
					maxIdSeq = (int) list.get(0);
					// eligible = (String) list.get(0);
				}
				whereCondition = "AND A.status='N'  AND D.id = "
						+ userID;
			}

			// max SeqNo
			String sql = "Select B.trainingstartdate,B.schedulecode,"
					+ " A.id  as nomineeId , cast(D.firstname || ' '|| D.middlename ||' '|| D.lastname as varchar (50)) ,A.issueDate,A.certificateid  "
					+" , concat(E.trainingcentername , ' ' , s.statename, ' ' , ds.districtname) as address ,B.trainingEndDate as trainingStart,designationName||'-'||trainingTypeName||''||(CASE WHEN tPhase.trainingPhaseId =0 THEN '' ELSE '-'||tPhase.trainingPhaseName END) "
					+ " from nomineetrainee A "
					+ " inner join trainingcalendar B on(A.trainingcalendarid=B.trainingcalendarid) "
					//+"	inner join trainingcalendarmapping tcm on(tcm.batchcode=B.batchcode) "
					//+ " inner join modulemaster C on(tcm.subjectId=C.moduleid) "
					+ " inner join personalinformationtrainee D on(A.logindetails=D.logindetails) "
					+ " inner join personalinformationtraininginstitute E on(cast(B.traininginstitute as numeric)=E.id)  "
					+ " inner join statemaster as s on s.stateid = cast( E.correspondencestate as int) "
					+ " inner join districtmaster as ds on ds.districtid = cast( E.correspondencedistrict as int) inner join designation desg on (desg.designationid=cast(B.designation as numeric))"
       +" inner join trainingType ttype on (ttype.trainingTypeid=cast(B.trainingType as numeric))"
           +" inner join trainingPhase tphase on (tphase.trainingPhaseid=cast(B.trainingPhase as numeric)) "
					+ " " + whereCondition;
			int nomineeTraineeUserID = 0;
			String schedulecode = "";
			
			Query query = session.createSQLQuery(sql);
			List<Object[]> records = (List<Object[]>) query.list();
			System.out.println("Record Size == " + records.size());
			
			try {
				if (records.size() > 0) {
					Object[] obj = records.get(0);
					
					schedulecode = obj[1] == null ? "" : obj[1].toString();
					certificateInfo.setTrainingStart(obj[0] == null ? ""
							: obj[0].toString());
					nomineeTraineeUserID = (int) obj[2];
					certificateInfo.setName(obj[3] == null ? "" : obj[3]
							.toString());
					certificateInfo.setIssueDate(obj[4] == null ? "" : obj[4]
							.toString());
					certificateInfo.setCertificateID(obj[5] == null ? ""
							: obj[5].toString());
					
					certificateInfo.setTrainingAddress(obj[6] == null ? ""
							: obj[6].toString());
					certificateInfo.setTrainingEnd((String)obj[7]);
					certificateInfo.setProgramme((String)obj[8]);
				/*	certificateInfo.setTrainingPartnerName(obj[7] == null ? ""
							: obj[6].toString());*/
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (certificateInfo != null
					/*&& certificateInfo.getCertificateID() == null*/
					|| certificateInfo.getCertificateID().trim().length() <= 5
					|| certificateInfo.getCertificateID().toUpperCase()
							.equals("NULL")) {
				if (schedulecode != null && schedulecode.length() > 0) {
					certificateID = schedulecode
							+ StringUtils.leftPad(String.valueOf(maxIdSeq), 6,
									"0") + "17";
				}
				System.out.println(" nomineeTraineeUserID "+nomineeTraineeUserID);
				certificateInfo.setCertificateID(certificateID);
				if (nomineeTraineeUserID > 0) {
					NomineeTrainee nomineeTrainee = (NomineeTrainee) session
							.load(NomineeTrainee.class,
									nomineeTraineeUserID);
					nomineeTrainee.setCertificateID(certificateID);
					nomineeTrainee.setCertificateSeqNo(maxIdSeq);
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date dateobj = new Date();
					nomineeTrainee.setIssueDate(df.format(dateobj));
					nomineeTrainee.setCertificateStatus("Y");
					certificateInfo.setIssueDate(df.format(dateobj));
					session.update(nomineeTrainee);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return certificateInfo;
	}
	
	
	public List<String> courseTypes(){
		Session session = sessionFactory.getCurrentSession();
		List<String> courseTList = new ArrayList<String>();
		Query query = session.createQuery("from CourseType");
		List<CourseType> courseTypeList = query.list();
		for(CourseType c : courseTypeList){
			courseTList.add(c.getCourseType());
		}
		return courseTList;
	}
	
	
	
	@Override
	public List<CourseType> courseTypeList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CourseType");
		List<CourseType> courseTypeList = query.list();
		return courseTypeList;
	}
	
	@Override
	public String isAadharExist(CheckAadhar checkAadhar) {
		// TODO Auto-generated method stub
		String status = "";
		Session session = sessionFactory.getCurrentSession();
		String sql = "select aadharnumber from personalinformationtrainee where aadharnumber = '" + (checkAadhar.getAadharNo() == null ? "" : checkAadhar.getAadharNo().trim()) + "'";
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		if(list.size() > 0){
			System.out.println(list.size());
			new ZLogger("isAadharExist","list.size() "+ list.size(), "TraineeDAOImpl.java");
			status = (String) list.get(0);
		}
		return status;
	}
	
	@Override
	public List<State> stateList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from State where status = 'A'");
		List<State> stateList = query.list();
		return stateList;
	}
	
	//getCourseDetails
	
	@Override
	public  List getCourseDetails(String data){
		System.out.println("data "+data);
		String[] totalConnected = data.toString().split("-");
		String courseName,modeOfTraining,trainingPatrtner,trainingDate = null,trainingCenterState,trainingCenterDistrict , courseType;
		
		courseName = totalConnected[0];
		if(courseName.equals("0")){		courseName = "%";	}
		modeOfTraining = totalConnected[1];
		if(modeOfTraining.equals("0")){		modeOfTraining = "%";	}
		trainingPatrtner = totalConnected[2];
		if(trainingPatrtner.equals("0")){		trainingPatrtner = "%";	}
		//String[] trainingDate1 = totalConnected[3];
		trainingCenterState = totalConnected[3];
		if(trainingCenterState.equals("0")){		trainingCenterState = "%";	}
		trainingCenterDistrict = totalConnected[4];
		if(trainingCenterDistrict.equals("0")){		trainingCenterDistrict = "%";	}
		courseType = totalConnected[5];
		if(courseType.equals("0")){		courseType = "%";	}
		Session session = sessionFactory.getCurrentSession();
		String sql ="select tc.trainingcalendarid , concat(pitp.trainingpartnerpermanentline1 , ' ' , pitp.trainingpartnerpermanentline2 , ' ' , s.statename , ' ' , d.districtname , ' ' , c.cityname) as address, "+
				" concat(tc.trainingdate , ' / ' , tc.trainingtime) as schedule , "+
				" concat(pitp.firstname , ' ' , pitp.middlename , ' ' , pitp.lastname ) ,concat( pitp.trainingpartnerpermanentmobile , ' / ' , pitp.trainingpartnerpermanentemail)  as contact, "+
				" tc.seatCapacity ,(CAST(CAST (tc.seatCapacity AS NUMERIC(19,4)) AS INT) - ( select count(1) from courseenrolleduser where trainingcalendarid = tc.trainingcalendarid)) "+
				" ,cn.courseCode , tc.trainingDate, tc.batchCode, cn.courseduration  "+
				" from trainingcalendar as tc "+
				" inner join coursename as cn on cn.coursenameid = tc.coursename "+
				" inner join coursetype as ct on ct.coursetypeid = tc.coursetype "+
				" inner join managetrainingpartner as mtp on mtp.managetrainingpartnerid = tc.trainingpartner "+
				" inner join personalinformationtrainingpartner as pitp on mtp.managetrainingpartnerid = pitp.trainingpartnername "+
				" inner join state as s on s.stateid = pitp.trainingpartnerpermanentstate "+
				" inner join city as c on c.cityid = pitp.trainingpartnerpermanentcity "+
				" inner join district as d on d.districtid = pitp.trainingpartnerpermanentdistrict "+
				" and tc.trainingcenter = pitp.personalinformationtrainingpartnerid "+
				" where CAST(tc.coursename AS varchar(10)) like '"+courseName+"' "+
				" and CAST(tc.courseType AS varchar(10)) like  '"+courseType+"' "+
				" and CAST(tc.trainingpartner AS varchar(10)) like '"+trainingPatrtner+"'  "+
				" and CAST(s.stateid AS varchar(10)) like '"+trainingCenterState+"' "+
				" and CAST(d.districtid AS varchar(10)) like '"+trainingCenterDistrict+"' "+
				" and  to_timestamp(COALESCE(tc.trainingdate, '19900101010101'),'DD-MM-YYYY') > CURRENT_TIMESTAMP - INTERVAL '1 days' and  (CAST(CAST (tc.seatCapacity AS NUMERIC(19,4)) AS INT) - ( select count(1) from courseenrolleduser where trainingcalendarid = tc.trainingcalendarid) > 0)";
	
		Query query = session.createSQLQuery(sql);
		List courseTypeList = query.list();
		return courseTypeList;
	}
	
	
	//getAttendanceDeatilsByID
	@Override
	public  List getAttendanceDeatilsByID(String data){
		System.out.println("data "+data);
		Session session = sessionFactory.getCurrentSession();
		String sql ="select   distinct login.loginid as traineeName , batchcode, trainingstartdate  trainingStartDate , trainingenddate trainingEndDate ,att.attendancedate  from nomineetrainee nt  inner join trainingcalendar tc on (nt.trainingcalendarid = tc.trainingcalendarid) inner join logindetails login on  (nt.logindetails =  login.id) inner join subjectmapping sm on (tc.scheduleCode=sm.schedulecode) inner join traineedailyattendance att on (att.rollnumber = nt.rollno)where rollno='"+data+"'";
		Query query = session.createSQLQuery(sql);
		List courseTypeList = query.list();
		return courseTypeList;
	}
	
	
	//savePaymentStatus
	
	@Override
	public  String savePaymentStatus(String data){
		System.out.println("data "+data);
		String[] totalConnected = data.split("-");
		String paymentstatus , courseenrolleduserid ;
		paymentstatus= (totalConnected[0].split("="))[1];
		
		courseenrolleduserid = (totalConnected[1].split("="))[1];
		new ZLogger("trainingCenterStateList", " paymentstatus " + paymentstatus + " courseenrolleduserid  "+courseenrolleduserid,  "TraineeDAOImpl.java");
		Session session = sessionFactory.getCurrentSession();
		CourseEnrolledUser courseEnrolledUser = (CourseEnrolledUser) session.load(CourseEnrolledUser.class, Integer.parseInt(courseenrolleduserid));
		courseEnrolledUser.setPaymentstatus(paymentstatus);
		
		session.update(courseEnrolledUser);
		
		return "updated Sucessfully";
	}
	
	//addPersonalInfoTrainee
	
	@Override
	public  String addPersonalInfoTrainee(PersonalInformationTrainee p){

		PasswordGenerator passwordGenerator = new PasswordGenerator(6);
		char[] pass = passwordGenerator.get();
		String passwordString = String.valueOf(pass);
		
		Session session = sessionFactory.getCurrentSession();
		String encryprPassword = null;
		try{
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			encryprPassword = encryptionPasswordANDVerification.encryptPass(passwordString);
			
		}catch(NoSuchAlgorithmException e){
			System.out.println( " no such algo exception error catch ");
		}
		
		String nextSequenceUserID = pageLoadService.getNextCombinationId("TE", "personalinformationtrainee" , "000000");
		LoginDetails loginDetails = new LoginDetails();
		loginDetails.setLoginId(nextSequenceUserID);
		loginDetails.setPassword(passwordString);
		loginDetails.setEncrypted_Password(encryprPassword);
		loginDetails.setStatus("I");
		loginDetails.setProfileId(3);
		p.setStatus("A");
		p.setLoginDetails(loginDetails);
		session.save(p);
		return passwordString+"&"+nextSequenceUserID;
	}	
	
	
	
	@Override
	public  String updatePersonalInfoTrainee(PersonalInformationTrainee p){
		int id =  p.getId();
		new ZLogger("updatePersonalInfoTrainee", "userId"+id, "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		PersonalInformationTrainee personalInformationTrainee = (PersonalInformationTrainee) session.load(PersonalInformationTrainee.class, id);
		personalInformationTrainee.setDesignation(p.getDesignation());
		personalInformationTrainee.setTitle(p.getTitle());
		personalInformationTrainee.setAadharNumber(p.getAadharNumber());
		personalInformationTrainee.setEmpID(p.getEmpID());
		personalInformationTrainee.setDob(p.getDob());
		personalInformationTrainee.setGender(p.getGender());
		personalInformationTrainee.setFirstName(p.getFirstName());
		personalInformationTrainee.setFatherName(p.getFatherName());
		personalInformationTrainee.setLastName(p.getLastName());
		personalInformationTrainee.setMiddleName(p.getMiddleName());
		personalInformationTrainee.setEmail(p.getEmail());
		personalInformationTrainee.setMobile(p.getMobile());
		personalInformationTrainee.setCorrespondenceAddress1(p.getCorrespondenceAddress1());
		personalInformationTrainee.setCorrespondenceAddress2(p.getCorrespondenceAddress2());
		personalInformationTrainee.setCorrespondenceState(p.getCorrespondenceState());
		personalInformationTrainee.setCorrespondenceDistrict(p.getCorrespondenceDistrict());
		personalInformationTrainee.setCorrespondenceCity(p.getCorrespondenceCity());
		personalInformationTrainee.setCorrespondencePincode(p.getCorrespondencePincode());
		personalInformationTrainee.setResidentialLine1(p.getResidentialLine1());
		personalInformationTrainee.setResidentialLine2(p.getResidentialLine2());
		personalInformationTrainee.setResState(p.getResState());
		personalInformationTrainee.setResidentialDistrict(p.getResidentialDistrict());
		personalInformationTrainee.setResCity(p.getResCity());
		personalInformationTrainee.setResPincode(p.getResPincode());
		personalInformationTrainee.setDojService(p.getDojService());
		//personalInformationTrainee.setDojService(p.getDojService());
		//personalInformationTrainee.setEmpDate(p.getEmpDate());
		personalInformationTrainee.setDojPost(p.getDojPost());
		personalInformationTrainee.setDateRetirement(p.getDateRetirement());
		//personalInformationTrainee.setPostDistrict(p.getPostDistrict());
		
		System.out.println(" status "+p.getStatus());
		/*if(p.getStatus() != null){
			System.out.println(" loginId"+p.getLogId() );
			int loginId = Integer.parseInt(p.getLogId());
			String sql = "update logindetails set status ='"+p.getStatus()+"'  where id =("+loginId+")";
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		}*/
		session.update(personalInformationTrainee);
		return "updated";
	}	
	
	
	
	
	
	
	
	
	@Override
	public  String addPersonalInfoTrainer(PersonalInformationTrainer p,String pid){

		new ZLogger("addPersonalInfoTrainer", "userId"+pid, "TraineeDAOImpl.java");

		
		PasswordGenerator passwordGenerator = new PasswordGenerator(6);
		char[] pass = passwordGenerator.get();
		String passwordString = String.valueOf(pass);
	Session session = sessionFactory.getCurrentSession();
		if(p.getRadioTrainingInstitute().equals("N")){
			p.setRadioTrainingInstitute("N");
			p.setAssociatedWithAnyTrainingInstitute(null);
			p.setOtherTrainingInstitute(null);
		}
		else{
			p.setRadioTrainingInstitute("Y");
		}
	if(pid==null){
		p.setCreatedBy(4);
	}
	else{
		p.setCreatedBy(2);
	}
		String encryprPassword = null;
		try{
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			encryprPassword = encryptionPasswordANDVerification.encryptPass(passwordString);
			
		}catch(NoSuchAlgorithmException e){
			System.out.println( " no such algo exception error catch ");
		}
		
		String nextSequenceUserID = pageLoadService.getNextCombinationId("TR", "personalinformationtrainer" , "000000");
		LoginDetails loginDetails = new LoginDetails();
		loginDetails.setStatus("I");
		loginDetails.setLoginId(nextSequenceUserID);
		loginDetails.setPassword(passwordString);
		loginDetails.setEncrypted_Password(encryprPassword);
		loginDetails.setProfileId(4);
		p.setStatus("A");
		p.setLoginDetails(loginDetails);
		MappingMasterTrainer mmt;
		try{
			
			String s=p.getTrainingState();
			String a[]=s.split(",");
			for(int i=0;i<a.length;i++)
			{
				mmt=new MappingMasterTrainer();
				mmt.setTrainerId(p);
				mmt.setFirstName(p.getFirstName());
				mmt.setState(a[i]);
				session.save(mmt);
				
			}
			
			String subs=p.getKnownSubjects();
			String arr[]=subs.split(",");
			System.out.println("traier   "+p);
			TrainerSubjectMapping tsm;
			
			for(int i=0;i<arr.length;i++)
			{
				tsm=new TrainerSubjectMapping();
				
				tsm.setTrainerId(p);
				tsm.setSubjectId(Integer.parseInt(arr[i]));
				session.save(tsm);
				
			}
			p.setKnownSubjects(p.getKnownSubjects().replaceAll(",", "|"));
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		session.save(p);
    
		
		return passwordString+"&"+nextSequenceUserID;
	}	
	
	
	@Override
	public  String updatePersonalInfoTrainer(PersonalInformationTrainer p){
		int id =  p.getId();
		new ZLogger("updatePersonalInfoTrainer", "userId"+id, "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		PersonalInformationTrainer personalInformationTrainer = (PersonalInformationTrainer) session.load(PersonalInformationTrainer.class, id);
		
		personalInformationTrainer.setTitle(p.getTitle());
		personalInformationTrainer.setTrainingState(p.getTrainingState());
		personalInformationTrainer.setAadharNumber(p.getAadharNumber());
		personalInformationTrainer.setEmpID(p.getEmpID());
		personalInformationTrainer.setDob(p.getDob());
		personalInformationTrainer.setGender(p.getGender());
		personalInformationTrainer.setFirstName(p.getFirstName());
		personalInformationTrainer.setFatherName(p.getFatherName());
		personalInformationTrainer.setLastName(p.getLastName());
		personalInformationTrainer.setMiddleName(p.getMiddleName());
		personalInformationTrainer.setEmail(p.getEmail());
		personalInformationTrainer.setMobile(p.getMobile());
		personalInformationTrainer.setCorrespondenceAddress1(p.getCorrespondenceAddress1());
		personalInformationTrainer.setCorrespondenceAddress2(p.getCorrespondenceAddress2());
		personalInformationTrainer.setCorrespondenceState(p.getCorrespondenceState());
		personalInformationTrainer.setCorrespondenceDistrict(p.getCorrespondenceDistrict());
		personalInformationTrainer.setCorrespondenceCity(p.getCorrespondenceCity());
		personalInformationTrainer.setCorrespondencePincode(p.getCorrespondencePincode());
		personalInformationTrainer.setResidentialLine1(p.getResidentialLine1());
		personalInformationTrainer.setResidentialLine2(p.getResidentialLine2());
		personalInformationTrainer.setResState(p.getResState());
		personalInformationTrainer.setResidentialDistrict(p.getResidentialDistrict());
		personalInformationTrainer.setResCity(p.getResCity());
		personalInformationTrainer.setResPincode(p.getResPincode());
		personalInformationTrainer.setLanguages(p.getLanguages());
		personalInformationTrainer.setExpInMonth(p.getExpInMonth());
		personalInformationTrainer.setExpInYear(p.getExpInYear());
		personalInformationTrainer.setRadioTrainingInstitute(p.getRadioTrainingInstitute());
		personalInformationTrainer.setAssociatedWithAnyTrainingInstitute(p.getAssociatedWithAnyTrainingInstitute());
		personalInformationTrainer.setOtherTrainingInstitute(p.getOtherTrainingInstitute());
		personalInformationTrainer.setFssai_tot(p.isFssai_tot());
		personalInformationTrainer.setGraduation(p.getGraduation());
		personalInformationTrainer.setPostGraduation(p.getPostGraduation());
		personalInformationTrainer.setTotalExp(p.getTotalExp());
		personalInformationTrainer.setFoodSafetyAct(p.isFoodSafetyAct());
		personalInformationTrainer.setPOF(p.isPOF());
		personalInformationTrainer.setNovelDev(p.isNovelDev());
		personalInformationTrainer.setFoodNutrition(p.isFoodNutrition());
		personalInformationTrainer.setFunctional(p.isFunctional());
		personalInformationTrainer.setRiskAss(p.isRiskAss());
		personalInformationTrainer.setFoodLab(p.isFoodLab());
		personalInformationTrainer.setLabelling(p.isLabelling());
		personalInformationTrainer.setCaseStudy(p.isCaseStudy());
		personalInformationTrainer.setGlobalPerspective(p.isGlobalPerspective());
		personalInformationTrainer.setOtherExp(p.isOtherExp());
		
		System.out.println(" status "+p.getStatus());
		/*if(p.getStatus() != null){
			System.out.println(" loginId "+p.getLogId() );
			int loginId = Integer.parseInt(p.getLogId());
			String sql = "update logindetails set status ='"+p.getStatus()+"'  where id =("+loginId+")";
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();}*/
		System.out.println(p.getKnownSubjects());
		String subs=p.getKnownSubjects();
		String arr[]=subs.split(",");
		personalInformationTrainer.setKnownSubjects(p.getKnownSubjects().replaceAll(",", "|"));

						p = (PersonalInformationTrainer) session.load(PersonalInformationTrainer.class,p.getId());
						Query query = session.createSQLQuery("delete from  MappingMasterTrainer where personalinformationtrainer ='"+p.getId()+"'");
						//query.setParameter("state", p.getTrainingState());
						query.executeUpdate();
						
						MappingMasterTrainer mmt;
						try{
							
							String s=p.getTrainingState();
							String a[]=s.split(",");
							for(int i=0;i<a.length;i++)
							{
								mmt=new MappingMasterTrainer();
								mmt.setTrainerId(p);
								mmt.setFirstName(p.getFirstName());
								mmt.setState(a[i]);
								session.save(mmt);
								
							}
							
							query = session.createSQLQuery("delete from  TrainerSubjectMapping where personalinformationtrainer ='"+p.getId()+"'");
							query.executeUpdate();
							
							TrainerSubjectMapping tsm;
							
							for(int i=0;i<arr.length;i++)
							{
								tsm=new TrainerSubjectMapping();
								
								tsm.setTrainerId(p);
								tsm.setSubjectId(Integer.parseInt(arr[i]));
								session.save(tsm);
								
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						//session.save(p);
				    
		session.update(personalInformationTrainer);
		return "updated";
	}	
	
	
	
	@Override
	public  String updatePersonalInfoTrainingInstitute(PersonalInformationTrainingInstitute p){

		int id =  p.getId();
		new ZLogger("updatePersonalInfoTrainingInstitute", "userId"+id, "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		PersonalInformationTrainingInstitute personalInformationTrainingInstitute = (PersonalInformationTrainingInstitute) session.load(PersonalInformationTrainingInstitute.class, id);
		personalInformationTrainingInstitute.setTrainingCenterName(p.getTrainingCenterName());
		personalInformationTrainingInstitute.setTitle(p.getTitle());
		personalInformationTrainingInstitute.setFirstName(p.getFirstName());
		personalInformationTrainingInstitute.setLastName(p.getLastName());
		personalInformationTrainingInstitute.setMiddleName(p.getMiddleName());
		personalInformationTrainingInstitute.setEmail(p.getEmail());
		personalInformationTrainingInstitute.setMobile(p.getMobile());
		personalInformationTrainingInstitute.setCorrespondenceAddress1(p.getCorrespondenceAddress1());
		personalInformationTrainingInstitute.setCorrespondenceAddress2(p.getCorrespondenceAddress2());
		personalInformationTrainingInstitute.setCorrespondenceState(p.getCorrespondenceState());
		personalInformationTrainingInstitute.setCorrespondenceDistrict(p.getCorrespondenceDistrict());
		personalInformationTrainingInstitute.setCorrespondenceCity(p.getCorrespondenceCity());
		personalInformationTrainingInstitute.setCorrespondencePincode(p.getCorrespondencePincode());
		//personalInformationTrainingInstitute.setTrainingType(p.getTrainingType());
		//personalInformationTrainingInstitute.setSessWishToConduct(p.getSessWishToConduct());
		personalInformationTrainingInstitute.setSeatingCapacity(p.getSeatingCapacity());
		personalInformationTrainingInstitute.setAvailableTVProjector(p.isAvailableTVProjector());
		personalInformationTrainingInstitute.setAvailableInHouseTrainer(p.isAvailableInHouseTrainer());
		personalInformationTrainingInstitute.setNoOfInHouseTrainer(p.getNoOfInHouseTrainer());
		personalInformationTrainingInstitute.setNoOfPC(p.getNoOfPC());
		personalInformationTrainingInstitute.setAc(p.isAc());
		personalInformationTrainingInstitute.setLcd(p.isLcd());
		personalInformationTrainingInstitute.setLaptop(p.isLaptop());
		personalInformationTrainingInstitute.setProjector(p.isProjector());
		personalInformationTrainingInstitute.setPrinter(p.isPrinter());
		personalInformationTrainingInstitute.setPhotoCopier(p.isPhotoCopier());
		personalInformationTrainingInstitute.setWhiteBoard(p.isWhiteBoard());
		personalInformationTrainingInstitute.setPowerBackup(p.isPowerBackup());
		personalInformationTrainingInstitute.setTrTool(p.isTrTool());
		personalInformationTrainingInstitute.setInternetFacility(p.isInternetFacility());
		personalInformationTrainingInstitute.setLight(p.isLight());
		personalInformationTrainingInstitute.setSound(p.isSound());
		personalInformationTrainingInstitute.setFssaiLab(p.isFssaiLab());
		personalInformationTrainingInstitute.setLastYearTrainings(p.getLastYearTrainings());
		personalInformationTrainingInstitute.setLastYearParticipants(p.getLastYearParticipants());
		personalInformationTrainingInstitute.setConHall(p.isConHall());
		personalInformationTrainingInstitute.setNoOfHall(p.getNoOfHall());
		personalInformationTrainingInstitute.setPerHall(p.getPerHall());
		personalInformationTrainingInstitute.setHostelRooms(p.getHostelRooms());
		personalInformationTrainingInstitute.setMess(p.isMess());
		personalInformationTrainingInstitute.setPowerHostel(p.isPowerHostel());
		personalInformationTrainingInstitute.setHostel(p.isHostel());
		//personalInformationTrainingInstitute.setNoOfYearExp(p.getNoOfYearExp());
		//personalInformationTrainingInstitute.setSessWishToConduct(p.getSessWishToConduct());
		/*if(p.getStatus() != null){
			System.out.println(" loginId "+p.getLogId() );
			int loginId = Integer.parseInt(p.getLogId());
			String sql = "update logindetails set status ='"+p.getStatus()+"'  where id =("+loginId+")";
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
		}*/
		session.update(personalInformationTrainingInstitute);
		return "updated";
	}	
	

	
	@Override
	public  String addPersonalInfoTrainingInstitute(PersonalInformationTrainingInstitute p,int pid){
		
		new ZLogger("addPersonalInfoTrainingInstitute", "userId"+pid, "TraineeDAOImpl.java");

		PasswordGenerator passwordGenerator = new PasswordGenerator(6);
		char[] pass = passwordGenerator.get();
		String passwordString = String.valueOf(pass);
		
		Session session = sessionFactory.getCurrentSession();
		LoginDetails loginDetails = new LoginDetails();
		if(pid==1){
			p.setCreatedBy(1);
			loginDetails.setStatus("A");
		}
		else{
			p.setCreatedBy(2);
			loginDetails.setStatus("I");
		}
		String encryprPassword = null;
		try{
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			encryprPassword = encryptionPasswordANDVerification.encryptPass(passwordString);
			
		}catch(NoSuchAlgorithmException e){
			System.out.println( " no such algo exception error catch ");
		}
		
	//System.out.println(	p.getTpName() + " ");
		//String nextSequenceUserID = pageLoadService.getNextCombinationId("TCTP"+p.getTpName(), "personalinformationtrainingInstitute" , "000000");
		String nextSequenceUserID = pageLoadService.getNextCombinationId("TC", "personalinformationtrainingInstitute" , "000000");
		
		
		loginDetails.setLoginId(nextSequenceUserID);
		loginDetails.setPassword(passwordString);
		loginDetails.setEncrypted_Password(encryprPassword);
		//loginDetails.setStatus("I");
		loginDetails.setProfileId(5);
		p.setLoginDetails(loginDetails);
		p.setStatus("A");
		session.save(p);
		return passwordString+"&"+nextSequenceUserID;
	}

	
	//@Override
	public List<MyTrainingForm> listMyTraining() {
		// TODO Auto-generated method stub
		System.out.println("inside listMyTrainingForm");
		new ZLogger("listMyTraining", "", "TraineeDAOImpl.java");

		MyTrainingForm bean = new MyTrainingForm();
		List<MyTrainingForm> list = new ArrayList<MyTrainingForm>();
		Session session = this.sessionFactory.getCurrentSession();
		List<Object[]> lst = session.createSQLQuery("select  cast('1' as int) as id,cast('Refresher' as varchar(20)) as trainingType , cast('Foundation' as varchar(20)) as trainingPhase ,cast('26-03-2017' as varchar(40)) as startDate,cast('26-06-2017' as varchar(40)) as endDate,cast('online' as varchar(20)) as trainingCenter,cast('Completed' as varchar(20)) as status").list();
		for (Object[] li : lst ) {
			bean.setId((int) li[0]);
			bean.setTrainingType((String) li[1]);
			bean.setTrainingPhase((String) li[2]);
			bean.setStartDate((String) li[3]);
			bean.setEndDate(((String)li[4]));
			bean.setTrainingCenter(((String)li[5]));
			bean.setStatus(((String)li[6]));
			list.add(bean);
		}
System.out.println("list "+list);
		return list;
	}
	

	
	//@Override
		public GetScoreCardForm listGetScoreCard(int id) {
			// TODO Auto-generated method stub
			System.out.println("inside listGetScoreCardForm");
			new ZLogger("listGetScoreCard", "id"+id, "TraineeDAOImpl.java");

			Session session = sessionFactory.getCurrentSession();
			GetScoreCardForm bean = new GetScoreCardForm();
			/*List<GetScoreCardForm> list = new ArrayList<GetScoreCardForm>();*/
			String sql = "select  max(nomineeid) from assessmentevaluationtrainee GROUP BY logindetails='"+ id +"' ";
			Query query = session.createSQLQuery(sql);
			List list = query.list();
			if(list.size() >0){
			int nomineeid=(int) list.get(0);
			List<Object[]> lst = session.createSQLQuery("select * from assessmentevaluationtrainee where nomineeid='"+ nomineeid +"' ").list();
			
			if(lst.size() >0){
				 Object[] obj=	lst.get(0);
				 bean.setScore((Double)obj[7]);
				
				}
	
			}
	//System.out.println("list "+list);
			return bean;
		}

		
		
		//@Override
		public List<CertificateForm> listCertificate(int loginId) {
			// TODO Auto-generated method stub
			System.out.println("inside listCertificateForm");
			new ZLogger("listCertificate", "id"+loginId, "TraineeDAOImpl.java");

			List<CertificateForm> list = new ArrayList<CertificateForm>();
			Session session = this.sessionFactory.getCurrentSession();
			StringBuffer sqlQuery  = new StringBuffer();
			 sqlQuery.append("select tt.trainingtypeName, case when coalesce(nt.result , 'F') = '' then cast('Pending' as varchar(20)) else cast('Completed' as varchar(20)) end as status,  case when nt.result = 'P' then cast('YES' as varchar(3)) else cast('NO' as varchar(2)) end as cerificateAvailable  , pit.id from nomineetrainee nt inner join trainingCalendar tc on (nt.trainingcalendarid = tc.trainingcalendarid) inner join trainingType tt on (cast(tc.trainingType as numeric) = tt.trainingTypeId)  ");
			 sqlQuery.append("left join personalinformationtrainee pit on (nt.logindetails = pit.logindetails) where nt.logindetails =  '"+loginId+"' and nt.status='N'");
			List<Object[]> lst = session.createSQLQuery(sqlQuery.toString()).list();
			for (Object[] li : lst ) {
				CertificateForm bean = new CertificateForm();
				bean.setTrainingType((String) li[0]);
				bean.setCompletionStatus((String) li[1]);
				bean.setCertificateAvailable((String) li[2]);
				bean.setId((int) li[3]);	
		
				list.add(bean);
			}
			return list;
		}
				

			

	@Override
	public PersonalInformationTrainer fullDetailtrainer(int loginId) {
		// TODO Auto-generated method stub
		System.out.println("LogintrainerDAOImpl full detail process start ");
		new ZLogger("fullDetailtrainer", "id"+loginId, "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		Query query = session.createQuery("from PersonalInformationTrainer where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainer> list = query.list();
		PersonalInformationTrainer personalInformationTrainer = null;
		for(PersonalInformationTrainer personalInformationTrainer1: list){
			personalInformationTrainer=personalInformationTrainer1;
		}
		return personalInformationTrainer;

	}
	
	
	@Override
	public PersonalInformationTrainingInstitute FullDetailTrainingInstitude(int loginId) {
		// TODO Auto-generated method stub
		System.out.println("LogintrainerDAOImpl full detail process start ");
		new ZLogger("FullDetailTrainingInstitude", "id"+loginId, "TraineeDAOImpl.java");

		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		System.out.println("search " + loginId);
		Query query = session.createQuery("from PersonalInformationTrainingInstitute where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainingInstitute> list = query.list();
		PersonalInformationTrainingInstitute personalInformationTrainingInstitute = null;
		for(PersonalInformationTrainingInstitute personalInformationTrainingInstitute2: list){
			personalInformationTrainingInstitute=personalInformationTrainingInstitute2;
		}
		return personalInformationTrainingInstitute;

	}
	//Online training-------

	@Override
	public UnitMaster listTrainingTopic(int id) {
		// TODO Auto-generated method stub
		System.out.println("inside listTrainingTopic");
		new ZLogger("listTrainingTopic", "id"+id, "TraineeDAOImpl.java");

		Session session = this.sessionFactory.getCurrentSession();
		UnitMaster bean=new UnitMaster();
		List<Object[]> list = session.createSQLQuery("select um.unitname,mst.contentname,mst.contenttype,mst.contentlink from nomineetrainee nt inner join trainingschedule ts on (nt.trainingscheduleid = ts.trainingscheduleid) left join modulemaster mst on (ts.moduleid = mst.moduleid)left join unitmaster um on (um.unitid = mst.unitid)where nt.logindetails='"+ id +"'").list();
		
		if(list.size() > 0){
			 Object[] obj=	list.get(0);
			 bean.setUnitName((String)obj[0]);
			 bean.setContentName((String)obj[1]);
			 bean.setContentType((String)obj[2]);
			 bean.setContentLink((String)obj[3]);
			
			}
		
		return bean; 
		
	}
	
		//@Override
		public OnlineTrainingForm listOnlineTraining(int id) {
			// TODO Auto-generated method stub
			System.out.println("inside listOnlineTrainingForm");
			new ZLogger("listOnlineTraining", "id"+id, "TraineeDAOImpl.java");
			OnlineTrainingForm bean = new OnlineTrainingForm();
		//	List<OnlineTrainingForm> list = new ArrayList<OnlineTrainingForm>();
			Session session = this.sessionFactory.getCurrentSession();
			/*List<Object[]> lst = session.createSQLQuery("select trainingtype , trainingphase ,mm.modulename,  trainingstartdate , trainingenddate  from nomineetrainee nt  left join trainingschedule ts on  (nt.trainingscheduleid = ts.trainingscheduleid) left join modulemaster mm on (mm.moduleid = ts.moduleid) where nt.logindetails ='"+ id +"'").list();*/
			List<Object[]> lst = session.createSQLQuery("select tt.trainingtypeName , tp.trainingphaseName ,d.designationName,tc.trainingstartdate , tc.trainingenddate , s.subjectname from nomineetrainee nt  left join trainingcalendar tc on  (nt.trainingcalendarid = tc.trainingcalendarid)left join trainingtype tt on tt.trainingtypeId = cast(tc.trainingtype as numeric) left join trainingPhase tp on tp.trainingPhaseId= cast(tc.trainingPhase as numeric) left join designation d on d.designationId= cast(tc.designation as numeric)left join trainingschedule ts on (ts.schedulecode = tc.schedulecode) left join subjectmapping sm on (sm.schedulecode = ts.schedulecode) left join subjectmaster s on (s.subjectid = cast(sm.subject as numeric)) where nt.logindetails='"+ id +"'and nt.status='N'").list();
			if(lst.size() > 0){
			 Object[] obj=	lst.get(0);
			 bean.setTrainingType((String)obj[0]);
			 bean.setTrainingPhase((String)obj[1]);
			 bean.setDesignation((String)obj[2]);
			 bean.setTrainingstartdate((String)obj[3]);
			 bean.setTrainingenddate((String)obj[4]);
			 bean.setSubjectName((String)obj[5]);
			
			}
			    
		
			return  bean;
		}

			
	
		
		//traineeFeedback
		@Override
		public List<FotestFeedbackForm> listFotestFeedback() {
			System.out.println("inside listprintAdmitCard");
			FotestFeedbackForm bean = new FotestFeedbackForm();
			List<FotestFeedbackForm> resulList = new ArrayList<FotestFeedbackForm>();
			Session session = this.sessionFactory.getCurrentSession();
			List<Object[]> list = session.createSQLQuery("select cast('ICP-MS' as varchar(20)) as courseName , cast('02/05/2017' as varchar(20)) as trainingDate ,  cast('02:00 PM' as varchar(20)) as trainingTime , cast('Adlabs' as varchar(20)) as trainingLab    ").list();
			for (Object[] li : list ) {
				
				bean.setCourseName((String) li[0]);
				bean.setTrainingDate((String) li[1]);
				bean.setTrainingTime((String) li[2]);
				bean.setTrainingLab((String) li[3]);
				new ZLogger("traineeFeedback", "List:" + li, "TraineeDAOImpl.java");
				//logger.info("traineeFeedback List::" + li);
				resulList.add(bean);
			}
			return resulList;
		}
		// online training
		@Override
		public List<fotestonlineTrainingForm> listfotestonlineTraining() {
			// TODO Auto-generated method stub
			System.out.println("inside listfotestonlineTraining");
			fotestonlineTrainingForm bean = new fotestonlineTrainingForm();
			List<fotestonlineTrainingForm> resulList = new ArrayList<fotestonlineTrainingForm>();
			Session session = this.sessionFactory.getCurrentSession();
			List<Object[]> list = session.createSQLQuery("select cast('ICP-MS' as varchar(20)) as courseName , cast('02/05/2017' as varchar(20)) as trainingDate ,  cast('02:00 PM' as varchar(20)) as trainingTime , cast('Adlabs' as varchar(20)) as trainingLab    ").list();
			for (Object[] li : list ) {
				
				bean.setCourseName((String) li[0]);
				bean.setTrainingDate((String) li[1]);
				bean.setTrainingTime((String) li[2]);
				bean.setTrainingLab((String) li[3]);
				//logger.info("fotestonlineTrainingForm List::" + li);
				resulList.add(bean);
			}
			return resulList;
		}
		

		//certification
		
		
		
		@Override
		public List<FotestCertificationForm> listcertification() {
			// TODO Auto-generated method stub
			System.out.println("inside listprintAdmitCard");
			FotestCertificationForm bean = new FotestCertificationForm();
			List<FotestCertificationForm> resulList = new ArrayList<FotestCertificationForm>();
			Session session = this.sessionFactory.getCurrentSession();
			List<Object[]> list = session.createSQLQuery("select cast('ICP-MS' as varchar(20)) as courseName , cast('02/05/2017' as varchar(20)) as trainingDate ,  cast('02:00 PM' as varchar(20)) as trainingTime , cast('Adlabs' as varchar(20)) as trainingLab    ").list();
			for (Object[] li : list ) {
				
				bean.setCourseName((String) li[0]);
				bean.setTrainingDate((String) li[1]);
				bean.setTrainingTime((String) li[2]);
				bean.setTrainingLab((String) li[3]);
				resulList.add(bean);
			}
			return resulList;
		}

		@Override
		public List listsubjects(int id) {
			// TODO Auto-generated method stub
			Session session = this.sessionFactory.getCurrentSession();
			new ZLogger("listsubjects", "", "TraineeDAOImpl.java");

			
			/*Query query2  = session.createSQLQuery("select * from assessmentevaluationtrainee where logindetails='"+ id +"'");
			System.out.println("QUUUUUUUUUUUUUUUUU "+query2);*/
		/*	String sql = "select * from assessmentevaluationtrainee where logindetails='"+ id +"'";
			Query maxIDList = session.createSQLQuery(sql);
			List list1 = maxIDList.list();
			Query query=null;
			int a=0;
			if (list1.size() == 0){
				System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPP");
			query =session.createSQLQuery("  select  s.subjectname,case when coalesce(vr.marks , -1) = -1 then cast('Pending' as varchar(20)) else cast(vr.marks as varchar(20)) end as Marks from nomineetrainee nt inner join viewResult vr on vr.traineeId=nt.logindetails inner join trainingCalendar tc on tc.trainingCalendarId=nt.trainingCalendarId left join trainingtype tt on tt.trainingtypeId = cast(tc.trainingtype as numeric) left join trainingPhase tp on tp.trainingPhaseId= cast(tc.trainingPhase as numeric) left join designation d on d.designationId= cast(tc.designation as numeric) left join subjectmaster s on (s.subjectid = vr.subject) where nt.logindetails='"+ id +"'");
 a=1;
			}*/
			Query query=null;
				 query =session.createSQLQuery(" select distinct s.subjectname from nomineetrainee nt  left join trainingcalendar tc on  (nt.trainingcalendarid = tc.trainingcalendarid)left join trainingtype tt on tt.trainingtypeId = cast(tc.trainingtype as numeric) left join trainingPhase tp on tp.trainingPhaseId= cast(tc.trainingPhase as numeric) left join designation d on d.designationId= cast(tc.designation as numeric)left join trainingschedule ts on (ts.schedulecode = tc.schedulecode) left join subjectmapping sm on (sm.schedulecode = ts.schedulecode) left join subjectmaster s on (s.subjectid = cast(sm.subject as numeric)) where nt.logindetails='"+ id +"'and nt.status='N'");
				
			
			//Query query = 	session.createSQLQuery("select c.batchCode,c.designation,t.trainingTypeName,p.trainingPhaseName,c.trainingInstitute,c.trainerName,c.trainingStartDate from TrainingCalendar c inner join TrainingType t on cast(c.trainingType as numeric)=t.trainingTypeId  inner join TrainingPhase p on cast(c.trainingPhase as numeric)=p.trainingPhaseId order by trainingCalendarId ");
		
			List list = query.list();
			return list;
		}
		/*@Override
		public List listCalendar() {
			// TODO Auto-generated method stub
			Session session = this.sessionFactory.getCurrentSession();
			//Query query = 	session.createSQLQuery("select c.batchCode,c.designation,t.trainingTypeName,p.trainingPhaseName,c.trainingInstitute,c.trainerName,c.trainingStartDate from TrainingCalendar c inner join TrainingType t on cast(c.trainingType as numeric)=t.trainingTypeId  inner join TrainingPhase p on cast(c.trainingPhase as numeric)=p.trainingPhaseId order by trainingCalendarId ");
			Query query =session.createSQLQuery("select batchCode, (select designationName from designation where designationid=cast(designation as numeric)),(select trainingTypeName from trainingType where trainingTypeid=cast(trainingType as numeric)),scheduleCode,(select trainingCenterName from personalinformationtraininginstitute where id=cast(traininginstitute as numeric)), totalDays,trainingStartDate,trainingEndDate from trainingCalendar");
			
			List list = query.list();
			return list;
		}*/


		
		
		
@Override
public List listsubjects2(int id) {
	// TODO Auto-generated method stub
	new ZLogger("listsubjects2", "", "TraineeDAOImpl.java");
	Session session = this.sessionFactory.getCurrentSession();
	Query query=null;
	
	query =session.createSQLQuery("   select distinct s.subjectname,case when coalesce(vr.marks , -1) = -1 then cast('Pending' as varchar(20)) else cast(vr.marks as varchar(20)) end as Marks from nomineetrainee nt inner join viewResult vr on vr.rollno=nt.rollno inner join subjectmaster s on (s.subjectid = vr.subject) where nt.status='N' and nt.logindetails='"+ id +"'");


	
	//Query query = 	session.createSQLQuery("select c.batchCode,c.designation,t.trainingTypeName,p.trainingPhaseName,c.trainingInstitute,c.trainerName,c.trainingStartDate from TrainingCalendar c inner join TrainingType t on cast(c.trainingType as numeric)=t.trainingTypeId  inner join TrainingPhase p on cast(c.trainingPhase as numeric)=p.trainingPhaseId order by trainingCalendarId ");

	List list = query.list();
	return list;
}

@Override
public String listOfflineTraining(int userID) {
	// TODO Auto-generated method stub
	new ZLogger("listOfflineTraining", "", "TraineeDAOImpl.java");

			String eligible = "";
			Session session = sessionFactory.getCurrentSession();
			String sql = "select tc.trainingphase from nomineetrainee nt inner join trainingcalendar tc on tc.trainingcalendarid=nt.trainingcalendarid where nt.result='-1' and nt.logindetails='"+userID+"'";
			Query query = session.createSQLQuery(sql);
		    List list = query.list();
			String trainingPhase=(String) list.get(0);
		return trainingPhase;
}	
}	
		/*
		@Override
		public List<traineeFeedbackForm> listtraineeFeedback() {
			// TODO Auto-generated method stub
			System.out.println("inside listprintAdmitCard");
			traineeFeedbackForm bean = new traineeFeedbackForm();
			List<traineeFeedbackForm> resulList = new ArrayList<traineeFeedbackForm>();
			Session session = this.sessionFactory.getCurrentSession();
			List<Object[]> list = session.createSQLQuery("select cast('ICP-MS' as varchar(20)) as courseName , cast('02/05/2017' as varchar(20)) as trainingDate ,  cast('02:00 PM' as varchar(20)) as trainingTime , cast('Adlabs' as varchar(20)) as trainingLab    ").list();
			for (Object[] li : list ) {
				
				bean.setCourseName((String) li[0]);
				bean.setTrainingDate((String) li[1]);
				bean.setTrainingTime((String) li[2]);
				bean.setTrainingLab((String) li[3]);
				logger.info("traineeFeedback List::" + li);
				resulList.add(bean);
			}
			return resulList;
		}*/