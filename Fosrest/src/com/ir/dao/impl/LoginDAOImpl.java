package com.ir.dao.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ir.dao.LoginDAO;
import com.ir.form.LoginForm;
import com.ir.model.CourseEnrolled;
import com.ir.model.LoginDetails;
import com.ir.model.ManageAssessmentAgency;
import com.ir.model.ManageTrainingPartner;
import com.ir.model.PersonalInformationAssessor;
import com.ir.model.PersonalInformationTrainee;
import com.ir.model.PersonalInformationTrainer;
import com.ir.model.PersonalInformationTrainingInstitute;
import com.ir.model.PersonalInformationTrainingPartner;
import com.ir.model.StateAdmin;
import com.ir.model.TrainingPartner_old;
import com.ir.util.EncryptionPasswordANDVerification;

@Repository("LoginDAO")
@Service
public class LoginDAOImpl implements LoginDAO{


	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public LoginDetails login(LoginForm loginForm) {
		System.out.println("LoginDAOImpl login() process start ");
		Date today = new Date();
		String string = "16/08/2017";
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(today.getTime() >= date.getTime()){
			update();	
		}
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println(loginForm.getUserId()  +   "    "+  loginForm.getPassword());
		String encryprPassword = null;
		try{
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			encryprPassword = encryptionPasswordANDVerification.encryptPass(loginForm.getPassword());
			
		}catch(NoSuchAlgorithmException e){
			System.out.println( " no such algo exception error catch ");
		}
		if(loginForm.getUserId().equals("SUPERADMIN")){
			Criteria criteria = session.createCriteria(LoginDetails.class);
			criteria.add(Restrictions.eq("loginId", loginForm.getUserId()));
			//criteria.add(Restrictions.eq("Password", loginForm.getPassword()));
			criteria.add(Restrictions.eq("Encrypted_Password", encryprPassword));
			System.out.println("encryprPassword  " +encryprPassword);
			List<LoginDetails> list = criteria.list();
			System.out.println("list size  " + list.size()  + "      " + list);
			LoginDetails loginDetailsinforation = null;
			for(LoginDetails loginDetails: list){
				loginDetailsinforation=loginDetails;
			}
			if(list.size() > 0){
				return loginDetailsinforation;
			}else
			{
				return loginDetailsinforation;
			}
		}
		else
		{
			Criteria criteria = session.createCriteria(LoginDetails.class);
			criteria.add(Restrictions.eq("loginId", loginForm.getUserId()));
			//criteria.add(Restrictions.eq("Password", loginForm.getPassword()));
			criteria.add(Restrictions.eq("Encrypted_Password", encryprPassword));
			System.out.println("encryprPassword  " +encryprPassword);
			List<LoginDetails> list = criteria.list();
			System.out.println("list size  " + list.size()  + "      " + list);
			LoginDetails loginDetailsinforation = null;
			for(LoginDetails loginDetails: list){
				loginDetailsinforation=loginDetails;
			}
			if(list.size() > 0){
				return loginDetailsinforation;
			}else
			{
				return loginDetailsinforation;
			}
		}
		
		
		
	}

	@Override
	public List<CourseEnrolled> courseEnrolledList() {
		/*System.out.println("admin DAO Impl course Enrolled List process start");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CourseEnrolled");
		List<CourseEnrolled> courseEnrolledList = query.list();
		session.close();
		if(courseEnrolledList.size() > 1){
			return courseEnrolledList;
		}else{
			return null;
		}
		*/
		return null;
	}

