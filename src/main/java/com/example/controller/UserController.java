package com.example.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 类名称：UserController   
 * 类描述：   
 * 创建人：石维强   
 * 创建时间：2017年12月11日 下午4:31:08 
 * @version
 */

@Controller
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login() {
		return "doLogin";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String success() {
		
		return "userList";
	}
	
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	@ResponseBody
	public Map getList(String models){
		System.out.println("=========================");
		/*Map searchParameters = new HashMap();
		if (models != null && models.length() > 0) {
			try {
				searchParameters = objectMapper.readValue(models,
						new TypeReference<Map>() {
						});
			} catch (JsonParseException e) {
				log.error("JsonParseException{}:", e.getMessage());
			} catch (JsonMappingException e) {
				log.error("JsonMappingException{}:", e.getMessage());
			} catch (IOException e) {
				log.error("IOException{}:", e.getMessage());
			}
		}*/
		Map page=userService.getList(1, 5, null, null);
		System.out.println(page);
		return page;
	}

}
