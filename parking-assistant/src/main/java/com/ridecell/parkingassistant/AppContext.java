package com.ridecell.parkingassistant;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

import com.ridecell.parkingassistant.dao.DaoProviderFactory;
import com.ridecell.parkingassistant.dao.RawDaoProvider;

//Singleton
public class AppContext {
	private static final String PROP_FILE="app.properties";
	
	public static final String PS_JDBC_URL="com.ridecell.parkingassistant.db.url";
	public static final String PS_JDBC_DRIVER="com.ridecell.parkingassistant.db.driver";
	public static final String PS_JDBC_USERNAME="com.ridecell.parkingassistant.db.username";
	public static final String PS_JDBC_PASSWORD="com.ridecell.parkingassistant.db.password";
	
	public static final String PS_DAO_IMPL_PACKAGE="com.ridecell.parkingassistant.dao.impl";
	
	private static AppContext appContext;
	 
	private Properties props;
	
	private RawDaoProvider daoProvider;
	
	private AppContext(){
		props = new Properties();
		try {
			LogManager.getLogger().info("Using prop file:"+ PROP_FILE);
			props.load(getClass().getClassLoader().getResourceAsStream(PROP_FILE));
			LogManager.getLogger().info("Done");
			LogManager.getLogger().info("Properties loaded = "+props);
		} catch (IOException e) {
			LogManager.getLogger().error("Couldn't load "+PROP_FILE+" file");
			LogManager.getLogger().error("Please make sure the file exists and/or is in your classpath!");
		}
		
		Properties connectionProps = new Properties();
		connectionProps.setProperty(RawDaoProvider.CONN_DRIVER, props.getProperty(AppContext.PS_JDBC_DRIVER));
		connectionProps.setProperty(RawDaoProvider.CONN_URL, props.getProperty(AppContext.PS_JDBC_URL));
		connectionProps.setProperty(RawDaoProvider.CONN_USERNAME, props.getProperty(AppContext.PS_JDBC_USERNAME));
		connectionProps.setProperty(RawDaoProvider.CONN_PASSWORD, props.getProperty(AppContext.PS_JDBC_PASSWORD));
		daoProvider = DaoProviderFactory.create(PS_DAO_IMPL_PACKAGE, connectionProps);
	}
	
	
	public static AppContext getDefaultContext(){
		if(appContext == null){
			appContext = new AppContext();
		}
		return appContext;
	}

	public String getProperty(String propName){
		return props.getProperty(propName);
	}

	/**
	 * @return the daoProvider
	 */
	public RawDaoProvider getDaoProvider() {
		return daoProvider;
	}
	
}
