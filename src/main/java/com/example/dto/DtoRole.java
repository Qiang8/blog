package com.example.dto;

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
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * 类名称：Role 角色类  
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月8日 下午2:53:34 
 * @version
 */


public class DtoRole implements Serializable{

	private static final long serialVersionUID = -8511056042073354323L;;


	private String id; // 用户的唯一标识

	@NotEmpty(message = "菜单名称不能为空")
	private String name;
	
	@NotEmpty(message = "菜单描述不能为空")
	private String mark;//角色描述
	
	private List<DtoMenu> menus;
	
	
	
	/**
	 * 
	 * Description: 空参构造方法
	 * @author 石维强
	 */
	public DtoRole() {
		super();
	}

	/**
	 * 
	 * Description: 有参构造方法
	 * @param id
	 * @param name
	 * @param mark
	 * @author 石维强
	 */
	public DtoRole(String id, String name, String mark) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
	}

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
