package com.example.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * 类名称：User 实体类
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月10日 下午10:23:59 
 * @version
 */

@Entity 
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid") //使用uuid生成主键
	@Column(nullable = false, length = 100) 
	private String id; // 用户的唯一标识
 
	@Column(nullable = false, length = 20)
	private String userId;

	@Column(length = 100)
	private String password; // 登录时密码

	@Column(nullable = false, length = 40) // 映射为字段，值不能为空
	private String name;

	@Column(nullable = false, length = 4) // 映射为字段，值不能为空
	private String gender;

	@Column(nullable = false, length = 40) 
	private String staffCategory;  //员工类别
	
	@Column(nullable = false, length = 100) 
	private String city;  //城市
	
	@Column(nullable = false, length = 40) 
	private String department;  // 映射为字段，值不能为空

	@Column(nullable = false, length = 40) 
	private String secondDepartment;  // 映射为字段，值不能为空

	@Column(nullable = false, length = 40) 
	private String positionSequence;  // 映射为字段，值不能为空

	@Column(nullable = false, length = 40) 
	private String  positionLevel;  // 映射为字段，值不能为空

	@Column(nullable = false, length = 40) 
	private String  position;  // 映射为字段，值不能为空
	
	@Column(nullable = false) // 映射为字段，值不能为空
	private Date hiredate;

	@Column(nullable = false, length = 20)
	private String phone;

	@Column(nullable = false, length = 50, unique = true)
	private String email;

	private Blob avatar; // 头像图片

	@Column(nullable = false, length = 4)
	private int age;

	@Column(nullable = false, length = 20)
	private String qualification;
	
	@Column(nullable = false) // 映射为字段，值不能为空
	private Date birthdate;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	/**
	 * 
	 * Description: 无参构造函数；
	 */
	
	public User() { 
	}
	
		/**
		 * 
		 * Description:有参构造函数
		 */
	public User( String userId, String password, String name, String gender, String staffCategory, String city,
			String department, String secondDepartment, String positionSequence, String positionLevel, String position,
			Date hiredate, String phone, String email, Blob avatar, int age, String qualification,
			Date birthdate) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.staffCategory = staffCategory;
		this.city = city;
		this.department = department;
		this.secondDepartment = secondDepartment;
		this.positionSequence = positionSequence;
		this.positionLevel = positionLevel;
		this.position = position;
		this.hiredate = hiredate;
		this.phone = phone;
		this.email = email;
		this.avatar = avatar;
		this.age = age;
		this.qualification = qualification;
		this.birthdate = birthdate;
	}
	/*
	 * @Description: 各属性的get、set方法
	 */
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getStaffCategory() {
		return staffCategory;
	}


	public void setStaffCategory(String staffCategory) {
		this.staffCategory = staffCategory;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getsecondDepartment() {
		return secondDepartment;
	}


	public void setsecondDepartment(String secondDepartment) {
		secondDepartment = secondDepartment;
	}


	public String getPositionSequence() {
		return positionSequence;
	}


	public void setPositionSequence(String positionSequence) {
		this.positionSequence = positionSequence;
	}


	public String getPositionLevel() {
		return positionLevel;
	}


	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public Date getHiredate() {
		return hiredate;
	}


	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Blob getAvatar() {
		return avatar;
	}


	public void setAvatar(Blob avatar) {
		this.avatar = avatar;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public Date getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", staffCategory=" + staffCategory + ", city=" + city + ", department=" + department
				+ ", secondDepartment=" + secondDepartment + ", positionSequence=" + positionSequence
				+ ", positionLevel=" + positionLevel + ", position=" + position + ", hiredate=" + hiredate + ", phone="
				+ phone + ", email=" + email + ", avatar=" + avatar + ", age=" + age + ", qualification="
				+ qualification + ", birthdate=" + birthdate + "]";
	}

}
