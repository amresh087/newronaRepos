package com.newrona.config;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;



@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.newrona")
@EnableWebMvc
@EnableAspectJAutoProxy
public class ApplicationConfig extends WebMvcConfigurerAdapter
{
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_NAME_HIBERNATE_HBM2DDLAUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_HIBERNATE_USE_UNICODE = "hibernate.connection.useUnicode";
	private static final String PROPERTY_NAME_HIBERNATE_CHARACTER_ENCODING = "hibernate.connection.characterEncoding";
	private static final String PROPERTY_NAME_HIBERNATE_CHARSET = "hibernate.connection.charSet";
	
	@Resource
	private Environment env;
	
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21
	 * @return
	 */
	
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new Hibernate4Module());

        messageConverter.setObjectMapper(mapper);
        return messageConverter;

    }

	
	
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21
	 */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	
        converters.add(jacksonMessageConverter());
        converters.add(new StringHttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
        
        super.configureMessageConverters(converters);
    }

	
	
/**
 * @author  Amresh verma
 * @version 2.0
 * @since   2017-03-21 
 * @return
 */
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		

		return viewResolver;
	}

	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21
	 * @return
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(this.restDataSource());
		sessionFactory.setPackagesToScan(new String[] { env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN) });
		sessionFactory.setHibernateProperties(this.hibernateProperties());
		return sessionFactory;
	}
/**
 * @author  Amresh verma
 * @version 2.0
 * @since   2017-03-21 
 * @return
 */
	@Bean
	public DataSource restDataSource()
	{
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return dataSource;
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */

	final Properties hibernateProperties()
	{
		return new Properties()
		{
			private static final long serialVersionUID = 1L;
			{
				this.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
				this.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
				this.put(PROPERTY_NAME_HIBERNATE_HBM2DDLAUTO, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDLAUTO));
				this.put(PROPERTY_NAME_HIBERNATE_USE_UNICODE, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_USE_UNICODE));
				this.put(PROPERTY_NAME_HIBERNATE_CHARACTER_ENCODING, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CHARACTER_ENCODING));
				this.put(PROPERTY_NAME_HIBERNATE_CHARSET, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CHARSET));
				
			}
		};
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */
	@Bean
	public DataSourceInitializer dataSourceInitializer()
	{
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(restDataSource());
		return initializer;
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */
	@Bean
	public HibernateTransactionManager transactionManager()
	{
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(this.sessionFactory().getObject());
		return txManager;
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 * @throws VelocityException
	 * @throws IOException
	 */
	@Bean
	public VelocityEngine getVelocityEngine() throws VelocityException, IOException
	{
		VelocityEngineFactory factory = new VelocityEngineFactory();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */
	@Bean
	public JavaMailSender javaMailService()
	{
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(env.getRequiredProperty("mail.smtp.host"));
		javaMailSender.setPort(Integer.parseInt(env.getRequiredProperty("mail.smtp.port")));
		javaMailSender.setUsername(env.getRequiredProperty("newrona.classteacher.noreply.mail"));
		javaMailSender.setPassword(env.getRequiredProperty("newrona.classteacher.noreply.password"));

		javaMailSender.setJavaMailProperties(getMailProperties());

		return javaMailSender;
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */
	private Properties getMailProperties()
	{
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", env.getRequiredProperty("mail.smtp.auth"));
		properties.setProperty("mail.smtp.starttls.enable", env.getRequiredProperty("mail.smtp.starttls.enable"));
		properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		return properties;
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	{
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties property = new Properties();
		property.put("com.newrona.controller.ApplicationExceptionHandler", "error");
		simpleMappingExceptionResolver.setExceptionMappings(property);
		return simpleMappingExceptionResolver;
	}
	/**
	 * @author  Amresh verma
	 * @version 2.0
	 * @since   2017-03-21 
	 * @return
	 */
	@Bean
	public MultipartResolver multipartResolver()
	{
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(150000000);
		return multipartResolver;
	}

}