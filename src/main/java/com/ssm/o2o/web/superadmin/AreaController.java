package com.ssm.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.o2o.entity.Area;
import com.ssm.o2o.service.AreaService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger=(Logger) LoggerFactory.getLogger(AreaController.class);
	@Autowired
	AreaService areaService;
	
	@RequestMapping(value="/listarea",method=RequestMethod.GET)
	@ResponseBody //json格式
	private Map<String,Object> listArea(){
		Map<String,Object> modelMap=new HashMap<>();
		List<Area> list=new ArrayList<>();
		try {
			list=areaService.getAreaList();
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		}
		catch(Exception e){
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		logger.error("日志输出");
		return modelMap;
	}
	
	
	
	
	public List<Area> findAllAreas() {
		return areaService.getAreaList();
	}
}
