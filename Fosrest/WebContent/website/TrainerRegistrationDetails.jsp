<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <link href="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.0.3/css/bootstrap.min.css"
        rel="stylesheet" type="text/css" />

    <link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
        rel="stylesheet" type="text/css" />
   -->

<script src="website/js/commonController.js"></script>


<script>
	function DrawCaptcha() {
		var a = Math.ceil(Math.random() * 7) + '';
		var b = Math.ceil(Math.random() * 7) + '';
		var c = Math.ceil(Math.random() * 7) + '';
		var d = Math.ceil(Math.random() * 7) + '';
		var e = Math.ceil(Math.random() * 7) + '';
		var f = Math.ceil(Math.random() * 7) + '';
		var code = a + ' ' + b + ' ' + c + ' ' + d + ' ' + e + ' ' + f;
		document.getElementById("txtCaptcha").value = code
	}

	function OnStart() {


		/* if(c!=4){
			$("#sa").css("display", "none");
			$("#on").css("display", "none");
			$("#tc").css("display", "none");
		}  */
		// $("#otherTrainingInstitute").css("display" , "none");
		var isUpdate = '${isUpdate}';
		if (isUpdate != null && isUpdate == "Y") {

			var name = '${PersonalInformationTrainer.firstName}';

			$("#logId").val('${PersonalInformationTrainer.loginDetails.id}');
			$("#status").val('${PersonalInformationTrainer.status}');
			$("#correspondenceState").val(
					'${PersonalInformationTrainer.correspondenceState}');
			$("#correspondenceState").trigger("change");
			window.setTimeout(
							function() {
								$("#correspondenceDistrict").val('${PersonalInformationTrainer.correspondenceDistrict}');
								var ss = '${PersonalInformationTrainer.correspondenceDistrict}';
								$("#correspondenceDistrict").trigger("change");
								window.setTimeout(function() {
													$("#correspondenceCity").val('${PersonalInformationTrainer.correspondenceCity}');
												}, 1000);
							}, 1000);
			
			
			$("#resState").val('${PersonalInformationTrainer.resState}');
	        $("#resState").trigger("change");
	        window.setTimeout(function() {
	        	$("#residentialDistrict").val('${PersonalInformationTrainer.residentialDistrict}');
	            $("#residentialDistrict").trigger("change");
	            window.setTimeout(function() {
	            	$("#resCity").val('${PersonalInformationTrainer.resCity}');
	            }, 1000);
	        }, 1000);   

			/* $("#resState").val('');
			$("#residentialDistrict").val('');
			$("#resCity").val('');
			$("#ResidentialLine1").val('');
			$("#ResidentialLine2").val(''); */
			$("#createUpdateBtn").val("Update");
			$("#captcha").css("display", "none");
			$("#chkunit").css("display", "none");
			$("#check").attr("checked", "checked");

			}
		DrawCaptcha();

		flatpickr("#dob", {});
		$('#sameAddr')
				.change(
						function() {
							if (this.checked) {
								$("#resState").val(
										$("#correspondenceState").val());
								$("#resState").trigger("change");
								window
										.setTimeout(
												function() {
													$("#residentialDistrict")
															.val(
																	$(
																			"#correspondenceDistrict")
																			.val());
													$("#residentialDistrict")
															.trigger("change");
													window
															.setTimeout(
																	function() {
																		$(
																				"#resCity")
																				.val(
																						$(
																								"#correspondenceCity")
																								.val());
																	}, 1000);
												}, 1000);

								$("#resPincode").val(
										$("#correspondencePincode").val());
								$("#ResidentialLine1").val(
										$("#correspondenceAddress1").val());
								$("#ResidentialLine2").val(
										$("#correspondenceAddress2").val())
							} else {
								$("#resState").val('');
								$("#residentialDistrict").val('');
								$("#resCity").val('');
								$("#resPincode").val('');
								$("#ResidentialLine1").val('');
								$("#ResidentialLine2").val('');

							}

						});
	

	}
	window.onload = OnStart;
	// Remove the spaces from the entered and generated code
	function removeSpaces(string) {
		return string.split(' ').join('');
	}
</script>


