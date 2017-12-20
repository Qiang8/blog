package com.example.service;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.example.domain.Menu;
import com.example.domain.Menu;
import com.example.dto.DtoMenu;
import com.example.dto.DtoMenu;
import com.example.repsitory.MenuRepository;


public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	/**
	 * 
	 * @Description: 增加菜单
	 * @param menu
	 * @return Menu 
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	@Transactional
	public void saveMenu(DtoMenu dMenu) {
		Menu menu=new Menu();
		BeanUtils.copyProperties(dMenu, menu);
		 menuRepository.save(menu);
	}

	/**
	 * 
	 * @Description: 删除菜单
	 * @param id void  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	@Transactional
	public void removeMenu(String id) {
		menuRepository.delete(id);
	}
	
	/**
	 * 
	 * @Description: 修改菜单
	 * @param menu
	 * @return Menu  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	
	@Transactional
	public void updateMenu(DtoMenu dMenu) {
		Menu menu=new Menu();
		BeanUtils.copyProperties(dMenu, menu);
		 menuRepository.save(menu);
	}
	
	/**
	 * 
	 * @Description: menu分页查询
	 * @param page
	 * @param pageSize
	 * @param orderMaps
	 * @param filters
	 * @return Map  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	public Map getList(int page,int pageSize,
			HashMap<String,String> orderMaps,HashMap<String,String> filters) {
		Page<Menu> pageContent;
		if (pageSize < 1)
			pageSize = 1;
		if (pageSize > 100)
			pageSize = 100;

		List<Order> orders = new ArrayList<Order>();
		if (orderMaps != null) {
			for (String key : orderMaps.keySet()) {
				if ("DESC".equalsIgnoreCase(orderMaps.get(key))) {
					orders.add(new Order(Direction.DESC, key));
				} else {
					orders.add(new Order(Direction.ASC, key));
				}
			}
			
		}
		PageRequest pageable;
		if (orders.size() > 0) {
			pageable = new PageRequest(page, pageSize, new Sort(orders));
		} else {
			pageable = new PageRequest(page, pageSize);
		}

		if (filters != null) {
			Specification<Menu> spec = new Specification<Menu>() {
				@Override
				public Predicate toPredicate(Root<Menu> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (String key : filters.keySet()) {
						String value = filters.get(key);
						if ("enabled".equalsIgnoreCase(key)) {
							if ("true".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(key), true));
							} else if ("false".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(key), false));
							}
						} else if ("code".equalsIgnoreCase(key)) {
							if (value.length() > 0)
								pl.add(cb.like(root.<String> get(key), value + "%"));
						} else if ("name".equalsIgnoreCase(key)) {
							if (value.length() > 0)
								pl.add(cb.like(root.get(key), value));
						}
					}
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			pageContent = menuRepository.findAll(spec, pageable);
		} else {
			pageContent = menuRepository.findAll(pageable);
		}
		
		Map map = new HashMap();
		map.put("total", pageContent.getTotalElements());
		map.put("Menus", accountPage2Dto(pageContent));
		return map;
	}
	
	/**
	 * 
	 * @Description: getPage方法中的accountPage2Dto方法的实现
	 * @param pageContent
	 * @return List<DtoMenu>  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	private List<DtoMenu> accountPage2Dto(Page<Menu> pageContent) {
		List<DtoMenu> uList=new ArrayList<DtoMenu>();	
		List<Menu> content = pageContent.getContent();
		for (Menu Menu : content) {
			DtoMenu MenuToDto = MenuToDto(Menu);
			uList.add(MenuToDto);
		}
		return uList;
	}

	/**
	 * 
	 * @Description: Menu实体转dto
	 * @param Menu
	 * @return DtoMenu  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	public DtoMenu MenuToDto(Menu Menu) {
		DtoMenu dtoMenu=new DtoMenu();
		BeanUtils.copyProperties(Menu, dtoMenu);
		return dtoMenu;
	}
	

}
