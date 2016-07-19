package com.hexun.yewu.orders.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hexun.framework.core.controller.DefaultBaseController;
import com.hexun.framework.core.utils.DateUtils;
import com.hexun.framework.core.utils.HTTPUtils;
import com.hexun.framework.core.utils.RespEnum;
import com.hexun.framework.core.utils.StringUtils;

/**
 * 后台管理部分
 * @author zhoudong
 *
 */
//@Scope("prototype")
@Controller
public class AdminController extends DefaultBaseController {
	private Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView index() {
		return getModelAndView("index", "key1","value1","key2","value2","key3","value3");
	}
	
	/**
	 * 带参数
	 * @return
	 */
	@RequestMapping(value="tags/{userName}",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView tags(@PathVariable String userName) {
		return getModelAndView("tags","userName",userName);
	}
	
	/**
	 * 带参数
	 * @return
	 */
	@RequestMapping(value="details",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView details() {
		return getModelAndView("tags","userName",getRequest().getParameter("userName"),
				"userCode",getRequest().getParameter("userCode"));
	}
	
	/**
	 * 返回json 测试
	 * 测试线程
	 * @return
	 */
	@RequestMapping(value="thread",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> thread() {
		String sleep = getRequest().getParameter("sleep");
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(sleep.equals("on")){
			resultMap.put("a", "a");
			resultMap.put("b", "b");
			resultMap.put("c", "c");
			resultMap.put("d", "d");
			resultMap.put("e", "e");
		}else {
			resultMap.put("aa", "aa");
			resultMap.put("bb", "bb");
			resultMap.put("cc", "cc");
			resultMap.put("dd", "dd");
			resultMap.put("ee", "ee");
		}
		
		
		if(sleep.equals("on")){
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(Map.Entry<String, Object> entry : resultMap.entrySet()){
			System.out.println(sleep + "-->" + entry.getKey() + "--" +entry.getValue());
		}
		return resultMap;
	}
	
	/**
	 * 返回ModelAndView 测试
	 * 测试线程
	 * @return
	 */
	@RequestMapping(value="thread1",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> thread1() {
		String sleep = getRequest().getParameter("sleep");
		
		ModelAndView mav;
		if(sleep.equals("on")){
			mav = getModelAndView("index");
		}else {
			mav = getModelAndView("tags");
		}
		
		if(sleep.equals("on")){
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(sleep +"--->"+ mav.getViewName());
		
		return new HashMap<String, Object>();
	}
	
	/**
	 * 单纯的返回json数据
	 * @param userName
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value="resp_json/{userName}/{userCode}",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> respJson(@PathVariable String userName,@PathVariable String userCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userCode", userCode);
		map.put("respCode", RespEnum.RESP_SUCCESS.getCode());
		map.put("respMsg", RespEnum.RESP_SUCCESS.getCnMsg());
		return map;
	}
	/**
	 * 通过http 调用远程数据
	 * @return
	 */
	@RequestMapping(value="get_http_json",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody JSONObject httpJsonValue(){
		String url = getRequest().getParameter("url");
		if(StringUtils.isBlank(url)){
			url = "http://localhost:8080/orders/resp_json/tom/tomCode";
		}
		String result = HTTPUtils.sendGet(url);
		return JSONObject.parseObject(result);
	}
	/**
	 * 操作时间
	 * @return
	 */
	@RequestMapping(value="date",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String,Object> date(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("当前时间", DateUtils.getDateAndTime(new Date()));
		map.put("今天周几", DateUtils.getWeek(new Date()).getChineseName());
		map.put("明天现在", DateUtils.getDateAndTime(DateUtils.addDay(new Date(), 1)));
		map.put("1小时以后", DateUtils.getDateAndTime(DateUtils.addHour(new Date(), 1)));
		map.put("10分钟以后", DateUtils.getDateAndTime(DateUtils.addMinute(new Date(), 10)));
		map.put("respCode", RespEnum.RESP_SUCCESS.getCode());
		map.put("respMsg", RespEnum.RESP_SUCCESS.getCnMsg());
		return map;
	}
}
