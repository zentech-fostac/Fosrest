<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="cs" uri="http://www.springframework.org/tags"%>
        <%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core"%>
        <!-- @author Jyoti Mekal -->




 <script src="website/js/commonController.js"></script>

   			  <ct:url var="addAction" value="/UnitMaster/add.fssai" ></ct:url>
            <cf:form action="${addAction}" name="myForm" method="POST" commandName="UnitMaster" onsubmit="return validateFields();">

                <section>
                    <%@include file="../roles/top-menu.jsp"%>
                </section>
                <!-- main body -->
                <section class="main-section-margin-top">
                    <div class="container-fluid">
                        <div id="wrapper">
                            <!-- Sidebar -->
                            <%@include file="../roles/slider.jsp" %>
                                <!-- /#sidebar-wrapper -->
                                <!-- Page Content -->
                                <div id="page-content-wrapper">
                                    <div class="container-fluid">
                                        <!-- vertical button -->
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome Admin</span>
                                                </a>
                                            </div>
                                        </div>
                                        <!-- add the content here for main body -->
                                        <!-- timeline  -->
                                        <div class="row">

                                            <div class="col-xs-12">
                                                <h1>Chapter Master <label id="created" class="style-li error-red">${created }</label></h1>
                                                <div class="row">
                                                    <div class="col-xs-12">
												                           <fieldset>
                                        <legend>Search Chapter Master</legend>
                                        <!-- table starts here -->
                                        <!-- left side -->
                                        <div class="col-md-6 col-xs-12">
                                            
                                            <cf:input path="unitId" type="hidden" />
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Designation:</strong></li>
                                                         <li id="userTypeErr" style="display:none;" class="style-li error-red" >Please Select Designation.</li>
                                                        <li class="style-li error-red"> </li>
                                                    </ul>
                                                </div>
                                            <cf:select path="Designation" class="form-control">
															<cf:option value="" label="Select Designation" />
															<cf:options items="${DesignationList}"
																itemValue="designationId" itemLabel="designationName" />
														</cf:select>
                                            </div>
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong> Training Type:</strong></li>
                                                        <li id="trainingTypeErr" style="display:none;" class="style-li error-red" > Please Select Training Type.</li>
                                                        <li class="style-li error-red"> </li>
                                                    </ul>
                                                </div>
														 <cf:select path="trainingType" class="form-control" onchange="getTrainingPhase(this.value , 'trainingPhase')">
															<cf:option value="" label="Select Training Type" />
															<cf:options items="${TrainingTypeList}"
																itemValue="trainingTypeId" itemLabel="trainingTypeName" />
														</cf:select>
                                            </div>
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong> Training Phase:</strong></li>
                                                        <li id="trainingPhaseErr" style="display:none;" class="style-li error-red" > Please Select Training Phase.</li>
                                                        <li class="style-li error-red"> </li>
                                                    </ul>
                                                </div>
												 <cf:select path="trainingPhase" class="form-control">
															<cf:option value="" label="Select Training Type" />
															<cf:options items="${TrainingPhaseList}"
																itemValue="trainingPhaseId" itemLabel="trainingPhaseName" />
														</cf:select>
                                            </div>
                                            
                                        </div>
                                        <!-- right side -->
                                        <div class="col-md-6 col-xs-12">
                                            
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong>Chapter Name:</strong></li>
                                                           <li id="unitNameErr" style="display:none;" class="style-li error-red" > Please Enter Chapter Name.</li>
                                                        <li class="style-li error-red"> </li>
                                                    </ul>
                                                </div>
                                                <cf:input type="text" path="unitName"  placeholder="Chapter Name" class="form-control"/> 
                                            </div>
                                            
                                            <div class="form-group">
                                                <div>
                                                    <ul class="lab-no">
                                                        <li class="style-li"><strong> Status:</strong></li>
                                                        <li class="style-li error-red"> </li>
                                                    </ul>
                                                </div>
                                           	<cf:select path="status" class="form-control">
                                             <cf:option value="A" label="Active" />
                                              <cf:option value="I" label="In-Active" />
                                              </cf:select>
                                            </div>
                                            
                                            <div class="row">
                                                <div class="col-md-6 col-xs-12" style="margin-top: 25px;">
                                                   <input type="submit"  id="updatebtn" style="display:none;float: right;margin-right: 122px;"
														value="Update" class="btn login-btn"/>
												
												
													<input type="submit" id="createbtn"
														value="Create"  class="btn login-btn"/>
												
                                                </div>
                                                <div class="col-md-6 col-xs-12" style="margin-top: 25px;">
                                                   <!--   <button  class="btn login-btn show-details-vacancy collapsed" data-toggle="collapse" data-target="#show-result" aria-expanded="false">Show Details</button> -->
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                                    </div>


                                                </div>
                                            </div>

                                            <!-- search Results -->
                                            <div class="col-xs-12 " id="testt">

                                                <!-- table -->
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                            <fieldset>
                                           <legend>Chapter Master</legend>
                                            <ct:if test="${!empty listUnitMaster}">
                                            <table border="1" id="datatablesfosrest" class="table table-bordered table-responsive">
                                               <thead>
                                                    <tr class="background-open-vacancies">
                                                        <th>S.No.</th>
                                                        <th>Designation</th>
                                                        <th>Training Type</th>
                                                        <th>Training Phase</th>
                                                        <th>Chapter Name</th>
                                                        <th>Chapter Code</th>
                                                        <th>Status</th>
                                                        <th>Edit</th>
                                                        <th>Delete</th>
                                                    </tr>
                                                </thead>       
                                            <ct:forEach items="${listUnitMaster}" var="UnitMaster" varStatus="loop">
                                                <tr>
												<td>${loop.count}</td>
												<td>${UnitMaster[0]}</td>
												<td>${UnitMaster[1]}</td>
												<td>${UnitMaster[2]}</td>
												<td>${UnitMaster[3]}</td>
												<td>${UnitMaster[4]}</td>
												<td><ct:choose><ct:when test="${ UnitMaster[5] == 'A'}">Active</ct:when> <ct:otherwise>In-Active</ct:otherwise></ct:choose></td> 
											<td><button onclick='editUnit(${UnitMaster[6]});return false;' >Edit</button></td>
												<td><a href="<ct:url value='/UnitMaster/remove/${UnitMaster[6]}.fssai' />" >Delete</a></td>
											</tr>
										</ct:forEach>
                                            </table>
                                           </ct:if>
                                        </fieldset>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- search div ends -->

                                        </div>
                                        <!-- row ends -->

                                    </div>
                                </div>
                        </div>
                    </div>
                </section>
                <input type="hidden" id="idH" value="" />
            </cf:form>
            <script>
            function editUnit(id){
                
                var name1=JSON.stringify({
            		courseName:0
              })
            	$.ajax({
            	      type: 'post',
            	      url: 'UnitMaster/edit/'+id+'.fssai',
            	      contentType : "application/json",
            		  data:name1,
            	      success: function (response) {      
            	      var mainData1 = jQuery.parseJSON(response);
            	    $("#unitId").val(mainData1.unitId);
            	    $("#Designation").val(mainData1.Designation);
            	    $("#trainingType").val(mainData1.trainingType);
            	    $("#trainingPhase").val(mainData1.trainingPhase);
            	    $("#unitName").val(mainData1.unitName);
            	     $("#updatebtn").css("display" , "block");
            	     
            	     $("#createbtn").css("display" , "none");
            	      }
            	      });     
                
                }

            function validateFields(){
            	 $("#userTypeErr").css("display" , "none");
            	 $("#unitNameErr").css("display" , "none");
            	 $("#trainingTypeErr").css("display" , "none");
            	$("#trainingPhaseErr").css("display" , "none");
                	if($("#userType").val() == ''){
               		 $("#userTypeErr").css("display" , "block");
                		return false; 
               	 }
                	else if($("#unitName").val() == ''){
               		 
             		$("#unitNameErr").css("display" , "block");
             		return false;
             	 }
                 else if($("#trainingType").val() == 0){
               		 
             		$("#trainingTypeErr").css("display" , "block");
             		return false;
             	 }
             	/* else if($("#trainingPhase").val() == 0){
              		 
             		$("#trainingPhaseErr").css("display" , "block");
             		return false;
             	 } */
                	}
            </script>