<cf:form action="PersonalInformationTrainerAdd.fssai" name="myForm"
	method="POST" commandName="PersonalInformationTrainer"
	onsubmit="return validateFields();">

	<section>
		<div class="container">
			<div class="row mar-top-aadhar">
				<div class="col-md-1 col-xs-12"></div>

				<div class="col-md-10  col-xs-12">
					<h3 class="text-capitalize heading-3-padding">Trainer
						Registration Form</h3>

					<form>

						<!-- personal information -->
						<fieldset>
							<legend>Personal Information</legend>

							<!-- form field starts here -->



							<!-- left side -->
							<div class="col-md-6 col-xs-12">


								<cf:input path="logId" type="hidden" />
								<cf:input path="status" type="hidden" />
								<cf:input type="hidden" path="id" />
							

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Aadhar Number:</strong></li>

											<li class="style-li error-red">*</li>
											<li id="AadharNumberErr" style="display: none;"
												class="style-li error-red">Please Enter Aadhar No.</li>
												<li id="aadharNumberErr2" style="display: none;"
																	class="style-li error-red">Aadhar No. should be 12 digit</li>
											<li class="style-li error-red"><span id="aadhar_status"></span>
										</ul>
									</div>
									<cf:input type="text" path="AadharNumber" class="form-control"
										maxlength="12" placeholder="Aadhar Number"
										onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')"
										onblur="ck_aadhar('personalinformationtrainer');" />
								</div>
								<%-- <div class="form-group" id="on">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Organization Name:</strong></li>
											<li class="style-li error-red">*</li>
											

											<li id="s1" style="display: none;" class="style-li error-red">
												Organization Name can not be blank.</li>

										</ul>
									</div>

									<cf:input type="text" path="orgName" class="form-control"
										placeholder="Organization Name" required="" />
								</div> --%>
								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Emp Id:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="empIDErr" style="display: none;"
												class="style-li error-red">Please Enter Emp ID.</li>
										</ul>
									</div>
									<cf:input type="text" path="empID" class="form-control"
										placeholder="Emp ID" />
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Date of Birth:</strong></li>
											<li id="dobErr" style="display: none;"
												class="style-li error-red">Please Select DOB.</li>
											<li class="style-li error-red">*</li>
										</ul>
									</div>
									<cf:input type="text" path="dob" id="dob" name="dob"
										class="form-control" />
								</div>



								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Gender:</strong></li>

											<li class="style-li error-red">*</li>
										</ul>
									</div>
									<cf:radiobutton path="gender" value="M" checked="true" />
									Male&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<cf:radiobutton path="gender" value="F" />
									Female
								</div>



							</div>
							<!-- left side ends -->

							<!-- right side -->
							<div class="col-md-6 col-xs-12">

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Title:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="titleErr" style="display: none;"
												class="style-li error-red">Please Select Title.</li>

										</ul>
									</div>
									<cf:select path="title" class="form-control">
										<cf:option value="" label="Select Title" />
										<cf:options items="${titleMap}" />
									</cf:select>
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>First Name:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="firstNameErr" style="display: none;"
												class="style-li error-red">Please Enter First Name.</li>

										</ul>
									</div>
									<cf:input type="text" path="firstName" class="form-control"
										placeholder="First Name"
										onkeyup="if (/\d/g.test(this.value)) this.value = this.value.replace(/\d/g,'')" />
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Middle Name:</strong></li>
											<li class="style-li error-red"></li>
											<li id="MiddleNameErr" style="display: none;"
												class="style-li error-red">Please Enter Middle Name.</li>

										</ul>
									</div>
									<cf:input type="text" path="MiddleName" class="form-control"
										placeholder="Middle Name"
										onkeyup="if (/\d/g.test(this.value)) this.value = this.value.replace(/\d/g,'')" />
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Last Name:</strong></li>
											<li class="style-li error-red"></li>
											<li id="LastNameErr" style="display: none;"
												class="style-li error-red">Please Enter Last Name.</li>

										</ul>
									</div>
									<cf:input type="text" path="LastName" class="form-control"
										placeholder="Last Name"
										onkeyup="if (/\d/g.test(this.value)) this.value = this.value.replace(/\d/g,'')" />
								</div>
								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Father's Name/
													Mother's Name:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="FatherNameErr" style="display: none;"
												class="style-li error-red">Please Enter Father's Name.</li>

										</ul>
									</div>
									<cf:input type="text" path="FatherName" class="form-control"
										placeholder="Father's Name"
										onkeyup="if (/\d/g.test(this.value)) this.value = this.value.replace(/\d/g,'')" />
								</div>



							</div>
							<!-- right side ends -->
						</fieldset>



						<!-- Correspondence Address -->
						<fieldset>
							<legend>Correspondence Address</legend>

							<!-- form field starts here -->

							<!-- left side -->
							<div class="col-md-6 col-xs-12">

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Correspondence
													Address Line 1:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="correspondenceAddress1Err" style="display: none;"
												class="style-li error-red">Please Enter Address.</li>

										</ul>
									</div>
									<cf:input type="text" path="correspondenceAddress1"
										class="form-control" placeholder="Address" required="" />
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Correspondence
													Address Line 2:</strong></li>
											<li class="style-li error-red"></li>
										</ul>
									</div>
									<cf:input type="text" path="correspondenceAddress2"
										class="form-control" placeholder="Address" required="" />
								</div>




								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>State:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="correspondenceStateErr" style="display: none;"
												class="style-li error-red">Please Select State.</li>

										</ul>
									</div>
									<cf:select path="correspondenceState" class="form-control"
										onchange="getDistrict(this.value , 'correspondenceDistrict')">
										<cf:option value="0" label="Select state Name" />
										<cf:options items="${listStateMaster}" itemValue="stateId"
											itemLabel="stateName" />
									</cf:select>
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Email:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="EmailErr" style="display: none;"
												class="style-li error-red">Please Enter Valid Email ID.</li>

										</ul>
									</div>
									<cf:input type="text" path="Email" class="form-control"
										placeholder="Email" onblur="emailVal(this.id,this.value);return false;"
										required="" />

								</div>

							</div>
							<!-- left side ends -->

							<!-- right side -->
							<div class="col-md-6 col-xs-12">

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>District:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="correspondenceDistrictErr" style="display: none;"
												class="style-li error-red">Please Select District.</li>

										</ul>
									</div>
									<cf:select path="correspondenceDistrict" class="form-control"
										onchange="getCity(this.value , 'correspondenceCity')">

									</cf:select>
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>City:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="correspondenceCityErr" style="display: none;"
												class="style-li error-red">Please Select City.</li>

										</ul>
									</div>
									<cf:select path="correspondenceCity" class="form-control">

									</cf:select>
								</div>



								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Pin Code:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="correspondencePincodeErr" style="display: none;"
												class="style-li error-red">Please Enter Pin Code.</li>
											<li id="correspondencePincodeErr1" style="display: none;"
												class="style-li error-red">Please Enter valid Pin Code.</li>
										</ul>
									</div>
									<cf:input type="text" path="correspondencePincode"
										class="form-control" minlength="6" maxlength="6"
										placeholder="Pin Code" required=""
										onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')" />
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Mobile:</strong></li>
											<li class="style-li error-red">*</li>
											<li id="mobileErr" style="display: none;"
												class="style-li error-red">Mobile cannot be blank</li>
										</ul>
									</div>
									<cf:input type="text" path="mobile" class="form-control"
										placeholder="Mobile Number" minlength="10" maxlength="10"
										required=""
										onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')" />
								</div>


							</div>
							<!-- right side ends -->
						</fieldset>
						<!-- Correspondence address ends -->


						<!-- permanent Address -->
						<fieldset>
							<legend>Permanent Address</legend>

							<!-- form field starts here -->

							<div class="col-xs-12">
								<label class="checkbox-inline"> <input id="sameAddr"
									type="checkbox" />Is your permanent address same as
									correspondence address.
								</label>

							</div>

							<!-- left side -->
							<div class="col-md-6 col-xs-12">

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Permanent Address
													Line 1:</strong></li>
											<li class="style-li error-red"></li>
											<li id="ResidentialLine1Err" style="display: none;"
												class="style-li error-red">Permanent Address can
												not be blank.</li>

										</ul>
									</div>
									<cf:input type="text" path="ResidentialLine1"
										class="form-control" placeholder="Address" required="" />
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Permanent Address
													Line 2:</strong></li>


										</ul>
									</div>
									<cf:input type="text" path="ResidentialLine2"
										class="form-control" placeholder="Address" required="" />
								</div>




								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>State:</strong></li>
											<li class="style-li error-red"></li>
											<li id="resStateErr" style="display: none;"
												class="style-li error-red">State can not be blank.</li>
										</ul>
									</div>
									<cf:select path="resState" class="form-control"
										onchange="getDistrict(this.value , 'residentialDistrict')">
										<cf:option value="0" label="Select state Name" />
										<cf:options items="${listStateMaster}" itemValue="stateId"
											itemLabel="stateName" />
									</cf:select>
								</div>


							</div>
							<!-- left side ends -->

							<!-- right side -->
							<div class="col-md-6 col-xs-12">

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>District:</strong></li>
											<li class="style-li error-red"></li>
											<li id="residentialDistrictErr" style="display: none;"
												class="style-li error-red">District can not be blank.</li>
										</ul>
									</div>
									<cf:select path="residentialDistrict" class="form-control"
										onchange="getCity(this.value , 'resCity')">

									</cf:select>
								</div>

								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>City:</strong></li>
											<li class="style-li error-red"></li>
											<li id="resCityErr" style="display: none;"
												class="style-li error-red">City can not be blank.</li>
										</ul>
									</div>
									<cf:select path="resCity" class="form-control">

									</cf:select>
								</div>



								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Pin Code:</strong></li>
											<li class="style-li error-red"></li>
											<li id="resPincodeErr" style="display: none;"
												class="style-li error-red">Pin code can not be blank.</li>
										</ul>
									</div>
									<cf:input type="text" path="resPincode" class="form-control"
										minlength="6" maxlength="6" placeholder="Pin Code" required=""
										onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')" />
								</div>
							</div>
							<!-- right side ends -->
						</fieldset>
						<!-- Permanent address ends -->

						<!-- Experience starts -->

						<fieldset>
							<legend>Experience Details </legend>
							<!-- left side -->
							<div class="col-md-6 col-xs-12">
								<div class="form-group">
								<div>
										<ul class="lab-no">
											<li class="style-li"><strong>Languages in which
													trainer wish to conduct training:</strong></li>
											<li class="style-li error-red"></li>
											 <li id="languagesErr" style="display: none;"
												class="style-li error-red">Language can not be blank.</li>
										</ul>
									</div>
									<cf:select path="languages" class="form-control">
										 <cf:option value="0" label="Select Language" />
										<cf:options items="${LanguagesList}" itemValue="languageId"
											itemLabel="languageName" />
										<%-- <cf:option value="0" label="Others" /> --%>
									</cf:select>
								</div>

								<!-- modal -->
								<!-- <div class="container"> -->

									<!-- Trigger the modal with a button -->
									<!--  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Select State</button> -->
									<div class="col-md-6 col-xs-12" >
									
									<!-- 	<a href="#" data-toggle="modal" data-target="#myModal" style="margin-left: -13px;"><b>Select
												State:</b></a> -->
