package web.performance;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerformanceStatistic implements PerformanceStatisticMXBean {
	private Logger log = LoggerFactory.getLogger(this.getClass());

    private MBeanServer platformMBeanServer;
    private ObjectName objectName;

	private String httpMethod;
	private String resourceName;
	private int statusCode;
	
	private long totalTime;
	private long minTime;
	private long maxTime;
	private long lastTime;
	private long count;

	public PerformanceStatistic(String httpMethod, String resourceName, int statusCode, long time) {
		this.httpMethod = httpMethod;
		this.resourceName = resourceName;
		this.statusCode = statusCode;
		
		this.totalTime = time;
		this.minTime = time;
		this.maxTime = time;
		this.lastTime = time;
		this.count = 1;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public String getResourceName() {
		return resourceName;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public long getMinTime() {
		return minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public long getLastTime() {
		return lastTime;
	}

	public long getCount() {
		return count;
	}

	public long getAverageTime() {
		return totalTime / count;
	}

	public void addTime(long time) {
		lastTime = time;
		totalTime += time;
		if(time < minTime) minTime = time;
		if(time > maxTime) maxTime = time;
		count++;
	}
	
    protected void registerInJMX() {
        try {
            objectName = new ObjectName("web.performance:type=PerformanceStatistic,name=" + this);
            platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
            platformMBeanServer.registerMBean(this, objectName);
        } catch (Exception e) {
        	log.error("Problem during registration of Performance Statistic into JMX", e);
        }
    }

    protected void unregisterFromJMX() {
        try {
            platformMBeanServer.unregisterMBean(this.objectName);
        } catch (Exception e) {
        	log.error("Problem during unregistration of Performance Statistic into JMX", e);
        }
    }

}