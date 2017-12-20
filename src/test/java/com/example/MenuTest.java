package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dto.DtoMenu;
import com.example.service.MenuService;
import com.example.service.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuTest {
	
	@Autowired
	private MenuService menuService;
	
	@Test
	public void add() {		
		DtoMenu dMenu=new DtoMenu("1", "一级菜单", "url", "", 1, 12, 12);
		menuService.saveMenu(dMenu);
	}
	
	
	@Test
	public void delete() {
		//menuService.removeMenu(id);
	}
	
	@Test
	public void update() {
		DtoMenu dMenu=new DtoMenu();
		//dMenu.setId(id);
		dMenu.setName("二级菜单");
		dMenu.setMark("这是一个菜单");
		menuService.updateMenu(dMenu);
	}

}
