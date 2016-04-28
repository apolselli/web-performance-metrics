# web-performance-metrics
A web-app lib useful to collect HTTP (e.g. REST or WS) API performance metrics and expose them via JMX for monitoring purposes.

It can be included in any Java EE 6 Web Application as a maven dependency:

`<dependency>`  
`<groupId>com.github.apolselli</groupId>`  
`<artifactId>web-performance-metrics</artifactId>`  
`<version>1.1</version>`  
`</dependency>`  

and then configured and activated via web.xml:

`<filter>`  
`<filter-name>performanceFilter</filter-name>`  
`<filter-class>web.performance.PerformanceFilter</filter-class>`  
`</filter>`  
`<filter-mapping>`  
`<filter-name>performanceFilter</filter-name>`  
`<url-pattern>/*</url-pattern>`  
`</filter-mapping>`  

Best served cold with http://jolokia.org and http://hawt.io
