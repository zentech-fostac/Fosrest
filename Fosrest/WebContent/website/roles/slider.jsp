<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 10);
	window.onunload = function() {
		null
	};
</script>
<%
	System.out.println("Profile ID : "
			+ session.getAttribute("profileId"));
	if (session.getAttribute("profileId").equals(3)) {
		System.out.println("Profile ID : "
				+ session.getAttribute("profileId"));
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		        <li><a href="OnlineTraining.fssai">Training </a></li>
				<li><a href="assessment-instructions-trainee.fssai">Assessment</a></li>
				<li><a href="GetScoreCard.fssai">Get Score Card </a></li>
				<li><a href="Certificate.fssai">Certificate</a></li>
				<li><a href="PersonalInformationTrainee.fssai">Update Profile</a></li>
		<!-- <li><a href="course-training.fssai?courseTypeId=1">Online Assessment</a></li>-->
		<!-- <li><a href="assessment-instructions-trainee.fssai">Assessment</a></li> -->
		<!-- <li><a href="feedbackForm.fssai">Feedback</a></li>
		<li><a href="generateCertificatetrainee.fssai">Certification</a>
		<li><a href="generateCourseCertificate.fssai">Past Certificate</a> </li> -->
		</li>
	</ul>
</div>
<%
	} else if (session.getAttribute("profileId").equals(4)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<!-- <li><a href="search-and-apply.fssai">Search	& Apply Vacancy</a></li> -->
	<li><a href="loginProcess.fssai">Upcoming Training</a></li>

	<li><a href="trainerUpdateResult.fssai">Update Result</a></li>
	<li><a href="PersonalInformationTrainer.fssai">Update Profile</a></li>
		<li> <a href="viewassessmentquestions.fssai">View Questions</a> </li>
	</ul>
</div>

<%
	} else if (session.getAttribute("profileId").equals(5)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<!-- <li class="sidebar-brand">
                        </li> -->
                        <li><a href="loginProcess.fssai">Upcoming Training</a></li>
		<!-- <li><a href="TrainingRequest.fssai">Training Request</a></li> -->
	<li> <a href="traineeAttendance.fssai">Mark Attendance</a> </li>
	<li> <a href="viewnominatedtrainee.fssai">View Nominated Trainee</a> </li>
		<li><a href="PersonalInformationTrainingInstitute.fssai">Update Profile</a></li>
	
	</ul>
</div>

<%
	} else if (session.getAttribute("profileId").equals(6)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<!-- <li class="sidebar-brand">
                    </li> -->
		<li><a href="viewAssessmentAgencyCalendar.fssai">Assessment
				Calendar</a></li>
		<!-- <li> <a href="trainingpartnermarkAttendence.fssai">Mark Attendance</a> </li> -->
		<li><a href="traineeAttendance.fssai">Trainee Attendance</a></li>
		<li><a href="update-result.fssai">Update Result</a></li>
		<li><a href="trainingpartnertrainingcalendar.fssai">Reschedule
				Assessment</a></li>
	</ul>
</div>

<%
	} else if (session.getAttribute("profileId").equals(7)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<!-- <li class="sidebar-brand">
                        </li> -->
		<li><a href="postVacancyTrainingPartner.fssai">Post Vacancy
				for Trainer</a></li>
		<li><a href="trainingpartnerapplicationstatus.fssai">Application
				Status</a></li>
		<li><a href="trainingCalendarForm.fssai">View Training
				Calendar</a></li>
		<li><a href="viewFeedbackDetails.fssai">View Feedback</a></li>
		<li><a href="registrationFormTrainingPartner.fssai">Register
				Training Center</a></li>
	</ul>
</div>

<%
	} else if (session.getAttribute("profileId").equals(8)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<!-- <li class="sidebar-brand"></li> -->
		<!-- TODO -->
		<!--                         <li><a href="viewTrainingCalendar.fssai">View Calendar</a></li> -->
		<li><a href="viewAssessmentAgencyCalendar.fssai">View
				Calendar</a></li>


	</ul>
</div>

<%
	} else if (session.getAttribute("profileId").equals(1)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><a href="trainingschedulemaster.fssai"><p    style=" margin-left: -13px;">Training Schedule Master</p></a></li>
	<!-- 	<li><a href="TrainingClosure.fssai">Training Closure Report</a></li> -->
		<!-- <li><a href="GenerateCertificate.fssai">Generate Certificate</a> -->
		</li>
		<!-- <li><a href="UpdateTrainingSchedule.fssai">Update Training
				Schedule</a></li> -->
		<!-- <li><a href="HolidayMaster.fssai">Holiday Master</a></li> -->
		<!-- <li><a href="UnitMaster.fssai">Chapter Master</a></li> -->
		<li><a href="SubjectMaster.fssai">Subject Master</a></li>
		<li><a href="manageAssessmentQuestions.fssai">Assessment Questions</a></li>
		<!-- <li><a href="SubjectMaster.fssai">Subject Master</a></li>-->
		<li><a href="trainingcalendar.fssai">Training Calendar</a></li> 
		<li><a href="ResetPassword.fssai">Reset Password</a></li>
		<li><a href="photogallery.fssai">Upload Photo Gallery</a></li>
	<li> <a href="viewnominatedtrainee.fssai">View Nominated Trainee</a> </li>
                   
	</ul>
</div>

<%
	} else if (session.getAttribute("profileId").equals(2)) {
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<!-- <li class="sidebar-brand"></li> -->
		<!-- <li><a href="traineeUserManagementForm.fssai">Trainee User Management Form</a></li> -->
		     <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Create User<i class="fa fa-angle-down"></i></a>
            <ul class="dropdown-menu">
       			<li><a href="PersonalInformationTrainee.fssai">Trainee</a></li>
              <li><a href="PersonalInformationTrainer.fssai">Trainer</a></li>
              <li><a href="PersonalInformationTrainingInstitute.fssai">Training Institute</a></li>
            </ul>
          </li>
       <li><a href="NominateTrainee.fssai">Nominate Trainee</a></li>
		<!-- <li><a href="TrainingSchedule.fssai">Update Training Schedule</a></li> -->
		  <li><a href="trainingcalendar.fssai">Training Calendar</a></li> 
		  <li><a href="stateAdminUpdateResult.fssai">Update Result</a></li>
		  <li> <a href="viewnominatedtrainee.fssai">View Nominated Trainee</a> </li>
		  	<li><a href="stateAdminPersonalInformation.fssai">Update Profile</a></li>
		  		<li> <a href="viewassessmentquestions.fssai">View Questions</a> </li>
</ul>
</div>

<%
	} else if(session.getAttribute("profileId").equals(20)){
%>

<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li> <a href="CustomerMaster.fssai">Customer</a> </li>
        <li> <a href="TaxMaster.fssai">Tax Master</a> </li>
        <li><a href="EmployeeMonthlyCharges.fssai">Employee Monthly Charges</a></li>
        <li><a href="CustomerDetails.fssai">Customer Details</a></li>
        <li><a href="InvoiceMaster.fssai">Invoice Details</a></li>
     <!--    <li><a href="invoicePrint.fssai">Invoice</a></li> -->
</ul>
</div>

<%
	} else if(session.getAttribute("profileId").equals(30)){
%>

<div id="sidebar-wrapper">
                    <ul class="sidebar-nav">
                        <!-- <li class="sidebar-brand"></li> -->
                        <li><a href="RegionMapping.fssai">Region Mapping</a></li>
                        <li><a href="manageTraining.fssai">Manage Training</a></li>
                        <li> <a href="managecoursecurriculum.fssai">Manage Course Curricullum</a> </li>
                        <li> <a href="fotestassessmentquestions.fssai">Assessment Questions</a> </li>
                        <li> <a href="fotestFeedbackMaster.fssai">Feedback Master</a> </li>
                        <li> <a href="verifyTraineeEnrollment.fssai">Verify Trainee Enrollment</a> </li>
                        <li> <a href="managetrainingcalendar.fssai">Manage Training Calendar</a> </li>
                        <li> <a href="feedbackMaster.fssai">Activate Training</a> </li>
                        <li> <a href="activateAssessmentOfTrainee.fssai">Activate Assessment</a> </li>
                        <li> <a href="viewtrainingcalendar.fssai"> View Training Calendar</a> </li>
                        <li> <a href=viewEnrolledCourses.fssai> View Enrolled Courses</a> </li>
                        <li> <a href="fotestGenerateCertificate.fssai">Generate Certificate</a> </li>
                    </ul>
                </div>

<%
	} 
