package com.ir.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ir.dao.CommonDao;
import com.ir.form.ChangePasswordForm;
import com.ir.util.ChangePasswordUtility;
import com.zentech.logger.ZLogger;

@Repository
@Service
public class CommonDaoImpl implements CommonDao{
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	@Qualifier("changePasswordUtility")
	public ChangePasswordUtility changePasswordUtility;
	
	@Override
	public String getCourseTrainingType(String courseNameId){
		Session session = sessionFactory.getCurrentSession();
		String response = "" ;
		String strQuery = "select case "
				+ "when online = 'Online' and classroom='Nil' THEN 'Online' "
				+ "when online = 'Nil' and classroom='Classroom' THEN 'Classroom' "
				+ "when online ='Online' and classroom = 'Classroom' THEN 'Both' END as modeOfTraining "
				+ "from coursename where coursenameid ="+courseNameId;
		try{
			Query query = session.createSQLQuery(strQuery);
			List list = (List)query.list();
			if(list.size() > 0){
				response = (String)list.get(0);	
			}
		}catch(Exception e){
			System.out.println("Exception while retrieving mode of training for course id - " + courseNameId + ":" + e.getMessage());
			response = "error";
		}finally{
		}
		return response;
	}
	
	@Override
	public boolean changePasswordSave(ChangePasswordForm changePasswordForm, String id) {
		String oldPassword=	changePasswordForm.getOldPassword();
		String newPassword=changePasswordForm.getNewPassword();
		boolean confirm = changePasswordUtility.changePasswordUtil(oldPassword, newPassword, id);
		return confirm;
	}
	
	
	@Override
	public String checkAadhar(String aadhar ,String tableName){
		Session session = sessionFactory.getCurrentSession();
		String response = null;
		String sql="select * from "+tableName+" where aadharnumber = '" + aadhar + "'";
		try{
			Query query = session.createSQLQuery(sql);
			List list = (List)query.list();
			if(list.size() > 0){
				new ZLogger("checkAadhar", "not available to use", "CommonDaoImpl.java");
				response ="Already";
			}else{
				response ="N";
			}
		}catch(Exception e){
			
		}finally{
		}
		return response;
	}
	

	@Override
	public  List getCourseName(String courseName){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select cn.coursenameid , cn.coursecode from coursename as cn inner join coursetype as ct on ct.coursetypeid = cn.coursetypeid where cn.coursetypeid  = '"+courseName+"'";		
		Query query = session.createSQLQuery(sql);
		List courseTypeList = query.list();
		return courseTypeList;
	}
	
	//getAssessorName
	
	
	
	@Override
	public  List getAssessorName(String assessorAgencyName){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select personalinformationassessorid , (firstname || ' ' ||  middlename || ' ' ||  lastname)  from personalInformationAssessor  where assessmentagencyname  = '"+assessorAgencyName +"'";		
		Query query = session.createSQLQuery(sql);
		List courseTypeList = query.list();
		return courseTypeList;
	}
	
	//getCourseTrainingMode
	
	@Override
	public String getCourseTrainingMode(String courseName ){
		Session session = sessionFactory.getCurrentSession();
		String response = null;
		String sql = "select online from coursename where coursenameid ="+courseName;
		
		try{
			Query query = session.createSQLQuery(sql);
			List list = (List)query.list();
			if(list.size()>0){
				response = (String)list.get(0);	
			}
		}catch(Exception e){
			
		}finally{
		}
		return response;
	}
	
	

	@Override
	public  List getTrainingInstitude(String data){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select id ,  trainingcentername  from  personalinformationtraininginstitute where correspondenceState= '"+data+"'";		
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		return list;
	}
	
	
	
	@Override
	public  List getModule(String data){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select moduleid ,  modulecode ,modulename from  ModuleMaster where unitId= '"+data+"'";		
		Query query = session.createSQLQuery(sql);
		List moduleList = query.list();
		return moduleList;
	}

	@Override
	public  List getBatchCode(String data,int stateId){
		String arr[]=data.split("-");
		String designationId=arr[0];
		String trTypeId=arr[1];
		String trPhaseId=arr[2];
		Session session = sessionFactory.getCurrentSession();
		String sql="select distinct tc.trainingInstitute ,pit.trainingCenterName from trainingCalendar tc inner join personalinformationtraininginstitute pit on pit.id=cast(tc.trainingInstitute as numeric) where designation= '"+designationId+"' and trainingType='"+trTypeId+"' and trainingPhase='"+trPhaseId+"'and pit.correspondenceState='"+stateId+"'";
		Query query = session.createSQLQuery(sql);
		List batchCodeList = query.list();
		return batchCodeList;
	}
	@Override
	public  List loadTrainingPhase(int trainingTypeId){
		Session session = sessionFactory.getCurrentSession();
        String sql = "  select tp.trainingPhaseName from trainingPhase tp where tp.trainingType= '"+trainingTypeId+"'";		
		Query query = session.createSQLQuery(sql);
		List PhaseList = query.list();
		return PhaseList;
	}

	@Override
	public String checkState(String id, String tableName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String response = null;
		String sql="select * from "+tableName+" where state = '" + id + "'";
		try{
			Query query = session.createSQLQuery(sql);
			List list = (List)query.list();
			if(list.size() > 0){
				new ZLogger("checkState", "not available to use", "CommonDaoImpl.java");
				response ="Already";
			}else{
				response ="N";
			}
		}catch(Exception e){
			
		}
		return response;
	}

	@Override
	public List getSceduleCode(String data) {
		// TODO Auto-generated method stub
		
		String arr[]=data.split("-");
		
		String designationId=arr[0];
		String trTypeId=arr[1];
		String trPhaseId=arr[2];
		
		Session session = sessionFactory.getCurrentSession();
		String sql = "select scheduleCode from  trainingSchedule where designation= '"+designationId+"' and trainingType='"+trTypeId+"' and trainingPhase='"+trPhaseId+"' and isActive='Y'";		
		Query query = session.createSQLQuery(sql);
		List shList = query.list();
		return shList;
	}

}