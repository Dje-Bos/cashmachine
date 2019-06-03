package org.sut.cashmachine.config;

//import org.eclipse.persistence.config.PersistenceUnitProperties;
//import org.eclipse.persistence.jpa.PersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//import static org.eclipse.persistence.config.PersistenceUnitProperties.NONE;

//@Configuration
public class EclipseLinkJpaConfiguration {

    @Resource
    private DataSource dataSource;
    @Bean
    protected AbstractJpaVendorAdapter jpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

     Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<>();
//        map.put(PersistenceUnitProperties.WEAVING, "true");
//        map.put(PersistenceUnitProperties.DDL_GENERATION, NONE);
        map.put("eclipselink.logging.level.sql", "FINE");
        map.put("eclipselink.logging.parameters", "true");
        return map;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        localContainerEntityManagerFactoryBean.setPersistenceUnitName("cashmachine");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setLoadTimeWeaver(loadTimeWeaver());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(getVendorProperties());
        localContainerEntityManagerFactoryBean.setPackagesToScan("org.sut.cashmachine.model");
//        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(PersistenceProvider.class);

        localContainerEntityManagerFactoryBean.afterPropertiesSet();
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() {
        return new InstrumentationLoadTimeWeaver();
    }
}
