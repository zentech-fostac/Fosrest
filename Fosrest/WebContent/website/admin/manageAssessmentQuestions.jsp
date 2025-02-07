<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="website/js/commonController.js"></script>

<script>
function OnStart(){
	
document.getElementById('id').value = 0;
document.getElementById('subjectCode2').value = 0;
//document.getElementById('designation2').value = 0;
document.getElementById('trainingType2').value = 0;
document.getElementById('trainingPhase2').value = 0;
/* document.getElementById('unitCode').value = 0;
document.getElementById('moduleCode').value = 0; */
document.getElementById('questionNumber').value = '';
//document.getElementById('questionHint').value = '';
document.getElementById('questionTitle').value = '';
document.getElementById('noOfAssesmentQues').value = 0;
document.getElementById('correctAnswer').value = '';

//getModule(document.getElementById('unitCode1').value,'moduleCode1');
//getModule(document.getElementById('unitCode2').value,'moduleCode2');

}
window.onload = OnStart;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function getQuestions(){
//	var unitCodeSearch =  $("#unitCode1").val();
	var subjectCodeSearch =  $("#subjectCode1").val();
	var designationSearch =  $("#designation1").val();
	var trainingTypeSearch =  $("#trainingType1").val();
	var trainingPhaseSearch =  $("#trainingPhase1").val();
	//var total = "unitCodeSearch="+unitCodeSearch+"-moduleCodeSearch="+moduleCodeSearch;
	var total = "designationSearch="+designationSearch+"-trainingTypeSearch="+trainingTypeSearch+"-trainingPhaseSearch="+trainingPhaseSearch+"-subjectCodeSearch="+subjectCodeSearch;
	//alert(total);
	var result="";
	var name1=JSON.stringify({ 
		courseType:0
  })
	$.ajax({
	type: 'post',
	url: 'getQuestions.fssai?data='+ total,
	//url: 'getQuestions.fssai?data='+ moduleCodeSearch,
	contentType : "application/json",
	 data:name1,
	async: false, 
	success: function (data){
	$('#newTable').show();
	var mainData1 = jQuery.parseJSON(data);

	$('#newTable tr').remove();
	if(data=="[]"){  
	    //alert("What follows is blank: " + data);
	    $('#newTable').append('<tr><th>Empty</th></tr>')
	}
	if(!(data=="[]")){ 
	    //alert("What follows is NOT blank: " + data);
		//$('#newTable').append('<tr  class="background-open-vacancies"><th>Sr. No.</th><th>Subject Name (Edit)</th><th>Question No</th><th>Question Title</th><th>Delete</th></tr>')
						$('#newTable').append('<tr  class="background-open-vacancies"><th>Question No</th><th>Question Title (Edit)</th><th>Delete</th></tr>')

		
		$.each(mainData1 , function(i , obj)
		{
			//$('#newTable').append('<tr  id="tableRow"><td>'+j++ +'</td><td><a href="" onClick="return editAssessmentQuestion('+obj[2]+')">'+obj[2]+' - '+obj[3]+'</a></td><td>'+obj[4]+'</td></tr>');
			/* var a=obj[2];
			alert(a);
			$('#newTable').append('<tr  id="tableRow"><td>'+j++ +'</td><td><a href="" onClick="return editAssessmentQuestion('+obj[2]+')">'+obj[2]+' - '+obj[3]+'</a></td><td>'+obj[4]+'</td><td><a href="<ct:url value='/rr/remove/${a}.fssai' />" >Delete</a></td></tr>');
			 */
	/* 																																											
	 */		
	 	//$('#newTable').append('<tr  id="tableRow"><td>'+(i+1) +'</td><td><a href="" onClick="return editAssessmentQuestion('+obj[2]+')">'+obj[5]+'</a></td><td>'+obj[3]+'-'+obj[1]+'</td><td>'+obj[4]+'</td><td><a href="" onClick="return deleteAQ('+obj[2]+')" >Delete</a></td></tr>');
		 	$('#newTable').append('<tr  id="tableRow"><td>'+(i+1)+'</td><td><a href="" onClick="return editAssessmentQuestion('+obj[1]+','+(i+1)+')">'+obj[3]+'</a></td><td><a href="<ct:url value="/deleteassessmentquestion/'+obj[1]+'.fssai" />">Delete</a></td</tr>');  

	}); 

	}
	
}
});
return result;
}

