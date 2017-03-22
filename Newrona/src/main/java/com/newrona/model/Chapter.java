package com.newrona.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import com.newrona.util.Utility;

@Entity
@Table(name = "t_chapter")
@FilterDefs
({ 
	@FilterDef
	(
		name = "chapterFilterOrder", 
		parameters = { @ParamDef(name = "active", type = "java.lang.Integer"), @ParamDef(name = "gradeId", type = "java.lang.Long"), @ParamDef(name = "schoolId", type = "java.lang.Long"), @ParamDef(name = "order", type = "java.lang.Integer") }
	), 
	@FilterDef
	(
		name = "chapterFilterOrderRetail", 
		parameters = { @ParamDef(name = "active", type = "java.lang.Integer"), @ParamDef(name = "gradeId", type = "java.lang.Long"), @ParamDef(name = "boardId", type = "java.lang.Long"), @ParamDef(name = "order", type = "java.lang.Integer") }
	), 
	@FilterDef(name = "chapterFilterStatus", parameters = { @ParamDef(name = "active", type = "java.lang.Integer")}) 
})
public class Chapter implements Serializable
{
	private static final long serialVersionUID = 7999939099916282935L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_DISABLED = 2;
	/*public static final int STATUS_teacherToStudentContentstatus_INACTIVE = 0;
	public static final int STATUS_teacherToStudentContentstatus_ACTIVE = 1;
	*/

	// ###################################################################################//
	// ## User-wise : schoolId will be null //
	// ## School-wise : boardId will be null //
	// ## Extra fields : boardId //
	// ###################################################################################//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Lob
	@Column(name = "f_name")
	private String name;

	@Column(name = "f_subjectId")
	private Long subjectId;

	@Column(name = "f_schoolId")
	private Long schoolId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "chapter")
	@Filters({ @Filter(name = "topicFilterOrder", condition = "f_status = :active AND f_order = :order"), @Filter(name = "topicFilterstatus", condition = "f_status = :active") })
	private List<Topic> topics;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_subjectId", insertable = false, nullable = false, updatable = false)
	private Subject subject;

	@Column(name = "f_grade_id")
	private Long gradeId;

	@Column(name = "f_board_id")
	private Long boardId;

	@Lob
	@Column(name = "f_description")
	private String description;

	@Column(name = "f_status")
	private Integer status;
	

	@Column(name = "f_order")
	private Integer serialOrder;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_insertDate", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "f_modifiedDate", nullable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	
	@Transient
	private LinkedHashSet<Topic> topicSet = new LinkedHashSet<Topic>();
	
	@Transient
	private Integer count;
	@Transient
	private Integer moduleCount;
	@Transient
	private Integer isCompleted;
	
	
	@Transient
	private int markChapterContentPermission;
	

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
	
	
	public int getMarkChapterContentPermission()
	{
		return markChapterContentPermission;
	}

	public void setMarkChapterContentPermission(int markChapterContentPermission)
	{
		this.markChapterContentPermission = markChapterContentPermission;
	}
	/*@Transient
	private String name;*/

	public Integer getIsCompleted()
	{
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted)
	{
		this.isCompleted = isCompleted;
	}

	public Integer getModuleCount()
	{
		return moduleCount;
	}

	public void setModuleCount(Integer moduleCount)
	{
		this.moduleCount = moduleCount;
	}

	public Integer getCount()
	{
		return count;
	}

	public void setCount(Integer count)
	{
		this.count = count;
	}

	@Transient
	private Integer resultPercent = 0;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	/*public Byte[] getNameUTF8()
	{
		return nameUTF8;
	}

	public void setNameUTF8(Byte[] nameUTF8)
	{
		this.nameUTF8 = nameUTF8;
	}*/

	public Long getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(Long subjectId)
	{
		this.subjectId = subjectId;
	}

	public Long getSchoolId()
	{
		return schoolId;
	}

	public void setSchoolId(Long schoolId)
	{
		this.schoolId = schoolId;
	}


	public Long getGradeId()
	{
		return gradeId;
	}

	public void setGradeId(Long gradeId)
	{
		this.gradeId = gradeId;
	}

	public Long getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Long boardId)
	{
		this.boardId = boardId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	//@PrePersist
	//@PreUpdate
	public String getName()
	{
		//return Utility.convertByteUTF8ToString(getNameUTF8());
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public Integer getSerialOrder()
	{
		return serialOrder;
	}

	public void setSerialOrder(Integer serialOrder)
	{
		this.serialOrder = serialOrder;
	}

	public Subject getSubject()
	{
		return subject;
	}

	public void setSubject(Subject subject)
	{
		this.subject = subject;
	}

	public Integer getResultPercent()
	{
		return resultPercent;
	}

	public void setResultPercent(Integer resultPercent)
	{
		this.resultPercent = resultPercent;
	}

	public List<Topic> getTopics()
	{
		return topics;
	}

	public LinkedHashSet<Topic> getTopicSet() {
		return topicSet;
	}

	public void setTopicSet(LinkedHashSet<Topic> topicSet) {
		this.topicSet = topicSet;
	}

	public void setTopics(List<Topic> topics)
	{
		this.topics = topics;
	}
	
	
		
}