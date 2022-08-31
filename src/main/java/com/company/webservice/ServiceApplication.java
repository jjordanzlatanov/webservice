package com.company.webservice;

import com.company.webservice.health.BasicHealthCheck;
import com.company.webservice.resources.BlockResource;
import com.company.webservice.resources.EmployeeResource;
import com.company.webservice.resources.SystemResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class ServiceApplication extends Application<ServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }

    @Override
    public void run(ServiceConfiguration serviceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(serviceConfiguration);
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, serviceConfiguration.getDataSourceFactory(), "wbservice");
        environment.healthChecks().register("basic", new BasicHealthCheck());
        environment.jersey().register(new BlockResource(jdbi));
        environment.jersey().register(new SystemResource(jdbi));
        environment.jersey().register(new EmployeeResource(jdbi));
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ServiceConfiguration serviceConfiguration) {
                return serviceConfiguration.getDataSourceFactory();
            }
        });
    }
}
