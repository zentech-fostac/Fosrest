
package com.ir.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.CascadeType;
import javax.validation.constraints.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



/**
 * @author Jyoti Mekal
 *
 * New Table for Unit Master
 */
@Entity
@Table(name="SubjectMaster")
public class SubjectMaster {
	
/*	@Override
	public String toString() {
		return "ModuleMaster [moduleId=" + moduleId + ", moduleName="
				+ moduleName + ", status=" + status + ", unitMaster="
				+ unitMaster + "]";
	}


	@Override
	public String toString() {
		return moduleName ;
	}*/
	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name= "subjectId")
	private int subjectId;
	private String subjectName;
	private String status;
	private String contentType;
	private String contentName;
	private String contentLink;
	private String subjectCode;
	private int eligibility;
	
	@Column(columnDefinition="int default 0")
	private int seqNo;
	
	@Column(name="isActive", columnDefinition="character varying(10) default 'Y'")
	private String isActive;
	
	
	public int getEligibility() {
		return eligibility;
	}
	public void setEligibility(int eligibility) {
		this.eligibility = eligibility;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@OneToOne   @JoinColumn(name="unitId")
	private UnitMaster unitMaster;
	
	

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}



	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}



	public SubjectMaster() {
		super();
		// TODO Auto-generated constructor stub
	}



	

	
	



	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getContentType() {
		return contentType;
	}



	public void setContentType(String contentType) {
		this.contentType = contentType;
	}



	public String getContentName() {
		return contentName;
	}



	public void setContentName(String contentName) {
		this.contentName = contentName;
	}



	public String getContentLink() {
		return contentLink;
	}



	public void setContentLink(String contentLink) {
		this.contentLink = contentLink;
	}



	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;


	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getModifyDate() {
		return modifyDate;
	}



	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}



	



	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public int getSeqNo() {
		return seqNo;
	}



	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}





	
 
}
