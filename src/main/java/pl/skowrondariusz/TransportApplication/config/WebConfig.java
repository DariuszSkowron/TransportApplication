//package pl.skowrondariusz.TransportApplication.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.accept.ContentNegotiationManager;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
//import pl.skowrondariusz.TransportApplication.viewResolver.PdfViewResolver;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//
//
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
//        configurer
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .favorPathExtension(true);
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
//
//
//}
