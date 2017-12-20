package com.example.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.domain.Menu;

/**
 * 
 * 类名称：MenuRepository   
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月11日 下午10:12:19 
 * @version
 */
public interface MenuRepository extends JpaRepository<Menu, String>,JpaSpecificationExecutor<Menu>,
								PagingAndSortingRepository<Menu,String>{

}
