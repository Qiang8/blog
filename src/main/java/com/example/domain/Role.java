package com.example.domain;

import java.io.Serializable;
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

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * 类名称：Role 角色类  
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月8日 下午2:53:34 
 * @version
 */
@Entity
public class Role implements Serializable{

	private static final long serialVersionUID = -8511056042073354323L;;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid") //使用uuid生成主键
	@Column(nullable = false, length = 100) 
	private String id; // 用户的唯一标识

	@Column(nullable = false,length = 40) // 映射为字段，值不能为空
	private String name;
	
	@Column(nullable = false,length = 100)// 映射为字段，值不能为空
	private String mark;//角色描述
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"))
	private List<Menu> menus;
	
	/**
	 * 
	 * @Description: 角色表各属性的get set方法
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	
	

	
}
