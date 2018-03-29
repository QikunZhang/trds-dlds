package com.paic.tsds.dlds.creditcard.test;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paic.tsds.tool.template.dispatcher.DelayDispatcher;
import com.paic.tsds.tool.template.dto.SwapDto;
import com.paic.tsds.tool.template.service.Service;
import com.paic.tsds.tool.template.utils.BeanLoader;

/**
 * @description TODO
 * @log 日志
 *      <p>
 *      ====2018年3月29日----ZHANGQIKUN785====
 *      </p>
 *      <p>
 *      Note: TODO修改原因
 *      </p>
 */

@Controller
@RequestMapping("/test")
public class TestController {
    
    @Resource(name="testService")
    private Service testService;
    
    
    @RequestMapping("/testServie")
    @ResponseBody
    void testServer(){
	testService.execute(new SwapDto(new HashMap<>()));
    }
    
    @RequestMapping("/testDispatcher")
    void testDispatcher(){
	HashMap<String, String> reqdata = new HashMap<String, String>();
	reqdata.put("HEAD.MAIN_TOPIC", "0004");
	reqdata.put("BODY.CARD_NBR", "00874123456789");
	reqdata.put("BODY.ACCT", "123456789");
	
	DelayDispatcher dd = (DelayDispatcher) BeanLoader.getContext().getBean("delayDispatcher");
	dd.doDispatch(reqdata);
	
    }
    
}
