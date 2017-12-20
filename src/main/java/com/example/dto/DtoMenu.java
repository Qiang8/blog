package com.example.dto;



import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 类名称：Menu 传输类 
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月10日 下午10:24:24 
 * @version
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoMenu {
	
	@Id
	private String id;
	

	@NotEmpty(message = "菜单名称不能为空")
	private String name;
	
	@NotEmpty(message = "URL不能为空")
	private String URL;			//链接
	
	@NotEmpty(message = "菜单描述不能为空")
	private String mark;          //描述
	
	@NotEmpty(message = "菜单层级不能为空")
	private Integer level;			//层级
	
	@NotEmpty(message = "菜单父id不能为空")
	private int fatherId; //父id
	
	@NotEmpty(message = "菜单子id能为空")
	private int subId; //子id
}
