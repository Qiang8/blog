package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 类名称：Menu  实体类 
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月10日 下午10:24:24 
 * @version
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid") //使用uuid生成主键
	@Column(nullable = false, length = 100) 
	private String id;
	
	
	@Size(min=0, max=40)
	@Column(nullable = false, length = 40) 
	private String name;
	
	@Size(min=0, max=100)
	@Column(nullable = false, length = 100) 
	private String URL;			//链接
	
	@Column(nullable = false,length = 100)
	private String mark;          //描述
	
	@Column(nullable = false,length = 20)
	private Integer level;			//层级
	
	@Column(nullable = false,length = 20)
	private int fatherId; //父id
	
	@Column(nullable = false,length = 20)
	private int subId; //子id
}
