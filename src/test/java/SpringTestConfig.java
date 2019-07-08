import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.skowrondariusz.TransportApplication.security.recaptcha.ReCaptchaService;

import static org.mockito.Mockito.mock;

@Configuration
public class SpringTestConfig
{
    
    
    @Bean
    ReCaptchaService reCaptchaService(){
        return mock(ReCaptchaService.class);
    }
}