//

//
function deleteAQ(id){   
//alert(id);
var name1=JSON.stringify({
	courseName:0
})
$.ajax({
      type: 'post',
      url: 'deleteassessmentquestion/'+id+'.fssai',
      contentType : "application/json",
	  data:name1,
      success: function (response) {      
     }
      });   

location.reload();
return false;

}

function editAssessmentQuestion(id,Qcount){
	var result="";
	$.ajax({
	type: 'post',
	url: 'getSingleAssessmentQuestions.jspp?'+ id,
	async: false, 
	success: function (data){
	var mainData1 = jQuery.parseJSON(data);
	$.each(mainData1 , function(i , obj)
	{
		document.getElementById('id').value = obj[0];
	/* 	document.getElementById('unitCode2').value = obj[1];
		$("#unitCode2").trigger("change");
		window.setTimeout(function(){
			document.getElementById('moduleCode2').value = obj[2];
	    }, 3000); */
	    document.getElementById('subjectCode2').value = obj[4];
		document.getElementById('designation2').value = obj[1];
		document.getElementById('trainingType2').value = obj[2];
		document.getElementById('trainingPhase2').value = obj[3];
		document.getElementById('questionNumber').value = Qcount;
		document.getElementById('dispQuestionNumber').value = Qcount;
		//document.getElementById("dispQuestionNumber").disabled = true;
		//document.getElementById('questionHint').value = obj[3];
		document.getElementById('questionTitle').value = obj[6];
		document.getElementById('noOfAssesmentQues').value = obj[7];
		$("#noOfAssesmentQues").trigger("change");
		window.setTimeout(function(){
			for(j=1;j<=obj[4];j++){
				if(j==1){
					document.getElementById('optionOne').value = obj[9];
				}else if(j==2){
					document.getElementById('optionTwo').value = obj[10];
				}else if(j==3){
					document.getElementById('optionThree').value = obj[11];
				}else if(j==4){
					document.getElementById('optionFour').value = obj[12];
				}else if(j==5){
					document.getElementById('optionFive').value = obj[13];
				}else if(j==6){
					document.getElementById('optionSix').value = obj[11];
				}
			}
	    }, 1000);
		document.getElementById('correctAnswer').value = obj[8];
	});
	}
	});
	return false;
}

