package rst.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * 产生一个配置属性，如果在命令行指定了process.code属性，则从GMP服务中取对应的配置信息，
 * 否则（例如本机开发环境）则取 {@link #setLocations(org.springframework.core.io.Resource[])}中指定的文件，就像 {@link PropertiesFactoryBean}一样。
 * @author chenjw
 *
 */
public class EnvironmentFactoryBean extends PropertiesLoaderSupport implements FactoryBean<Properties> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Properties getObject() throws Exception {

		Properties props = new Properties();
		
		Map<String, String> env = System.getenv();
		
		if (StringUtils.isNotEmpty(env.get("instanceName")) && !"standalone".equals(env.get("instanceName").toLowerCase()))
		{
			logger.info("发现 instanceName环境变量，开始从环境变量取实例配置。");
			props.putAll(System.getenv());
		}
		else
		{
			//直接从配置的本地文件取值
			loadProperties(props);
			InputStream inputStream = this.getClass().getResourceAsStream("classpath:sys.properties");
			BufferedReader bf = new BufferedReader(new    InputStreamReader(inputStream));  
			props.load(bf);  
		}
		//命令行上的参数更优先
		props.putAll(System.getProperties());
		showKeysAndValues(props);
		return props;
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	
	 /** 
     * @param properties 
     */  
    private void showKeysAndValues(Properties properties) {  
        Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();  
        while (it.hasNext()) {  
            Entry<Object, Object> entry = it.next();  
            Object key = entry.getKey();  
            Object value = entry.getValue();  
            System.out.println( key+"=[" + value+"]");  
            System.out.println("---------------");  
        }  
    }  
}