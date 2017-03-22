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

import com.newrona.util.Utility;

@Entity
@Table(name = "t_question_topic")
public class QuestionTopic implements Serializable
{	
	private static final long serialVersionUID = 1013458709370254509L;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_DISABLED = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private Long id;

	@Column(name = "f_question_id")
	private Long questionId;
	
	@Column(name = "f_topic_id")
	private Long topicId;
	
	// uni-directional many-to-one association to Question
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_question_id", insertable=false, updatable=false)
	private Question question;

	// uni-directional many-to-one association to Grade
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "f_topic_id", insertable=false, updatable=false)
	private Topic topic;
	
	@Column(name = "f_status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_insertDate", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date insertDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="f_modifiedDate", nullable=false, columnDefinition="TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date modifiedDate = new Date();
	

	@Column(name = "f_cognitive_level")
	private Long cognitiveLevelId;

	@Column(name = "f_difficulty_level")
	private Long difficultyLevelId;
	
	
	
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
	
	public Long getCognitiveLevelId()
	{
		return cognitiveLevelId;
	}

	public void setCognitiveLevelId(Long cognitiveLevelId)
	{
		this.cognitiveLevelId = cognitiveLevelId;
	}

	public Long getDifficultyLevelId()
	{
		return difficultyLevelId;
	}

	public void setDifficultyLevelId(Long difficultyLevelId)
	{
		this.difficultyLevelId = difficultyLevelId;
	}

	public QuestionType getCognitiveLevel()
	{
		return cognitiveLevel;
	}

	public void setCognitiveLevel(QuestionType cognitiveLevel)
	{
		this.cognitiveLevel = cognitiveLevel;
	}

	public QuestionType getDifficultyLevel()
	{
		return difficultyLevel;
	}

	public void setDifficultyLevel(QuestionType difficultyLevel)
	{
		this.difficultyLevel = difficultyLevel;
	}

	// uni-directional many-to-one association to QuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_cognitive_level", insertable = false, updatable = false)
	private QuestionType cognitiveLevel;

	// uni-directional many-to-one association to QuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_difficulty_level", insertable = false, updatable = false)
	private QuestionType difficultyLevel;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getQuestionId()
	{
		return questionId;
	}

	public void setQuestionId(Long questionId)
	{
		this.questionId = questionId;
	}

	public Long getTopicId()
	{
		return topicId;
	}

	public void setTopicId(Long topicId)
	{
		this.topicId = topicId;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public Topic getTopic()
	{
		return topic;
	}

	public void setTopic(Topic topic)
	{
		this.topic = topic;
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