import java.util.Map;
import com.paic.tsds.tool.template.dto.SwapDto;
import com.paic.tsds.tool.template.service.schedule.BaseCallable;
import com.paic.tsds.tool.template.service.schedule.EndLogic;
import com.paic.tsds.tool.template.service.schedule.ScheduleMap;
import com.paic.tsds.tool.template.utils.TemplateConstants;

public class TestScheduleCallable {

    public static BaseCallable sleepTask() {
	return new BaseCallable() {
	    private long sleepTime = 3000l;

	    public SwapDto execute(SwapDto inSwapDto) {
		Map param = (Map) inSwapDto.getData();
		try {
		    Thread.sleep(sleepTime);
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
	sm.putSchedule(1, new Long(5000), EndLogic.ALLSUCCESS);
	sm.putSchedule(2, new Long(2000), EndLogic.ALLFAIL);

	sm.putCallable(1, sleepTask());
	sm.putCallable(1, sleepTask());
	sm.putCallable(1, sleepTask());
	sm.putCallable(2, sleepTask());
	sm.putCallable(2, sleepTask());

	return sm;
    }

}