function validateFields(){
		
	$(':focus').blur();// this is required for to call answerno() method
	

	$("#designationErr").css("display", "none");

	$("#trainingTypeErr").css("display", "none");
	$("#trainingPhaseErr").css("display", "none"); 
	// $("#unitCodeErr").css("display" , "none");
	 $("#subjectCodeErr").css("display" , "none");
	$("#questionNumberErr").css("display" , "none");
$("#questionTitleErr").css("display" , "none");
//$("#questionHintErr").css("display" , "none");
$("#noOfAssesmentQuesErr").css("display" , "none");
$("#assAnsTableErr").css("display" , "none");
$("#correctAnswerErr").css("display" , "none");


if ($("#designation2").val() == 0) {
	$("#designationErr").css("display", "block"); 
	return false;
}
if ($("#trainingType2").val() == 0) {
	$("#trainingTypeErr").css("display", "block");
	return false;
}
if ($("#trainingType2").val() == 3 && $("#trainingPhase2").val() == 0) {// 3 for induction 
	$("#trainingPhaseErr").css("display", "block");
	return false;
}

/* if($("#unitCode2").val() == 0){
	 
		$("#unitCodeErr").css("display" , "block");
		return false;
	 }  */
if($("#subjectCode2").val() == 0){
	 
		$("#subjectCodeErr").css("display" , "block");
		return false;
	 } 
 /*if($("#questionNumber").val() == 0){
	 
	$("#questionNumberErr").css("display" , "block");
	return false;
}  */
if($("#questionTitle").val() == ''){
	 
		$("#questionTitleErr").css("display" , "block");
		return false;
	 } 

/* if($("#questionHint").val() == ''){
	 
		$("#questionHintErr").css("display" , "block");
		return false;
	 }  */

if($("#noOfAssesmentQues").val() < 1){
	 
		$("#noOfAssesmentQuesErr").css("display" , "block");
		return false;
	 } 
if($("#optionOne").val() == '' || $("#optionTwo").val() == '' || $("#optionThree").val() == '' || $("#optionFour").val() == '' || $("#optionFive").val() == '' || $("#optionSix").val() == '' ){
	 
		$("#assAnsTableErr").css("display" , "block");
		return false;
	 } 


if($("#correctAnswer").val() == 0){
	 
		$("#correctAnswerErr").css("display" , "block");
		return false;
	 } 

return answerno("correctAnswer",$("#correctAnswer").val());
}



function getQuestionNo(subId){
	//var val=document.getElementById('unitCode2').value+'-'+	document.getElementById('moduleCode2').value;

	//alert(val );
	$("#designationErr").css("display", "none");

	$("#trainingTypeErr").css("display", "none");
	$("#trainingPhaseErr").css("display", "none"); 
	
if ($("#designation2").val() == 0) {
	$("#designationErr").css("display", "block"); 
	$("#subjectCode2").val('0');
	return false;
}
if ($("#trainingType2").val() == 0) {
	$("#trainingTypeErr").css("display", "block");
	$("#subjectCode2").val('0');
	return false;
}
if ($("#trainingType2").val() == 3 && $("#trainingPhase2").val() == 0) {// 3 for induction 
	$("#trainingPhaseErr").css("display", "block");
	$("#subjectCode2").val('0');
	return false;
}
	
	var name=JSON.stringify({
		courseType:0,
		courseName:0
  })
	$.ajax({
		type : 'post', 
		url : 'getQuestionNumber.fssai?data='+$("#designation2").val()+"|"+$("#trainingType2").val()+"|"+$("#trainingPhase2").val()+"|"+ subId,
		contentType : "application/json",
	    data:name,
		success : function(response) {
			var mainData1 = jQuery.parseJSON(response);
			//alert("Question Numnber "+mainData1); 
			document.getElementById('questionNumber').value =mainData1+1;
			document.getElementById('dispQuestionNumber').value =mainData1+1;
			//document.getElementById("dispQuestionNumber").disabled = true;
			
			
		}
	});
	
}


</script>