<button type="button" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#myModal" onclick="statecheckbox(); return false;">Select States</button>
									</div>

									<!-- Modal -->
							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">All States</h4>
										</div>
									<div class="modal-body">

							<!-- table -->
							<div class="row">
								<div class="col-xs-12">
								<table class="table table-bordered table-responsive"
												id="testTable">
												<thead>
													<tr class="background-open-vacancies">

														<th>S.No.</th>
														<th>State Id</th>
														<th>State Name</th>
														 <th>Enroll States</th>
													</tr>
												</thead>
												<ct:forEach items="${listStateMaster}" var="listStateMaster"
													varStatus="loop">
													<tr>
													<td>${loop.count}</td>
													<td>${listStateMaster.stateId}</td>
													<td id="userName_${loop.index}">${listStateMaster.stateName}
													</td>
												
													<td class="text-center" ><input type="checkbox" id="statecheck_${listStateMaster.stateId}" onclick='selectStates(${listStateMaster.stateId})'>
													</td>


													</tr>
												</ct:forEach>

											</table>
											
											
								</div>
							</div>
						</div>
								<div class="modal-footer">
											<div>
												<input type="button"
													class="btn login-btn show-details-vacancy collapsed"
													data-toggle="collapse" data-target="#show-result"
													aria-expanded="false" data-dismiss="modal" onclick="setAllStates();"  value="OK" />

											</div>
										</div>
