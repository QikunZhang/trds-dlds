import java.util.Map;

import com.paic.tsds.tool.template.dto.SwapDto;
import com.paic.tsds.tool.template.service.schedule.BaseCallable;
import com.paic.tsds.tool.template.service.schedule.EndLogic;
import com.paic.tsds.tool.template.service.schedule.ScheduleMap;
import com.paic.tsds.tool.template.utils.TemplateConstants;

public class TestScheduleCallable {

    public static BaseCallable sleepTask() {
	return new BaseCallable() {
	    private long sleepTime = 30l;

	    public SwapDto execute(SwapDto inSwapDto) {
		Map param = (Map) inSwapDto.getData();
		try {
		    Thread.sleep(sleepTime);
		    System.out.println("lalalala");
		    ((Map) param.get("tmpMap")).put(sleepTime, "true");
		} catch (InterruptedException e) {
		    ((Map) param.get("tmpMap")).put(sleepTime, "false");

		    return new SwapDto(TemplateConstants.SWAP_CODE_ERROR, "ERROR", null);
		}
		return new SwapDto(TemplateConstants.SWAP_CODE_SUCCESS, "SUCCESS", null);
	    }
	};
    }

    public static ScheduleMap releaseSchedule() {
	ScheduleMap sm = new ScheduleMap();
	sm.putSchedule(1, new Long(500), EndLogic.ALLSUCCESS);
	sm.putSchedule(2, new Long(200), EndLogic.ALLFAIL);

	sm.putCallable(1, sleepTask());
	sm.putCallable(1, sleepTask());
	sm.putCallable(1, sleepTask());
	sm.putCallable(2, sleepTask());
	sm.putCallable(2, sleepTask());

	return sm;
    }

}
