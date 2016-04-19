package web.performance;

public interface PerformanceStatisticMXBean {
	
	public String getHttpMethod();
	public String getResourceName();
	public int getStatusCode();
	public long getMinTime();
	public long getMaxTime();
	public long getLastTime();
	public long getAverageTime();
	public long getCount();
	
}