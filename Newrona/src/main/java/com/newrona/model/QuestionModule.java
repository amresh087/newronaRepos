package com.newrona.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.newrona.util.Utility;

@Entity
@Table(name = "t_question_module")
public class QuestionModule implements Serializable
{	
	private static final long serialVersionUID = 1013458709370254509L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	// uni-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "f_question_id", referencedColumnName = "f_id")
	private Question question;

	@Column(name = "f_module_url")
	private String moduleUrl;
	
	@Column(name = "f_status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate")
	private Date modifiedDate = new Date();
	
	@Column(name="f_dateTimeZone")
   	private String dateTimeZone=Utility.convertDateToStringWithZone(new Date()); 
	
    public String getDateTimeZone()
	{
		return dateTimeZone;
	}

	public void setDateTimeZone(String dateTimeZone)
	{
		this.dateTimeZone = dateTimeZone;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public String getModuleUrl()
	{
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl)
	{
		this.moduleUrl = moduleUrl;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Date getInsertDate()
	{
		return insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

}