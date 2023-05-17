package WeatherWearsYou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WeatherWearsYouApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherWearsYouApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("http://ec2-52-78-226-157.ap-northeast-2.compute.amazonaws.com:8080/");
			}
		};
	}
}