</div>
									</div>
								</div>
							
<br>
								</div>
								
							<!-- </div> -->
							<div class="col-md-6 col-xs-12">
								<div class="form-group">
									<label>Associated with any Training Institute ?</label> <br>
									<!-- <label class="radio-inline"> <input type="radio"
										name="optradio" checked="checked" /> Yes
									</label> <label class="radio-inline"> <input type="radio"
										name="optradio" /> No
									</label> -->
								</div>
									<cf:radiobutton  path="radioTrainingInstitute" value="Y" checked="true"/>
									Yes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<cf:radiobutton  path="radioTrainingInstitute" value="N" />
									No
								<div class="form-group">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong>If Yes Training
													Institute Name:</strong></li>
											<li class="style-li error-red"></li>

											<li id="AssociatedWithAnyTrainingInstituteErr"
												style="display: none;" class="style-li error-red">Select
												the associated Training Institute.</li>

										</ul>
									</div>

									<cf:select path="AssociatedWithAnyTrainingInstitute"
										class="form-control" onchange="OtiHide();">
										<%-- <cf:option value="0" label="Select Training Institude" /> --%>
										<cf:option value="" label="Select Training Institute" />
										<cf:options items="${listTrainingInstitude}" itemValue="id"
											itemLabel="trainingCenterName" />
										<cf:option value="0" label="Others" />
									</cf:select>
								</div>
								<div class="form-group" id="Oti">
									<div>
										<ul class="lab-no">
											<li class="style-li"><strong> Training
													Institute Name:</strong></li>
											<li class="style-li error-red"></li>
											<li id="otherTrainingInstituteErr" style="display: none;"
												class="style-li error-red">Training Institute Name can
												not be blank.</li>


										</ul>
									</div>

									<cf:input type="text" path="otherTrainingInstitute"
										class="form-control" placeholder="Training Institute Name:"
										required="" />
								</div>
