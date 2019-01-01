//package pl.skowrondariusz.TransportApplication.config;
//
//
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//@EnableWebMvc
////@EnableTransactionManagement
////@ComponentScan(basePackages = "pl.skowrondariusz.TransportApplication")
//public class MvcConfig extends WebMvcConfigurerAdapter {

//    @Bean
//    @Primary
//    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        return builder.modulesToInstall(new JavaTimeModule());
//    }


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

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
//        registry.addResourceHandler("/static/images/**").addResourceLocations("classpath:/images/");
//        registry.addResourceHandler("/script/**").addResourceLocations("classpath:/script/");
//    }
//@Override
//public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler(
//            "webjars/**",
//            "images/**")
//            .addResourceLocations(
//                    "classpath:/META-INF/resources/webjars/",
//                    "classpath:/static/images/");
//
//
//}
//
//}

