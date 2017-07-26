<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <ct:url var="addAction" value="/PersonalInformationTrainer.fssai" ></ct:url> --%>
<%--  <cf:form action="PersonalInformationTrainer.fssai" name="myForm" method="POST" commandName="TrainerForm" > --%>
 
 <script>

	 function myFunction() {
		    var searchText = document.getElementById('myInput').value;
		    var targetTable = document.getElementById('datatablesfosrest');
		    var targetTableColCount;
		    for (var rowIndex = 0; rowIndex < targetTable.rows.length; rowIndex++) {
		        var rowData = '';

		        if (rowIndex == 0) {
		           targetTableColCount = targetTable.rows.item(rowIndex).cells.length;
		           continue; //do not execute further code for header row.
		        }
		        for (var colIndex = 0; colIndex < targetTableColCount; colIndex++) {
		        	rowData += targetTable.rows.item(rowIndex).cells.item(colIndex).innerText.toLowerCase();
		        	 
		        }
		        if (rowData.toLowerCase().indexOf(searchText) == -1)
		            targetTable.rows.item(rowIndex).style.display = 'none';
		        else
		            targetTable.rows.item(rowIndex).style.display = 'table-row';
		    }
		}
</script>
 
 
<section id="main-slider" class="no-margin">
  <div class="carousel-inner innerpage">
    <div class="container">
      <div class="row slide-margin">
        <div class="col-sm-12">
          <h1 class="animation animated-item-1" style="padding-bottom:10px;"><i>About Trainer



</i></h1>
        </div>
      </div>
    </div>
    <!--/.item--> 
    <!--/.item--> 
    <!--/.item--> 
  </div>
  <!--/.carousel-inner--> 
  <!--/.carousel--> 
</section>
<!--/#main-slider--> 
<!--/#feature-->
<section id="trainee">
 <div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="row">
        <h1 class="pull-left">About Trainer</h1><br><br><br><br>
        <p class="clearfix">The Food Safety and Standards Authority of India has been established under the Food Safety and Standards Act, 2006 as a statutory body for laying down science based standards for articles of food and regulating manufacturing, processing, distribution, sale and import of food so as to ensure safe and wholesome food for human consumption. Cases pending under PFA Act and other orders repealed by FSS Act, 2006 (Schedule 2). (Uploaded on: 05.10.2016).</p>
     <!-- <h2 class="pull-left">Specialized Trainer</h2>    -->         
      </div>
      <br>
     
        <div class="col-xs-12 " id="testt">

                                                <!-- table -->
                                                <div class="row">
                                                    <div class="col-xs-12">
                                                            <fieldset>
                                            <legend>View List of Empanelled Trainers</legend>
                                            <ct:if test="${!empty listTrainer}">
                                            <div>
                                            <span style="font-weight:bold;margin-left: 784px;">Search:</span>
                                            <input type="text" onkeyup="myFunction();this.value = this.value.toLowerCase();" id="myInput"  placeholder="Enter search text">
                                             </div>
                                            <table id="datatablesfosrest" class="table table-bordered table-responsive">
                                                <thead>
                                                    <tr class="background-open-vacancies">
                                                        <th>S.No.</th>
                                                        <th>First Name</th>
                                                        <th>Email</th>
                                                        <th>Contact No.</th>
                                                     <th>State</th> 
                                                        <!-- <th>Organization Name</th> -->
                                                       <!--  <th>No. of Trainings Conducted</th> -->
                                                    </tr> 
                                                </thead>
                                                
                                               	<ct:forEach items="${listTrainer}" var="listTrainer" varStatus="loop">
										
										
											<tr>
												<td>${loop.count}</td>
												<td>${listTrainer[0]}</td>
												<td>${listTrainer[1]}</td>
												<td>${listTrainer[2]}</td>
										 <td>${listTrainer[3]}</td>   
												<%-- <td>${listTrainer[8]}</td> --%>
												<%-- <td>${listTrainer[9]}</td> --%>
											</tr>
										</ct:forEach>
                                            </table>
                                           </ct:if>
                                        </fieldset>
                                                    </div>
                                                </div>
                                            </div>
                                            <strong>To be Fssai Trainer</strong>
                                          <a href="PersonalInformationTrainer.fssai?id=4">  <input type="button" id="applynow"
														value="Apply Now"  class="btn login-btn"/></a>
     
      </div>
    </div>
  </div>
</section>