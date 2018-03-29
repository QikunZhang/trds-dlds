import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.paic.tsds.tool.template.converter.TradeConverter;
import com.paic.tsds.tool.template.dto.SwapDto;
import com.paic.tsds.tool.template.service.schedule.BaseCallable;
import com.paic.tsds.tool.template.utils.TemplateConstants;

public class DelayDispatcher {

    public static BaseCallable unfinFilter() {
	return new BaseCallable() {
	    public SwapDto execute(SwapDto inSwapDto) {
		Map reqDate = (Map) inSwapDto.getData();
		String converterName = "";
		if (String.valueOf(reqDate.get("HEAD.MAIN_TOPIC")).equals("0004")) {
		    converterName = "unfin";
		}
		return new SwapDto(TemplateConstants.SWAP_CODE_SUCCESS, converterName, null);
	    }
	};
    }

    public static TradeConverter unfinConver() {
	Map<String, Map<String, String[]>> converFun = new HashMap<String, Map<String, String[]>>();
	Map<String, String> converService = new HashMap<String, String>();
	HashMap<String, String[]> sdf = new HashMap<String, String[]>();
	sdf.put("BODY.CARD_NBR", new String[] { "card", "卡号" });
	converFun.put("格式转换1", sdf);
	converService.put("格式转换1", "testService");
	
	HashMap<String, String[]> sdf2 = new HashMap<String, String[]>();
	sdf2.put("BODY.ACCT", new String[] { "acct", "账号" });
	sdf2.put("HEAD.MAIN_TOPIC", new String[] { "maintopic", "主题" });
	converFun.put("格式转换2", sdf2);
	converService.put("格式转换2", "testService");
	
	TradeConverter tc = new TradeConverter();
	tc.setConverterName("unfin");
	tc.setConverFun(converFun);
	tc.setConverService(converService);
	tc.setPriority(15);
	return tc;
    }

    public static BaseCallable fin() {
	return new BaseCallable() {
	    public SwapDto execute(SwapDto inSwapDto) {
		Map reqDate = (Map) inSwapDto.getData();
		String converterName = "";
		if (String.valueOf(reqDate.get("HEAD.MAIN_TOPIC")).equals("0005")) {
		    converterName = "fin";
		}
		return new SwapDto(TemplateConstants.SWAP_CODE_SUCCESS, converterName, null);
	    }
	};
    }

    public static TradeConverter finConver() {
	Map<String, Map<String, String[]>> converFun = new HashMap<String, Map<String, String[]>>();
	Map<String, String> converService = new HashMap<String, String>();
	HashMap<String, String[]> sdf = new HashMap<String, String[]>();
	sdf.put("BODY.CARD_NBR", new String[] { "card", "卡号" });
	converFun.put("格式转换1", sdf);
	converService.put("格式转换1", "testService");
	
	HashMap<String, String[]> sdf2 = new HashMap<String, String[]>();
	sdf2.put("BODY.ACCT", new String[] { "acct", "账号" });
	sdf2.put("HEAD.MAIN_TOPIC", new String[] { "maintopic", "主题" });
	converFun.put("格式转换2", sdf2);
	converService.put("格式转换2", "testService");
	
	TradeConverter tc = new TradeConverter();
	tc.setConverterName("fin");
	tc.setConverFun(converFun);
	tc.setConverService(converService);
	tc.setPriority(15);
	return tc;
    }

    public static ArrayList<BaseCallable> releaseDispatcher() {
	ArrayList<BaseCallable> al = new ArrayList<BaseCallable>();
	al.add(unfinFilter());
	al.add(fin());
	return al;
    }

    public static HashMap<String, TradeConverter> releaseConverter() {
	HashMap<String, TradeConverter> al = new HashMap<String, TradeConverter>();
	al.put("unfin", unfinConver());
	al.put("fin", finConver());
	return al;
    }

}
