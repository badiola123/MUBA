package edu.eskola.muba.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import edu.eskola.muba.characteristics.entity.Characteristics;
import edu.eskola.muba.game.entity.Game;
import edu.eskola.muba.league.entity.League;
import edu.eskola.muba.leagueconnector.entity.LeagueConnector;
import edu.eskola.muba.player.entity.Player;
import edu.eskola.muba.team.entity.Team;
import edu.eskola.muba.transaction.entity.Transaction;
import edu.eskola.muba.user.entity.User;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = { 
      @ComponentScan("edu.eskola.muba.user.dao"),
      @ComponentScan("edu.eskola.muba.user.service"), 
      @ComponentScan("edu.eskola.muba.player.dao"),
      @ComponentScan("edu.eskola.muba.player.service"),
      @ComponentScan("edu.eskola.muba.team.dao"),
      @ComponentScan("edu.eskola.muba.team.service"),
      @ComponentScan("edu.eskola.muba.league.dao"),
      @ComponentScan("edu.eskola.muba.league.service"),
      @ComponentScan("edu.eskola.muba.leagueconnector.dao"),
      @ComponentScan("edu.eskola.muba.leagueconnector.service"),
      @ComponentScan("edu.eskola.muba.characteristics.dao"),
      @ComponentScan("edu.eskola.muba.characteristics.service"),
      @ComponentScan("edu.eskola.muba.game.dao"),
      @ComponentScan("edu.eskola.muba.game.service"),
      @ComponentScan("edu.eskola.muba.transaction.dao"),
      @ComponentScan("edu.eskola.muba.transaction.service")     })
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public DataSource getDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
      factoryBean.setDataSource(getDataSource());
      
      Properties props=new Properties();
      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

      factoryBean.setHibernateProperties(props);
      factoryBean.setAnnotatedClasses(User.class,Team.class,Player.class,Characteristics.class, League.class, LeagueConnector.class, Game.class, Transaction.class);
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }
}