<cf:form   action="manageAssessmentQuestionsSave.fssai" name="myForm" method="POST" commandName="assessmentQuestionForm" onsubmit="return validateFields();"> 
<script>
    function generateAnsSeq(){
    	$('#assAnsTable').html("");
    	var noOfAssmentQ =  $("#noOfAssesmentQues").val();
    	$('#assAnsTable').append('<tr><th>Sr.no</th><th>options</th></tr>')
		
    	for(i=1;i<=noOfAssmentQ;i++){
    		if(i==1){
    			$('#assAnsTable').append("<tr><td style='text-align:center;'>"+i+'</td><td><cf:input path="optionOne" class="form-control" /></td></tr>')
    			document.getElementById('optionOne').value = '';
    			
    		}else if(i==2){
    			$('#assAnsTable').append("<tr><td style='text-align:center;'>"+i+'</td><td><cf:input path="optionTwo" class="form-control" /></td></tr>')
    			document.getElementById('optionTwo').value = '';
    			
    		}else if(i==3){
    			$('#assAnsTable').append("<tr><td style='text-align:center;'>"+i+'</td><td><cf:input path="optionThree" class="form-control" /></td></tr>')
    			document.getElementById('optionThree').value = '';
    			
    		}else if(i==4){
    			$('#assAnsTable').append("<tr><td style='text-align:center;'>"+i+'</td><td><cf:input path="optionFour" class="form-control" /></td></tr>')
    			document.getElementById('optionFour').value = '';
    			
    		}else if(i==5){
    			$('#assAnsTable').append("<tr><td style='text-align:center;'>"+i+'</td><td><cf:input path="optionFive" class="form-control" /></td></tr>')
    			document.getElementById('optionFive').value = '';
    			
    		}else if(i==6){
    			$('#assAnsTable').append("<tr><td style='text-align:center;'>"+i+'</td><td><cf:input path="optionSix" class="form-control" /></td></tr>')
    			document.getElementById('optionSix').value = ''; 
    	}
    	}
    }
    
    function func2(){
    	$("#designationErr").css("display", "none");

    	$("#trainingTypeErr").css("display", "none");
    	$("#trainingPhaseErr").css("display", "none"); 
    	$("#subjectCode2").val('0');
    }
    

    function answerno(id,ansno){
	
    	var noOfAssmentQ =  $("#noOfAssesmentQues").val();
       	if(parseInt(ansno) > parseInt(noOfAssmentQ)){
       		
    	document.getElementById(id).value="";
    	$("#correctAnswerErr").css("display" , "block");
     	return false;
    	 } 
       	else
       		$("#correctAnswerErr").css("display" , "none");

      
    	}
    
  /*   function getModule(val , idName) {
    	
    	$('#'+idName+' option').remove();
     	var name=JSON.stringify({
    		courseType:0,
    		courseName:0
      })
    	$.ajax({
    		type : 'post',
    		url : 'getModule.fssai?data='+ val,
    		contentType : "application/json",
    	    data:name,
    		success : function(response) {
    			var mainData1 = jQuery.parseJSON(response);
    			$('#'+idName+' option').remove();
    			$('#'+idName).append(
    					'<option value="0" label="--Select Training Institude--" />');
    			$.each(mainData1, function(i, obj) {
    				$('#'+idName)
    						.append(
    								'<option value='+obj[0]+' >' + obj[2]
    										+ '</option>');
    			});
    		}
    	});
    } */
    </script>
    <section>
        <%@include file="../roles/top-menu.jsp"%>
    </section>
    <!-- main body -->
    <section class="main-section-margin-top">
        <div class="container-fluid">
            <div id="wrapper">
                <!-- Page Content -->
                <div><%@include file="../roles/slider.jsp" %>
                </div>
                <div id="page-content-wrapper">
                    <div class="container-fluid">
                        <!-- vertical button -->
                        <div class="row">
                            <div class="col-lg-12">
                                <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome ${userName} </span> </a>
                            </div>
                        </div>
                        <!-- add the content here for main body -->
                        <!-- timeline  -->
                        <h1>Manage Assessment Questions</h1> 
                        <div class="row">

                                <div class="col-xs-12">
                                  
                                   <fieldset> <legend>Search Questions</legend>
                                    <span id="name_status" class = "style-li error-red"><h3>${created } </h3></span>
                                   <BR>                          
                                    <div class="row">
                                        <div class="col-xs-12">
									         <!-- left side -->
									         <div class="col-xs-6">
									         <div class="form-group">
															<div>
																<ul class="lab-no">
																	<li class="style-li"><strong>Designation:</strong></li>
																	
																</ul>
															</div>
															<cf:select path="designation1" class="form-control">
																<cf:option value="0" label="Select Designation" />
															 	<cf:options items="${DesignationList}" 
																	itemValue="designationId" itemLabel="designationName"  /> 
															</cf:select>
	
														</div>
													
													<div class="form-group">
													<div>
														<ul class="lab-no">
															<li class="style-li"><strong>Training Type:</strong></li>
															
															
														</ul>
													</div>
													<cf:select path="trainingType1" class="form-control"
														onchange="getTrainingPhase(this.value , 'trainingPhase1');">
														<cf:option value="0" label="Select Training Type" />
														<cf:options items="${TrainingTypeList}"
															itemValue="trainingTypeId" itemLabel="trainingTypeName" />
													</cf:select>
												</div>
												<div class="form-group">
													<div>
														<ul class="lab-no">
															<li class="style-li"><strong>Training
																	Phase:</strong></li>
															
																
														</ul>
													</div>
													<cf:select path="trainingPhase1" class="form-control"
														>
														<cf:option value="0" label="Select Training Phase" />
														<cf:options items="${TrainingPhaseList}"
															itemValue="trainingPhaseId" itemLabel="trainingPhaseName" />
													</cf:select>
												</div>
                                            <div >
                                                
                                                <%--   <div class="form-group">
                                                    <div> 
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Chapter Name:</strong></li>
                                                           
                                                            <li class="style-li error-red">
                                                            <span id="name_status" class = "clear-label"> </span>
                                                            ${created }</li>
                                                        </ul>
                                                    </div>
												<cf:select path="unitCode1" class="form-control" onchange="getModule(this.value,'moduleCode1');">
													
													<cf:options items="${listUnitMaster}" itemLabel='unitName' itemValue="unitId" />
												</cf:select> 
											</div> --%>
                                                    
                                            </div> <!-- left side ends -->
                                              </div>
 										<div class="col-md-6 col-xs-12">
                                            <!-- right side -->
                                                   <div class="form-group">   
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Subject Name:</strong></li>
                                                            <li class="style-li error-red">
                                                            <span id="name_status" class = "clear-label"> </span>
                                                           <%-- ${created } --%></li>
                                                        </ul>
                                                    </div>
												<cf:select  path="subjectCode1"   class="form-control">
										<%-- 		 <cf:option value="0" label="Select Subject"></cf:option> --%>
												   <ct:forEach var="twofields" items="${listSubjectMaster}">
												   <cf:option value="${twofields[0]}"><ct:out value="${twofields[10]} - ${twofields[11]}"/></cf:option>
    												</ct:forEach>
												 </cf:select>
											</div>
											<div class="row">
                                                <div class="col-md-6 col-xs-12"></div>
                                                
                                                <div class="col-md-6 col-xs-12">
                                                <input type="button" onclick="getQuestions();"  class="btn login-btn" value="Search">                                                         </div>
                                            </div>
                                             </div> <!-- rigth side ends -->
                                            <!-- button -->
                                            
                                         
                                        </div>

                                       
                                    </div>
                                </fieldset>
                                </div>
                                

                                <!-- search Results -->
                                <div class="col-xs-12">
                                    
                                    <!-- table -->
                                    <div class="row">
                                        
                                        <!-- question list -->
                                        <div class="col-xs-12 table-overflow-responsive">
                                        
                                            <fieldset>
                                                <legend>Question List</legend>
                                                
                                                <table style='text-align:center;' id="newTable" class="table table-responsive table-bordered table-hover">
                                                        <thead>

                                                            <tr class="background-open-vacancies">
 <!--                                                                <th class="text-center"><input type="checkbox"> </th>
                                                                <th>Course Code</th>
                                                                <th>Question Number</th>
  -->                                                           </tr>

                                                        </thead>

                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                   
                                            </fieldset>
                                        </div>
                                        
                                        
                                        
                                        
                                        
                                        
                                    </div>
                                </div> <!-- search div ends -->
								 
								<div class="col-xs-12 table-overflow-responsive">
                                            <fieldset>
                                                <legend>Add/ Modify Questions</legend>
                                                
                                                <!-- left side -->
                                                <div class="col-md-6 col-xs-12">
                                                   <div class="form-group">
															<div>
																<ul class="lab-no">
																	<li class="style-li"><strong>Designation:</strong></li>
																	<li id="designationErr" style="display: none;"
																	class="style-li error-red">Select Designation.</li>
																</ul>
															</div>
															<cf:select path="designation2" class="form-control" onchange="func2();">
																<cf:option value="0" label="Select Designation" />
															 	<cf:options items="${DesignationList}" 
																	itemValue="designationId" itemLabel="designationName"  /> 
															</cf:select>
	
														</div>
													
												<div class="form-group">
													<div>
														<ul class="lab-no">
															<li class="style-li"><strong>Training Type:</strong></li>
															<li id="trainingTypeErr" style="display: none;"
																class="style-li error-red">Select Training Type.</li>
															
														</ul>
													</div>
													<cf:select path="trainingType2" class="form-control"
														onchange="func2();getTrainingPhase(this.value , 'trainingPhase2');">
														<cf:option value="0" label="Select Training Type" />
														<cf:options items="${TrainingTypeList}"
															itemValue="trainingTypeId" itemLabel="trainingTypeName" />
													</cf:select>
												</div>
												<div class="form-group"> 
													<div>
														<ul class="lab-no">
															<li class="style-li"><strong>Training
																	Phase:</strong></li>
															<li id="trainingPhaseErr" style="display: none;"
																class="style-li error-red">Select Training Phase.</li>
															
														</ul>
													</div>
													<cf:select path="trainingPhase2" class="form-control" onchange="func2();"
														>
														<cf:option value="0" label="Select Training Phase" />
														<cf:options items="${TrainingPhaseList}"
															itemValue="trainingPhaseId" itemLabel="trainingPhaseName" />
													</cf:select>
												</div>
                                                      <div class="form-group">  
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Subject Name:</strong></li>
                                                             <li id="subjectCodeErr" style="display:none;" class="style-li error-red" > Select Subject</li>
                                            
                                                            <li class="style-li error-red">
                                                            <span id="name_status" class = "clear-label"> </span>
                                                            <%-- ${created } --%></li>
                                                        </ul>
                                                    </div>
												<cf:select  path="subjectCode2"   class="form-control"  onchange="getQuestionNo(this.value);" >
												   	 <cf:option value="0" label="Select Subject"></cf:option>
												   <ct:forEach var="twofields" items="${listSubjectMaster}">
       												 <cf:option value="${twofields[0]}"><ct:out value="${twofields[9]} - ${twofields[10]}"/></cf:option>
    												</ct:forEach>
												 </cf:select>
											</div> 
                                                <%--       <div class="form-group">
                                                    <div>
                                                        <ul class="lab-no">
                                                            <li class="style-li"><strong>Chapter Name:</strong></li>
                                                             <li id="unitCodeErr" style="display:none;" class="style-li error-red" > Select Chapter Name</li>
                                            
                                                            <li class="style-li error-red">
                                                            <span id="name_status" class = "clear-label"> </span>
                                                            ${created }</li>
                                                        </ul>
                                                    </div>
												<cf:select  path="unitCode2" class="form-control" onchange="getModule(this.value,'moduleCode2')" >
													
													<cf:options items="${listUnitMaster}" itemLabel="unitCode" itemValue="unitId" />
												</cf:select> 
											</div> --%>
                                                     
                                                    
                                                    
                                                    
                                                    
                                                </div>
                                                
                                                <!-- right side -->
                                                <div class="col-md-6 col-xs-12">
                                                
                                                <%--     <div class="form-group">
                                                        <div>
                                                            <ul class="lab-no">
                                                                <li class="style-li"><strong>Help Text:</strong></li>
                                                                <li id="questionHintErr" style="display:none;" class="style-li error-red" > Enter Help Text</li>
                                               
                                                                <li class="style-li error-red"> </li>
                                                            </ul>
                                                        </div>
                                                        <cf:input path="questionHint" class="form-control" placeholder="Help Text" />
                                                    </div>   --%>   
                                                    
                                                    <div class="form-group">
                                                        <div>
                                                            <ul class="lab-no">
                                                                <li class="style-li"><strong>Question Number:</strong></li>
                                                                  <li id="questionNumberErr" style="display:none;" class="style-li error-red" > Enter Question number</li>
                                            
                                                                <li class="style-li error-red"> </li>
                                                            </ul> 
                                                        </div>
                                                        <input type="text" id="dispQuestionNumber" class="form-control" disabled='disabled'>
                                                      <cf:input type="hidden" path="questionNumber" class="form-control" placeholder="Question Number" />
                                                    </div> 
                                                    
                                                    <div class="form-group">
                                                        <div>
                                                            <ul class="lab-no"> 
                                                                <li class="style-li"><strong>Question:</strong></li>
                                                                  <li id="questionTitleErr" style="display:none;" class="style-li error-red" > Enter Question Title</li>
                                            
                                                                <li class="style-li error-red"> </li>
                                                            </ul>
                                                        </div>
                                                      <cf:input path="questionTitle" class="form-control" placeholder="Question Title" />
                                                    </div>
                                                  
                                                    
                                                    <div class="form-group">
                                                        <div>
                                                            <ul class="lab-no">
                                                                <li class="style-li"><strong>Number of Options:</strong></li>
                                                                <li id="noOfAssesmentQuesErr" style="display:none;" class="style-li error-red" >Select number of options</li>
                                                                
                                                                <li class="style-li error-red"> </li>
                                                            </ul>
                                                        </div>
                                                        <cf:select id="noOfAssesmentQues" onchange="generateAnsSeq();" path="noOfOption" class="form-control">
                                                            <cf:option value="0" label="Please Select" />
                                                           <%--  <cf:option value="1" label="1" /> --%>
                                                             <cf:option value="2" label="2" />
                                                             <cf:option value="3" label="3" />
                                                              <cf:option value="4" label="4" />
                                                               <cf:option value="5" label="5" />
                                                                <cf:option value="6" label="6" />
                                                        </cf:select>
                                                    </div>     
                                                    
                                                </div>
                                                
                                                    <br />
                                                    <br />
                                                <div class="col-md-6 col-xs-12">
                                                 <fieldset>  <legend><h3>Options</h3></legend>
                                                    <div id="assAnsTableErr" style="display:none;" class="style-li error-red" >Fill all the options</div>
                                                                
                                                    <table id="assAnsTable"  style='text-align:center;' class="table table-bordered table-responsive">
                                                <!--     <thead>
                                                        <tr class="background-open-vacancies">
                                                            <th>S.No.</th>
                                                            <th>Options</th>
                                                        </tr>
                                                    </thead> -->
                                                    <tbody></tbody>
                                                </table>
                                                    </fieldset>
                                                    <br />
                                                    <br />
                                                    
                                                      <div class="form-group">
                                                        <div>
                                                            <ul class="lab-no">
                                                                <li class="style-li"><strong>Answer Number:</strong></li>
                                                                  <li id="correctAnswerErr" style="display:none;" class="style-li error-red" >Invalid Answer number</li>
                                                                <li class="style-li error-red"> </li>
                                                            </ul>
                                                        </div>
                                                        <cf:input id="correctAnswer" path="correctAnswer" onblur="return answerno(this.id,this.value);" onkeyup="allnumeric(this.id,this.value);" class="form-control" placeholder="Answer Number" />
                                                        
                                                    </div>
                                                    <cf:input type="hidden" path="id" class="form-control" placeholder="Help Text" />
                                                    <button onClick="return validateFields();" class="btn login-btn pull-right">Save</button>
                                                </div> 
                                                
                                                <div class="col-md-6 col-xs-12">
                                                  
                                                
                                                </div>
                                            </fieldset>
                                            <br><br>
                                        </div>
								
                            </div><!-- row ends -->
                    </div>
                </div>
            </div>
        </div>
    </section>
    
   
    </cf:form>
    
    