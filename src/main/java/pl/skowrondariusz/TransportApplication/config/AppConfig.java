package pl.skowrondariusz.TransportApplication.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.skowrondariusz.TransportApplication")
public class AppConfig {


}
