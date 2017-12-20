package com.example;

import java.sql.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dto.DtoUser;
import com.example.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private ObjectMapper  objectMapper;
	
	/**
	 * 获取当前对象
	 * 
	 * UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 */
	
	/**
	 * 获取当前时间
	 * 
	 * Calendar.getInstance();
	 */
	
	@Test
	public void add() {
		Date hiredate = Date.valueOf("2017-09-07");
		Date birthdate = Date.valueOf("1988-09-06");
		/*DtoUser dUser=new DtoUser(2017002l, "123", "刘海柱", "男", "销售", "北京", "销售部", "销售三部",
				"3213", "s4", "销售", hiredate, 1887859383l, "123@123.com", null, 39, "本科", birthdate);*/
		DtoUser dUser=new DtoUser("2017003", "124", "刘能", "男", "销售", "北京", "销售部", "销售二部",
				"3213", "s4", "销售", hiredate, "1887859383l", "456@123.com", null, 39, "本科", birthdate);
		 userservice.saveUser(dUser);	
	}
	
	@Test
	public void delete() {
		userservice.removeUser("2017001");
	}
	
	@Test
	public void update() {
		DtoUser dtoUser=new DtoUser();
		dtoUser.setUserId("2017002");
		dtoUser.setAge(23);
		dtoUser.setName("无敌");
		userservice.updateUser(dtoUser);
	}
	
	public void getList() {
		String models="{'pag':1,'pageSize':8,'filter':{'filters':[{'field':'loginName','value':'sue'},"
				+ "{'field':'userName','value':'sd'}],'logic':'and'}}";
		
		 //Map readValue = objectMapper.readValue(models,new TypeReference<Map>() {});
		//userservice.getList(page, pageSize, orderMaps, filters)
	}
	
}
