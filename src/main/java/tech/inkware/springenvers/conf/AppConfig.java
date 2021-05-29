/**
 * 
 */
package tech.inkware.springenvers.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import tech.inkware.springenvers.security.service.SpringSecurityAuditorAware;

//@formatter:off
/**
 * @author jeanclaude.bucumi
 * jeanclaude.bucumi@outlook.com
 */
//@formatter:on

@Configuration
public class AppConfig {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new SpringSecurityAuditorAware();
	}

}
