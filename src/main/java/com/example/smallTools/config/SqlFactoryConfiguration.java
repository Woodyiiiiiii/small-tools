//package com.example.smallTools.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: woodyiiiiiii
// * @Date: 2022/1/1 00:03
// */
//@Configuration
//public class SqlFactoryConfiguration {
//
//    @Value("${mybatis-plus.mapper-locations}")
//    private String mapperLocations;
//
//    @Bean(name = "sqlSessionFactoryBean")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = null;
//        try {
//            // 加载JNDI配置
//            Context context = new InitialContext();
//            // 实例SessionFactory
//            sqlSessionFactoryBean = new SqlSessionFactoryBean();
//            // 配置数据源
//            sqlSessionFactoryBean.setDataSource(dataSource());
//            // 加载MyBatis配置文件
//            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//            // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
//            sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));
//        } catch (Exception e) {
//            System.out.println("创建SqlSession连接工厂错误：{}");
//        }
//        return sqlSessionFactoryBean;
//    }
//
//}
