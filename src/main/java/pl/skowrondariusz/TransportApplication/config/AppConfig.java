package pl.skowrondariusz.TransportApplication.config;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import pl.skowrondariusz.TransportApplication.viewResolver.PdfViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "pl.skowrondariusz.TransportApplication")
public class AppConfig {

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        return builder.modulesToInstall(new JavaTimeModule());
    }


//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setContentNegotiationManager(manager);
//
//        // Define all possible view resolvers
//        List<ViewResolver> resolvers = new ArrayList<>();
//
////        resolvers.add(csvViewResolver());
////        resolvers.add(excelViewResolver());
//        resolvers.add(pdfViewResolver());
//
//        resolver.setViewResolvers(resolvers);
//        return resolver;
//    }
//
//    @Bean
//    public ViewResolver pdfViewResolver() {
//        return new PdfViewResolver();
//    }

}