</div>
							
							<!-- right side ends -->
						</fieldset> 
						<!-- Experience end -->

						<!-- captcha -->
						<fieldset id="captcha">
							<div
								style="width: 95%; margin-left: 32px; float: left; height: 100px; border: 1px solid #cecece;"
								class="form-group">
								<div style="float: left">
									<div style="float: left; width: 98%;">
										<label id="captchaError"
											style="float: left; width: 99%; font-family: Calibri; margin-left: 0px;">Please
											enter captcha in below textbox !!!</label>
									</div>

									<div style="float: left; width: 99%;">

										<input type="text" id="txtCaptcha"
											style="background-image: url(1.jpg); text-align: center; border: none; width: 140px; margin-left: 8px; font-weight: bold; font-family: Modern"
											disabled="disabled" /> <input type="button" id="btnrefresh"
											value="Refresh" onclick="DrawCaptcha();" />
										<div id="txtInputErr" style="display: none;"
											class="style-li error-red">Captcha is required.</div>
										<input type="text" id="txtInput" placeholder="Captcha"
											style="width: 140px;" />

									</div>
								</div>
								<div id=chkunit style="float: left; width: 99%;">
									<input type="checkbox" id="check" style="margin-left: 1%;">
									<!-- <a href="#" target="_blank" class="terms-font-size">  -->
									I have read and understood the Terms & Conditions and the
									Privacy Policy of FSSAI.
									<!-- </a> -->
								</div>
							</div>
						</fieldset>
						<!-- button -->

						<div class="row">
							<div class="col-md-4 col-xs-12"></div>
							<div class="col-md-4 col-xs-12">
								<input type="submit" style="width: 100%;" class="btn login-btn"
									id="createUpdateBtn" value="Register" />
							</div>
							<div class="col-md-4 col-xs-12"></div>
						</div>


		<cf:hidden path="trainingState" value="" />
					</form>
					<!-- form ends -->

					</div>


					<div class="col-md-1 col-xs-12"></div>
				</div>
			</div>
	</section>


	<div class="col-md-2 hidden-xs"></div>
</cf:form>






