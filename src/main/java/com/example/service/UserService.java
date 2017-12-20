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
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.dto.DtoUser;
import com.example.repsitory.UserRepository;

/**
 * 
 * 类名称：UserService  user服务类 
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月10日 下午8:54:36 
 * @version
 */

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * 
	 * @Description: 增加用户
	 * @param user
	 * @return User  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	
	@Transactional
	public void saveUser(DtoUser dtoUser) {
		
		User user =new User();
		BeanUtils.copyProperties(dtoUser, user);
		
		User save = userRepository.save(user);
		
		
	}
	
	/**
	 * 
	 * @Description: 根据员工编号删除
	 * @param id void  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 * 
	 */

	@Transactional
	public void removeUser(String userId) {
		userRepository.removeByUserId(userId);
	}

	/**
	 * 
	 * @Description: 修改用户
	 * @param user
	 * @return User  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月10日
	 */
	@Transactional
	public void updateUser(DtoUser dtoUser) {

		User user = new User();
		BeanUtils.copyProperties(dtoUser, user);
		userRepository.save(user);	 
	}
	
	/**
	 * 
	 * @Description: 用户列表查询
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
		Page<User> pageContent;
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
			Specification<User> spec = new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root,
						CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> pl = new ArrayList<Predicate>();
					for (String key : filters.keySet()) {
						String value = filters.get(key);
						if ("enabled".equalsIgnoreCase(key)) {
							if ("true".equalsIgnoreCase(value)){
								pl.add(cb.equal(root.get(key), true));
							} else if ("false".equalsIgnoreCase(value)) {
								pl.add(cb.equal(root.get(key), false));
							}
						}  
						if ("code".equalsIgnoreCase(key)) {
							if (value.length() > 0)
								pl.add(cb.like(root.<String> get(key), value + "%"));
						}  
						if ("name".equalsIgnoreCase(key)) {
							if (value.length() > 0)
								pl.add(cb.like(root.get(key), value));
						}
					}
					return cb.and(pl.toArray(new Predicate[0]));
				}
			};
			
			pageContent = userRepository.findAll(spec, pageable);
			
		} else {
			pageContent = userRepository.findAll(pageable);
		}
		
		Map map = new HashMap();
		map.put("total", pageContent.getTotalElements());
		map.put("users", accountPage2Dto(pageContent));
		return map;
	}
	
	/**
	 * 
	 * @Description: getPage方法中的accountPage2Dto方法的实现
	 * @param pageContent
	 * @return List<DtoUser>  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	//TODO
	private List<DtoUser> accountPage2Dto(Page<User> pageContent) {
		List<DtoUser> uList=new ArrayList<DtoUser>();	
		List<User> content = pageContent.getContent();
		System.out.println("这是userservice"+content);
		for (User user : content) {
			DtoUser userToDto = UserToDto(user);
			uList.add(userToDto);
		}
		return uList;
	}

	/**
	 * 
	 * @Description: user实体转dto
	 * @param user
	 * @return DtoUser  
	 * @throws
	 * @author 石维强
	 * @date 2017年12月11日
	 */
	public DtoUser UserToDto(User user) {
		DtoUser dtoUser=new DtoUser();
		BeanUtils.copyProperties(user, dtoUser);
		return dtoUser;
	}
	
}
