package com.ir.form.common;

import java.util.List;

public class AssessmentEvaluationForm {

	/**Course name for which the assessment has been processing */
	//public int moduleId;
	private List<Integer> subjectId;
	/**List of questions id */
	public List<Integer> assessmentQuestionsList;


	

	public List<Integer> getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(List<Integer> subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the assessmentQuestionsList
	 */
	public List<Integer> getAssessmentQuestionsList() {
		return assessmentQuestionsList;
	}

	/**
	 * @param assessmentQuestionsList the assessmentQuestionsList to set
	 */
	public void setAssessmentQuestionsList(List<Integer> assessmentQuestionsList) {
		this.assessmentQuestionsList = assessmentQuestionsList;
	}
	
}