<script>
	function editTrainingSchedule(id) {

		var name1 = JSON.stringify({
			courseName : 0
		})
		$.ajax({
			type : 'post',
			url : 'PersonalInformationTrainer/edit/' + id + '.fssai',
			contentType : "application/json",
			data : name1,
			success : function(response) {
				var mainData1 = jQuery.parseJSON(response);
				//  $("#trainingScheduleId").val(mainData1.trainingScheduleId);
				//$("#userType").val(mainData1.userType);
				$("#title").val(mainData1.title);
				$("#AadharNumber").val(mainData1.AadharNumber);
				$("#firstName").val(mainData1.firstName);
				$("#MiddleName").val(mainData1.MiddleName);
				$("#LastName").val(mainData1.LastName);
				$("#FatherName").val(mainData1.FatherName);
				$("#dob").val(mainData1.dob);

				$("#correspondenceAddress1").val(
						mainData1.correspondenceAddress1);
				$("#correspondenceState").val(mainData1.correspondenceState);
				$("#Email").val(mainData1.Email);
				/* $("#correspondenceDistrict").val(
						mainData1.correspondenceDistrict);

				$("#correspondenceCity").val(mainData1.correspondenceCity); */
				$("#correspondencePincode")
						.val(mainData1.correspondencePincode);
				$("#mobile").val(mainData1.mobile);
			
				$("#updatebtn").css("display", "block");

				$("#createbtn").css("display", "none");
			}
		});

	}
	function OtiHide() {
		var a = document.getElementById('AssociatedWithAnyTrainingInstitute').value;
		if (a == 0) {
			$("#Oti").css("display", "block");
		} else {
			$("#Oti").css("display", "none");
		}
	}
	function validateFields() {
	
		var isUpdate = '${isUpdate}';

		//$("#userTypeErr").css("display", "none");
		$("#titleErr").css("display", "none");
		$("#correspondencePincodeErr").css("display", "none");
		$("#correspondencePincodeErr1").css("display", "none");
		$("#mobileErr").css("display", "none");
		$("#AadharNumberErr").css("display", "none");
		$("#firstNameErr").css("display", "none");
		//$("#MiddleNameErr").css("display", "none");
		$("#LastNameErr").css("display", "none");
		$("#FatherNameErr").css("display", "none");
		$("#empIDErr").css("display", "none");
		$("#dobErr").css("display", "none");
		$("#correspondenceAddress1Err").css("display", "none");
		$("#correspondenceStateErr").css("display", "none");
		$("#EmailErr").css("display", "none");
		$("#correspondenceDistrictErr").css("display", "none");
		$("#correspondenceCityErr").css("display", "none");
		$("#ResidentialLine1Err").css("display", "none");
		$("#ResidentialLine2Err").css("display", "none");
		$("#resStateErr").css("display", "none");
		$("#residentialDistrictErr").css("display", "none");
		$("#resCityErr").css("display", "none");
		$("#resPincodeErr").css("display", "none");
		$("#txtInputErr").css("display", "none");

		$("#sessWishToConductErr").css("display", "none");
		$("#expInYearErr").css("display", "none");
		$("#expInMonthErr").css("display", "none");
		$("#otherTrainingInstituteErr").css("display", "none");
		$("#s1").css("display", "none");
		$("#languagesErr").css("display" , "none");
		$("#state").css("display", "none");
		 $("#aadharNumberErr2").css("display" , "none");
		//$("#AssociatedWithAnyTrainingInstituteErr").css("display" , "none"); 

		 if ($("#AadharNumber").val() == 0) {

				$("#AadharNumberErr").css("display", "block");
				return false;
			}
			if($("#AadharNumber").val().match(/^[0-9]{12}$/) == null){
	   		 $("#aadharNumberErr2").css("display" , "block");
	    		 return false;
	      	 }
		
		if ($("#title").val() == 0) {

			$("#titleErr").css("display", "block");
			return false;
		}

		
		/* if ($("#orgName").val() == 0) {

			$("#s1").css("display", "block");
			return false;
		}
 */
		if ($("#firstName").val() == 0) {

			$("#firstNameErr").css("display", "block");
			return false;
		}

		if ($("#LastName").val() == 0) {

			$("#LastNameErr").css("display", "block");
			return false;
		}
		if ($("#FatherName").val() == 0) {

			$("#FatherNameErr").css("display", "block");
			return false;
		}
		if ($("#empID").val() == 0) {

			$("#empIDErr").css("display", "block");
			return false;
		}
		if ($("#dob").val() == 0) {

			$("#dobErr").css("display", "block");
			return false;
		}
		if ($("#correspondenceAddress1").val() == 0) {

			$("#correspondenceAddress1Err").css("display", "block");
			return false;
		}
		if ($("#correspondenceState").val() == 0) {

			$("#correspondenceStateErr").css("display", "block");
			return false;
		}
		if ($("#Email").val() == 0) {

			$("#EmailErr").css("display", "block");
			return false;
		}
		if ($("#correspondenceDistrict").val() == 0) {

			$("#correspondenceDistrictErr").css("display", "block");
			return false;
		}
		if ($("#correspondenceCity").val() == 0) {

			$("#correspondenceCityErr").css("display", "block");
			return false;
		}

		if ($("#correspondencePincode").val() == '') {

			$("#correspondencePincodeErr").css("display", "block");
			return false;

		}
		if (($("#correspondencePincode").val()).match(/^[0-9]{6}$/) == null) {

			$("#correspondencePincodeErr1").css("display", "block");
			return false;
		}

		if ($("#mobile").val().match(/^[0-9]{10}$/) == null) {

			$("#mobileErr").css("display", "block");
			return false;
		}
		if ($("#ResidentialLine1").val() == '') {
			$("#ResidentialLine1Err").css("display", "block");
			return false;
		}

		if ($("#resState").val() == 0) {
			$("#resStateErr").css("display", "block");
			return false;
		}
		if ($("#residentialDistrict").val() == 0) {
			$("#residentialDistrictErr").css("display", "block");
			return false;
		}

		if ($("#resCity").val() == 0) {
			$("#resCityErr").css("display", "block");
			return false;
		}
		 if ($("#resPincode").val().match(/^[0-9]{6}$/) == null) {
			$("#resPincodeErr").css("display", "block");
			return false;
		} 
		 if($("#languages").val() == 0){
			 $("#languagesErr").css("display" , "block");
			return false;
		 }
			if($("#testTable").find('input[type=checkbox]:checked').length == 0)
			{
			    alert('Please select atleast one checkbox');
			     return false;
			} 
			
		 /* if(isUpdate=='Y'){
			 return true;
		 }
		 else if ($("#stateBox").val()== 0) {
				$("#state").css("display", "block");
				return false;
			}  */
		if ($("#expInYear").val() == 0) {
			$("#expInYearErr").css("display", "block");
			return false;
		}
		if ($("#expInMonth").val() == 0) {
			$("#expInMonthErr").css("display", "block");
			return false;
		}

		if ($("#sessWishToConduct").val() < 1) {
			$("#sessWishToConductErr").css("display", "block");
			return false;

		}
		
		/* if($("#otherTrainingInstitute").val() ==''){
			 $("#otherTrainingInstituteErr").css("display" , "block");
				return false;
			 
		 }  */
		   
		 
		if (!(isUpdate != null && isUpdate == "Y")) {

			if ($("#txtInput").val() == '') {
				$("#txtInputErr").css("display", "block");
				return false;
			}
			var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
			var str2 = removeSpaces(document.getElementById('txtInput').value);
			if (!(str1 == str2)) {
				alert("Please Enter correct captcha");
				document.getElementById('txtInput').value = "";
				return false;
			}

		}
		 
		if ($("#check").is(":checked") == false) {
			alert("check the checkbox to agree to term and conditions");
			return false;
		}

	}
</script>
<script>

	
	 </script>
	 <script>
	 var isUpdate = '${isUpdate}';
	window.stateArray = [];
	
		function statecheckbox(){
			
			if(window.stateArray.length>1){
				for(var i = 0 ; i < window.stateArray.length ; i++){
					$("#statecheck_"+window.stateArray[i]).prop("checked" , true);
				}
			}
			
		}
	 function selectStates(stateId){
		 console.log(stateId);
		 var ischecked = document.getElementById("statecheck_"+stateId).checked;
		 console.log(ischecked);
		if(document.getElementById("statecheck_"+stateId).checked){
			window.stateArray.push(stateId);
		}else if(!document.getElementById("statecheck_"+stateId).checked){
			 var index = window.stateArray.indexOf(stateId);
			if(index > -1){
				window.stateArray.splice(index , 1);	
			} 	
		}
		 
		 
	 }
	 
	 function setAllStates(){
		 $('#trainingState').val(window.stateArray);	
		
	 } 
	 
	 

</script>