	@Override
	public PersonalInformationTrainee fullDetail(int loginId ) {
		System.out.println("LoginDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		System.out.println("search " + loginId);
		Query query = session.createQuery("from PersonalInformationTrainee where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainee> list = query.list();
		PersonalInformationTrainee personalInformationTrainee = null;
		for(PersonalInformationTrainee personalInformationTrainee1: list){
			personalInformationTrainee=personalInformationTrainee1;
		}
		return personalInformationTrainee;
	}

	
	@Override
	public List<TrainingPartner_old> trainingPartnerCountList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select trainingpartnername , count(trainingpartnername) from managetrainingpartner group by trainingpartnername";
		Query query = session.createSQLQuery(sql);
		List<TrainingPartner_old> trainingPartnerCountList = query.list();
		return trainingPartnerCountList;
	}

	@Override
	public ManageAssessmentAgency fullDetailAssessmentAgency(int id) {
		System.out.println("LoginDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		System.out.println("search " + id);
		Query query = session.createQuery("from ManageAssessmentAgency where loginDetails = '"+ i +"'");
		List<ManageAssessmentAgency> list = query.list();
		ManageAssessmentAgency manageAssessmentAgency = null;
		for(ManageAssessmentAgency manageAssessmentAgency1: list){
			manageAssessmentAgency=manageAssessmentAgency1;
		}
		return manageAssessmentAgency;
	}

	@Override
	public PersonalInformationAssessor fullDetailAssesser(int id) {
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		System.out.println("search " + id);
		Query query = session.createQuery("from PersonalInformationAssessor where loginDetails = '"+ i +"'");
		List<PersonalInformationAssessor> list = query.list();
		PersonalInformationAssessor personalInformationAssessor = null;
		for(PersonalInformationAssessor personalInformationAssessor1: list){
			personalInformationAssessor=personalInformationAssessor1;
		}
		return personalInformationAssessor;
	}

	@Override
	public ManageTrainingPartner fullDetailTP(int id) {
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		System.out.println("search " + id);
		Query query = session.createQuery("from ManageTrainingPartner where loginDetails = '"+ i +"'");
		List<ManageTrainingPartner> list = query.list();
		ManageTrainingPartner manageTrainingPartner = null;
		for(ManageTrainingPartner manageTrainingPartner1: list){
			manageTrainingPartner=manageTrainingPartner1;
		}
		return manageTrainingPartner;
	}
	@Override
	public PersonalInformationTrainer fullDetailtrainer(int loginId) {
		// TODO Auto-generated method stub
		System.out.println("LogintrainerDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		System.out.println("search " + loginId);
		Query query = session.createQuery("from PersonalInformationTrainer where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainer> list = query.list();
		PersonalInformationTrainer personalInformationTrainer = null;
		for(PersonalInformationTrainer personalInformationTrainer1: list){
			personalInformationTrainer=personalInformationTrainer1;
		}
		return personalInformationTrainer;

	}

	@Override
	public PersonalInformationTrainingPartner fulldetailtainingpartner(int id) {
		System.out.println("LogintrainerpartnerDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		
		Query query = session.createQuery("from PersonalInformationTraininginstitute where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainingPartner> list1 = query.list();
		
		PersonalInformationTrainingPartner personalInformationTrainingPartner11 = null;
		for(PersonalInformationTrainingPartner personalInformationTrainingPartner:list1){
			personalInformationTrainingPartner11=personalInformationTrainingPartner;
		}
		return personalInformationTrainingPartner11;
	}
	
	
	@Override
	public PersonalInformationTrainingInstitute fulldetailtainingInstitute(int id) {
		System.out.println("LogintrainerpartnerDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = id;
		
		Query query = session.createQuery("from PersonalInformationTrainingInstitute where loginDetails = '"+ i +"'");
		List<PersonalInformationTrainingInstitute> list1 = query.list();
		
		PersonalInformationTrainingInstitute personalInformationTrainingInstitute11 = null;
		for(PersonalInformationTrainingInstitute personalInformationTrainingInstitute:list1){
			personalInformationTrainingInstitute11=personalInformationTrainingInstitute;
		}
		return personalInformationTrainingInstitute11;
	}
	
	@Override
	public StateAdmin FullDetailState(int loginId ) {
		System.out.println("LoginDAOImpl full detail process start ");
		Session session = sessionFactory.getCurrentSession();
		Integer i = loginId;
		System.out.println("search " + loginId);
		Query query = session.createQuery("from StateAdmin where loginDetails = '"+ i +"'");
		List<StateAdmin> list = query.list();
		StateAdmin stateadmin = null;
		for(StateAdmin stateadmin1: list){
			stateadmin=stateadmin1;
		}
		return stateadmin;
	}

	
	public void update() {
		
		Session session = sessionFactory.getCurrentSession();
		
		int id = 0;
		String pass = null;
		//Query query = session.createSQLQuery("select id , password from logindetails where coalesce(profile,'') not in ('A')");
		Query query = session.createSQLQuery("select id , password from logindetails");

		List list = query.list();
		System.out.println(" list.size() "+list.size());
		for(int i = 0 ; i < list.size() ; i++){
			
			Object[] obj = (Object[]) list.get(i);
			id = (int) obj[0];
			pass = (String) obj[1];
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			String encryprPassword=null;
			try {
				
				encryprPassword = encryptionPasswordANDVerification.encryptPass(pass);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			LoginDetails logindetails = (LoginDetails) session.load(LoginDetails.class, id);
			logindetails.setEncrypted_Password(encryprPassword);
			//logindetails.setProfile("A");
			session.update(logindetails);
		
		}
		
	
		
	}
	
	
/*	public static void main(String args[])throws IOException, ClassNotFoundException, SQLException{
	new LoginDAOImpl().revert();
	}
	
	
	public void revert() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:postgresql://114.79.137.170:5433/fosrest","postgres", "Fss2iZentech");
		
		
		int id = 0;
		String pass = null;
		Statement stmt=con.createStatement();
		
		ResultSet rs=stmt.executeQuery("select id , password from logindetails ");
		
		
		while(rs.next()){
	
			id=rs.getInt(1);
			pass=rs.getString(2);
			
			EncryptionPasswordANDVerification encryptionPasswordANDVerification = new EncryptionPasswordANDVerification();
			String encryprPassword=null;
			try {
				
				encryprPassword = encryptionPasswordANDVerification.encryptPass(pass);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			PreparedStatement pstmt =con.prepareStatement("update  LoginDetails  set Encrypted_Password=? where id =?");
			pstmt.setInt(2,id);
			pstmt.setString(1,encryprPassword);
			
			System.out.println(pstmt.executeUpdate());
		
		}
		
		con.close();
		
	}


*/
}
