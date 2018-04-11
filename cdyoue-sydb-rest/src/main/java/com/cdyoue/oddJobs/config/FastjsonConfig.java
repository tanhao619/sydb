package com.cdyoue.oddJobs.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * @ClassName: FastjsonConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @date 2017年5月12日
 */
@Configuration
public class FastjsonConfig {
	@Bean 
	public HttpMessageConverters fastJsonHttpMessageConverters() {  
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();  
		FastJsonConfig fastJsonConfig = new FastJsonConfig();  
		//fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
				 SerializerFeature.WriteMapNullValue,
				 SerializerFeature.WriteNullStringAsEmpty,
				 SerializerFeature.WriteDateUseDateFormat,
				 SerializerFeature.WriteNullNumberAsZero);

		fastConverter.setFastJsonConfig(fastJsonConfig);  
		HttpMessageConverter<?> converter = fastConverter;  
		return new HttpMessageConverters(converter);
	}  
}
