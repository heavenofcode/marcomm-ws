package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "m_event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 11, nullable = false)
	private Long id;
	
	@Column(name = "code", length = 50, nullable = false)
	private String code;
	
	@Column(name = "event_name", length = 50, nullable = true)
	private String eventName;
	
	@Column(name = "start_date", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "end_date", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name = "place", length = 255, nullable = true)
	private String place;
	
	@Column(name = "budget", nullable = true)
	private Double budget;
	
	@Column(name = "requested_by", length = 11, nullable = false)
	private Long requestedBy;
	
	@Column(name = "requested_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date requestedDate;
	
	@Column(name = "approved_by", length = 11, nullable = true)
	private Long approvedBy;
	
	@Column(name = "approved_date", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date approvedDate;
	
	@Column(name = "assign_to", length = 11, nullable = true)
	private Long assignTo;
	
	@Column(name = "closed_date", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date closedDate;
	
	@Column(name = "notes", length = 255, nullable = true)
	private String notes;
	
	@Column(name = "status", length = 1, nullable = true)
	private Integer status;
	
	@Column(name = "reject_reason", length = 255, nullable = true)
	private String rejectReason;
	
	@Column(name = "is_delete", nullable = true)
	private Boolean isDelete;
	
	@Column(name = "created_by", length = 50)
	private Long createdBy;
	
	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "updated_by", length = 50)
	private Long updatedBy; 
	
	@Column(name = "updated_date")
	@Temporal(TemporalType.DATE)
	private Date updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Long getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Long requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Long getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Long approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Long getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(Long assignTo) {
		this.assignTo = assignTo;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}