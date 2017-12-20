package com.example.repsitory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.domain.User;




/**
 * 
 * 类名称：UserRepository 用户仓库   
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月8日 下午3:48:30 
 * @version
 */
public interface UserRepository extends JpaRepository<User, String>,JpaSpecificationExecutor<User>,
										PagingAndSortingRepository<User,String>{
	 
	
	//根据员工编号删除
	public void removeByUserId(String userId);

}
