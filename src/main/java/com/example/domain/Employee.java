package com.example.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the employee database table.
 * 
 */

@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eid;
	
	private String city;
	
	private String ename;
	
	private String gender;
	
	private String password;

	@Column(name="position_level")
	private String positionLevel;

	@Column(name="position_name")
	private String positionName;

	@Column(name="position_sequence")
	private String positionSequence;

	@Column(name="staff_category")
	private String staffCategory;
	
	private String state;
	
	private String username;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "emp_dept", joinColumns = @JoinColumn(name = "emp_id", referencedColumnName = "eid"), 
		inverseJoinColumns = @JoinColumn(name = "dept_id", referencedColumnName = "did"))
	private List<Department> departments;

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Employee() {
	}

	public String getEid() {
		return this.eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPositionLevel() {
		return this.positionLevel;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionSequence() {
		return this.positionSequence;
	}

	public void setPositionSequence(String positionSequence) {
		this.positionSequence = positionSequence;
	}

	public String getStaffCategory() {
		return this.staffCategory;
	}

	public void setStaffCategory(String staffCategory) {
		this.staffCategory = staffCategory;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}