%>
<%
	System.out.println("Profile ID : "
			+ session.getAttribute("profileId"));
	if (session.getAttribute("profileId").equals(33)) {
		System.out.println("Profile ID : "
				+ session.getAttribute("profileId"));
%>
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
	            <li class="dropdown active"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Course Enrollment<span class="caret"></span></a>
                                        <ul class="dropdown-menu" > 
                                            <li class="hov"><a href="traineeUserManagementForm.fssai" class="clr">General</a></li>
                                            <li class="hov"><a href="trainerUserManagementForm.fssai" class="clr">LC-MS/MS</a></li>
                                            <li class="hov"><a href="trainingCenterUserManagementForm.fssai" class="clr">GC-MS/MS</a></li>
                                            <li class="hov"><a href="assessorUserManagementForm.fssai" class="clr" style="margin-bottom: 1px;">ICP-MS</a></li>
                                        </ul>
               </li> 
                <li><a href="fotestonlinetraining.fssai">Print Admit Card </a></li>
                <li class="dropdown active"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Assessment<span class="caret"></span></a>
                                        <ul class="dropdown-menu" ><!-- style="position:relative; float:none;" --> 
                                            <li class="hov"><a href="traineeUserManagementForm.fssai" class="clr">Before Training</a></li>
                                            <li class="hov"><a href="trainerUserManagementForm.fssai" class="clr">After Training</a></li>
                                        </ul>
                </li> 
		        <li><a href="fotestonlinetraining.fssai">Online Training </a></li>
				<li><a href="fotestFeedback.fssai">Feedback</a></li>
				<li><a href="fotestcertification.fssai">Certification</a></li>
	</ul>
</div>
<%
	}
	%>	

