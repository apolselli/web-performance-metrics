package web.performance;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("performanceStatistics")
@ApplicationScoped
public class PerformanceStatisticsBean {
	private Map<String, PerformanceStatistic> statistics = new HashMap<>();
	
	public void logTime(String httpMethod, String resourceName, int statusCode, long time) {
		String key = httpMethod + " " + resourceName + " " + statusCode;
		PerformanceStatistic statistic = statistics.get(key);
		if (statistic == null) {
			statistic = new PerformanceStatistic(httpMethod, resourceName, statusCode, time);
			statistic.registerInJMX();
			statistics.put(key, statistic);
		} else {
			statistic.addTime(time);
		}
	}

    @PreDestroy
    public void unregisterStatisticsFromJMX() {
    	for(PerformanceStatistic statistic : statistics.values()) statistic.unregisterFromJMX();
    }

}