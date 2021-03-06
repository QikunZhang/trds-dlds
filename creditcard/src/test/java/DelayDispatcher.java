import java.util.ArrayList;
import java.util.Map;

import com.paic.tsds.tool.template.dto.SwapDto;
import com.paic.tsds.tool.template.service.schedule.BaseCallable;

public class DelayDispatcher {

    /**
      * @description 修改成有优先级(大于0)、映射方式的返回(多个映射方式)
      * @return BaseCallable
     */
    public static BaseCallable unfinFilter() {
	return new BaseCallable() {
	    public SwapDto execute(SwapDto inSwapDto) {
		Map reqDate = (Map) inSwapDto.getData();
		String converters = "";
		int priority = -1;
		
		if (String.valueOf(reqDate.get("HEAD.MAIN_TOPIC")).equals("0004")) {
		    converters = "dddd1===ccc2";
		    priority = 20;
		}
		return new SwapDto(priority, converters, null);
	    }
	};
    }

    public static BaseCallable fin() {
	return new BaseCallable() {
	    public SwapDto execute(SwapDto inSwapDto) {
		Map reqDate = (Map) inSwapDto.getData();
		String converters = "";
		int priority = -1;
		if (String.valueOf(reqDate.get("HEAD.MAIN_TOPIC")).equals("0005")) {
		    converters = "ccc4";
		}
		return new SwapDto(priority, converters, null);
	    }
	};
    }

    public static ArrayList<BaseCallable> releaseDispatcher() {
	ArrayList<BaseCallable> al = new ArrayList<BaseCallable>();
	al.add(unfinFilter());
	al.add(fin());
	return al;
    }
}
