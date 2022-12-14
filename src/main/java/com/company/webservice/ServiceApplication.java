package com.company.webservice;

import com.company.webservice.core.JWTAuthenticator;
import com.company.webservice.core.LocalDateConverterProvider;
import com.company.webservice.core.LocalDateTimeConverterProvider;
import com.company.webservice.core.Token;
import com.company.webservice.health.BasicHealthCheck;
import com.company.webservice.resources.*;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
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

        environment.jersey().register(new LocalDateTimeConverterProvider());
        environment.jersey().register(new LocalDateConverterProvider());

        environment.jersey().register(new BlockResource(jdbi));
        environment.jersey().register(new SystemResource(jdbi));
        environment.jersey().register(new EmployeeResource(jdbi));
        environment.jersey().register(new TechnicalRequestResource(jdbi));
        environment.jersey().register(new ActivityResource(jdbi));

        environment.jersey().register(new TechnicalRequestBlockXrefResource(jdbi));
        environment.jersey().register(new TechnicalRequestSystemXrefResource(jdbi));
        environment.jersey().register(new TechnicalRequestActivityXrefResource(jdbi));

        environment.jersey().register(new UserResource(jdbi));

        environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<Token>().setAuthenticator(new JWTAuthenticator()).setPrefix("bearer").buildAuthFilter()));
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
