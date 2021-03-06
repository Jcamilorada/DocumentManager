/*
 * Application.java is part of Document Manager (c) 2015.
 *
 * Document Manager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Document Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Document Manager.  If not, see <http://www.gnu.org/licenses/>.
 */

package configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Juan Camilo Rada
 *
 * Main spring configuration class. The component scan annotation defined the package to explore when a dependecy resolution
 * is required. @see <a href="http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-configuration-classes">Spring configuration documentation</a>
 */
@Configuration
@EnableAutoConfiguration
@ImportResource("search-engine-context.xml")
@EnableMongoRepositories(basePackages = "persistence.mongodb")
@ComponentScan({
    "configuration",
    "domain",
    "persistence",
    "restservices"
})
public class Application
{
    public static void main(String... args)
    {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }
}
