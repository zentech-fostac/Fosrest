<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="website/js/commonController.js"></script>
   <script>
                function OnStart() {
                   
                	flatpickr("#trainingDate" , {
                		
                	});	
                }
                window.onload = OnStart;

            </script>
 
<cf:form action="ListGenerateCertificate.fssai" name="myForm" method="POST" commandName="GenerateCertificateForm" onsubmit="return validateFields();"> 

    <section>
         <%@include file="../roles/top-menu.jsp"%>
    </section>
    <!-- main body -->
    <section class="main-section-margin-top">
        <div class="container-fluid">
            <div id="wrapper">
                <!-- Sidebar -->
                <div > <%@include file="../roles/slider.jsp" %>
                </div>
                <!-- /#sidebar-wrapper -->
                <!-- Page Content -->
                <div id="page-content-wrapper">
                    <div class="container-fluid">
                        <!-- vertical button -->
                        <div class="row">
                            <div class="col-lg-12">
                                <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome Admin</span> </a>
                            </div>
                        </div>
                        <!-- add the content here for main body -->
                        <!-- timeline  -->
                        <div class="row">

                                <div class="col-xs-12">
                                    <h1>Generate Certificate</h1>
                                    <div class="row">
                                        <div class="col-xs-12">

                                            <!-- left side -->
                                            <div class="col-xs-6">
                                                <div class="form-group">
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Training Type:</strong></li>
                                                           <!--  valid -->
                                                            <li id="trainingTypeErr" style="display:none;" class="style-li error-red">Please Select Training Type.</li>
                                                            <li class="style-li error-red">
                                                            <span id="name_status" class = "clear-label"> </span>
                                                            ${created }</li>
                                                        </ul>
                                                    </div>
												<cf:select path="trainingType" class="form-control">
													<cf:option value="" label="Select training" />
													<cf:options items="${trainingType}" />
												</cf:select>
											</div>
                                                
                                               <div class="form-group">
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Training Partner:</strong></li>
                                                           <!--  valid -->
                                                             <li id="trainingPartnerErr" style="display:none;" class="style-li error-red" >Please Select Training Partner.</li>
                                                            
                                                            <li class="style-li error-red"><label class="error visibility" id="courseError">* error</label></li>
                                                        </ul>
                                                    </div>
                                                 <cf:select path="trainingPartner" class="form-control" onchange="getTrainingInstitude(this.value , 'trainingInstitute')">
													<cf:option value="" label="Select training partner" />
													<cf:options items="${trainingPartner}" itemValue="trainingPartnerId" itemLabel="trainingPartnerName"/>	
												</cf:select>
                                                </div>
                                               <div class="form-group">
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Batch Code:</strong></li>
                                                           <!--  valid -->
                                                             <li id="batchCodeErr" style="display:none;" class="style-li error-red" >Please Select Batch Code.</li>
                                                            
                                                            <li class="style-li error-red"><label class="error visibility" id="courseError">* error</label></li>
                                                        </ul>
                                                    </div>
                                                <cf:select path="batchCode" class="form-control">
													<cf:option value="0" label="Select Batch Code" />
													<cf:options items="${batchCodeList}" itemLabel="batchCode" itemValue="trainingScheduleId" />
												</cf:select>
                                                </div>
                                                
                                            
                                            </div> <!-- left side ends -->

                                            <!-- right side -->
                                            <div class="col-xs-6">

											 <div class="form-group">
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Training Institute:</strong></li>
                                                           <!--  valid -->
                                                            <li id="trainingInstituteErr" style="display:none;" class="style-li error-red" >Please Select Training Institute.</li>
                                                            <!--  -->
                                                            <li class="style-li error-red"><label class="error visibility" id="courseError">* error</label></li>
                                                        </ul>
                                                    </div>
                                                 <cf:select path="trainingInstitute" class="form-control">
													<cf:option value="0" label="Select training institute" />
													<cf:options items="${listTrainingInstitude}"/>	
												</cf:select>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Training Start Date:</strong></li>
                                                          <!--   valid -->
                                                             <li id="trainingDateErr" style="display:none;" class="style-li error-red" >Please Select Training Date.</li>
                                                           
                                                            <li class="style-li error-red">
                                                            <span id="name_status" class = "clear-label"> </span>
                                                            ${created }</li>
                                                        </ul>
                                                    </div>
												
                                         		<cf:input path="trainingDate" type="text" class="form-control" />
                                                </div>
                                                
                                            
                                                
                                         
                                         
                                                
                                            </div> <!-- rigth side ends -->
                                            
                                            <!-- button -->
                                            <div class="row">
                                                <div class="col-md-6 col-xs-12"></div>
                                                
                                                <div class="col-md-6 col-xs-12">

                                                    <input type="submit"  class="btn login-btn show-details-vacancy collapsed" data-toggle="collapse" data-target="#show-result" aria-expanded="false" value="Search"/> 
                                               
                                                </div>
                                            </div>
                                           
                                        </div>

                                       
                                    </div>
                                </div>

                                
                                <!-- search Results -->
                                            <div class="col-xs-12 " id="testt">

                                                <!-- table -->
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                            <fieldset>
                                           <legend>GenerateCertificate</legend>
                                            <ct:if test="${!empty listGenerateCertificate}">
                                            <table id="datatablesfosrest" class="table table-bordered table-responsive">
                                               <thead>
                                                    <tr class="background-open-vacancies">
                                                        
                                                       <th>S.No.</th>
                                                        <th>Training Type</th>
                                                        <th>Training Date</th>
                                                        <th>Training Partner</th>
                                                        <th>Training Institute</th>
                                                        <th>Trainee name</th>
                                                        <th>Attendance status</th>
                                                        <th>Certificate status</th>
                                                        <th>Generate Certificate</th>
                                                       
                                                        
                                                    </tr>
                                                </thead>
                                                
                                                <ct:forEach items="${listGenerateCertificate}" var="GenerateCertificate">
                                                <tr>
												<td>${GenerateCertificate.id}</td>
												<td>${GenerateCertificate.trainingType}</td>
												<td>${GenerateCertificate.trainingDate}</td>
												<td>${GenerateCertificate.trainingPartner}</td>
												<td>${GenerateCertificate.trainingInstitute}</td>
												<td>${GenerateCertificate.traineeName}</td>
												<td>${GenerateCertificate.attendanceStatus}</td>
												<td>${GenerateCertificate.certificateStatus}</td>
												<td>${GenerateCertificate.generateCertificate}
												 <class="text-center" ><input id="${GenerateCertificate.id}" type="checkbox"> 
                                                        </td>
                                                            
                                                       
												
											</tr>
										</ct:forEach>
										 
                                            </table>
                                            <div class="col-md-6 col-xs-12"></div>
                                               
                                                 <div class="col-md-6 col-xs-12">

                                                    <input type="button"  class="btn login-btn show-details-vacancy collapsed" data-toggle="collapse" data-target="#show-result" aria-expanded="false" onclick="getTraineeId();return false;" value="Generate Certificate"/> 
                                               
                                                </div>
                                                    </div>
                                           </ct:if>
                                           
                                        </fieldset>
                                        
                                                </div>
                                                 
                                           
                                            </div>
                                             
                                                                                        
                                           
                                        </div>
                             <!-- search div ends -->
                        </div><!-- row ends -->
                    </div>
                </div>
            </div>
        </div>
    </section>
 
 <input type="hidden" id="idHidden" value="" />
 <input type="hidden" id="hiddenCourseType" value="" />                                             
   </cf:form>
 <!--   validation function -->
   <script>
   
   
   function getTraineeId(){
	   var traineeIds = "";
		$('#datatablesfosrest').find('input[type="checkbox"]').each(
				function(i) {
					if (this.checked) {
					
					
					if(traineeIds == ""){
						traineeIds = $(this).attr("id");
					}else{
						traineeIds = traineeIds + ","+ $(this).attr("id");
					}
					}
				});
		
		console.log(" traineeIds "+traineeIds);
		var name = JSON.stringify({
			courseType : 0
		});
		$.ajax({
			type : 'post',
			url : 'updateCertificate.fssai?data=' + traineeIds,
			contentType : "application/json",
			data : name,
			success : function(response) {
				alert(response);
				location.reload();
			}
		});
   }
   
   function validateFields(){
   	// alert($("#holidayDate").val());
   	// alert($("#holidayReason").val());
   	/*  if($("#trainingType").val() == ''){
   		 
   		$("#trainingTypeErr").css("display" , "block");
   		return false;
   	 } else if($("#trainingInstitute").val() == ''){
   		 $("#trainingInstituteErr").css("display" , "block");
    		return false; 
   	 }else if($("#trainingPartner").val() == ''){
   		 $("#trainingPartnerErr").css("display" , "block");
 		return false; 
	 }else if($("#trainingDate").val() == ''){
   		 $("#trainingDateErr").css("display" , "block");
 		return false; 
	 } */

    }
   
   </script>