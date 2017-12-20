package com.example;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.dto.DtoRole;
import com.example.service.RoleService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {
	
	@Autowired
	private RoleService roleService;
	
	
	@Test
	public void add() {
		DtoRole drole=new DtoRole("123", "user", "普通用户");
		roleService.saveRole(drole);
	}
	
	@Test
	public void delete() {
		//roleService.removeRole(id);
	}
	
	
	@Test
	public void update() {
		DtoRole drole=new DtoRole();
		drole.setName("admin");
		drole.setMark("基本用户");
		roleService.updateRole(drole);
	}

}
