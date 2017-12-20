package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Department;
import com.example.domain.Employee;
import com.example.dto.TreeViewDto;
import com.example.repsitory.DeptRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DeptService {
	@Autowired
	private DeptRepository deptRepo;
	@Autowired 
	ObjectMapper om;
	

	public String findDeptTree(String id) throws JsonProcessingException {
		Department dept = deptRepo.findOne(id);
		TreeViewDto tvd = new TreeViewDto();
			if(dept.getDepartments()!=null) {
				List<Department> childs = dept.getDepartments();
				List<TreeViewDto> nodes = new ArrayList<>();
				Map<String, TreeViewDto> childMap = new HashMap<>();
				for(Department child:childs) {
					
					TreeViewDto childNode = new TreeViewDto();
					if(childMap.get(child.getDeptName())==null) {
					childNode.setText(child.getDeptName());
					childNode.setColor("#4d88ff");
					childNode.setBackColor("#e5ffee");
					childNode.setIcon("glyphicon glyphicon-user");
					childNode.setSelectedIcon("glyphicon glyphicon-ok");
					childNode.setHref("getEmps?id="+child.getId());
					nodes.add(childNode);
					childMap.put(child.getDeptName(), childNode);
					}
				}
				TreeViewDto.setNodes(nodes);
			
		}
		String data = om.writeValueAsString(treeDto);
		return data;
	}

	public List<Employee> findById(String id){
		Department department = deptRepo.findOne(id);
		List<Employee> employees = department.getEmployees();
		return employees;
}

}
