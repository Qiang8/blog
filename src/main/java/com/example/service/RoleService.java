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

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.domain.Role;
import com.example.dto.DtoRole;
import com.example.repsitory.RoleRepository;
/**
 * 
 * 类名称：RoleService Role服务类  
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月8日 下午5:00:10 
 * @version
 */
@Service
public class RoleService {
	

	@Autowired
	private RoleRepository roleRepository;
	
	
	/**
	 * 
	 * @Description: 增加角色
	 * @param role
	 * @return Role  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	@Transactional
	public void saveRole(DtoRole dtorole) {
		Role role=new Role();
		BeanUtils.copyProperties(dtorole, role);
		 roleRepository.save(role);
	}

	/**
	 * 
	 * @Description: 删除角色
	 * @param id void  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	@Transactional
	public void removeRole(String id) {
		roleRepository.delete(id);
	}
	
	/**
	 * 
	 * @Description: 修改角色
	 * @param role
	 * @return Role  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	
	@Transactional
	public void updateRole(DtoRole dtorole) {
		Role role = new Role();
		BeanUtils.copyProperties(dtorole, role);
		 roleRepository.save(role);
	}
	
	/**
	 * 
	 * @Description: role分页查询
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
		Page<Role> pageContent;
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
			Specification<Role> spec = new Specification<Role>() {
				@Override
				public Predicate toPredicate(Root<Role> root,
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
			pageContent = roleRepository.findAll(spec, pageable);
		} else {
			pageContent = roleRepository.findAll(pageable);
		}
		
		Map map = new HashMap();
		map.put("total", pageContent.getTotalElements());
		map.put("Roles", accountPage2Dto(pageContent));
		return map;
	}
	
	/**
	 * 
	 * @Description: getPage方法中的accountPage2Dto方法的实现
	 * @param pageContent
	 * @return List<DtoRole>  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	private List<DtoRole> accountPage2Dto(Page<Role> pageContent) {
		List<DtoRole> uList=new ArrayList<DtoRole>();	
		List<Role> content = pageContent.getContent();
		for (Role Role : content) {
			DtoRole RoleToDto = RoleToDto(Role);
			uList.add(RoleToDto);
		}
		return uList;
	}

	/**
	 * 
	 * @Description: Role实体转dto
	 * @param Role
	 * @return DtoRole  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	public DtoRole RoleToDto(Role Role) {
		DtoRole dtoRole=new DtoRole();
		BeanUtils.copyProperties(Role, dtoRole);
		return dtoRole;
	}
	
	
	
	

}
