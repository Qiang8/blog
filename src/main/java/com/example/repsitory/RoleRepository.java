package com.example.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.domain.Role;
import com.example.domain.User;


/**
 * 
 * 类名称：RoleRepository   角色仓库
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月8日 下午3:57:53 
 * @version
 */
public interface RoleRepository extends JpaRepository<Role, String>,JpaSpecificationExecutor<Role>,
										PagingAndSortingRepository<Role,String>{

}
