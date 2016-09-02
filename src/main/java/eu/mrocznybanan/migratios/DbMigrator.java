package eu.mrocznybanan.migratios;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

import com.googlecode.flyway.core.Flyway;
import com.googlecode.flyway.core.api.MigrationInfo;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class DbMigrator {

    Logger log = Logger.getLogger(this.getClass().getName());

    @Resource(lookup = "java:jboss/datasources/ExampleDS")
    DataSource dataSource;

    @PostConstruct
    private void onStartup() {
        if (dataSource == null) {
            log.severe("no datasource found to execute the db migrations!");
            throw new EJBException("no datasource found to execute the db migrations!");
        }

        Flyway flyway = new Flyway();
        flyway.setInitOnMigrate(true);
        flyway.setDataSource(dataSource);
        for (MigrationInfo migration : flyway.info().all()) {
            log.info("migrate task: " + migration.getVersion() + " : " + migration.getDescription() + " from file: " + migration.getScript());
        }
        flyway.migrate();
    }

}
