package com.example.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 类名称：Role_Menu 角色和菜单关联类  
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月8日 下午3:28:51 
 * @version
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role_Menu implements Serializable{

	private static final long serialVersionUID = 7671493345445282009L;

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid") //使用uuid生成主键
	private String id;
	
	@Column(name="role_id",nullable = false, length = 100)
	private String roleId; 
	
	@Column(name="menu_id",nullable = false, length = 100)
	private String menuId; 
}
