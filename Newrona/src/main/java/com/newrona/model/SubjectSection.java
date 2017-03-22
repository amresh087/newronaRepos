package com.newrona.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.newrona.util.Utility;

@Entity
@Table(name = "t_subject_section")
@FilterDef(name="status", parameters=@ParamDef(name="active",type="java.lang.Integer"))
public class SubjectSection implements Serializable
{
	private static final long serialVersionUID = -4095590011404770847L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_status")
	private Integer status;

	@Column(name="f_subjectId")
	private Long subjectId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", nullable=false, insertable=false, updatable=false)
	private Subject subject;

	@Column(name="f_sectionId")
	private Long sectionId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_sectionId", nullable=false, insertable=false, updatable=false)
	private Section section;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
	

	@Transient
	private String name;
	
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public Long getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(Long sectionId)
	{
		this.sectionId = sectionId;
	}

	public Section getSection()
	{
		return section;
	}

	public void setSection(Section section)
	{
		this.section = section;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
