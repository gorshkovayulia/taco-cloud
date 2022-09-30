package sia.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class that will be executed during startup
 */
@SpringBootApplication
public class TacoCloudApplication {

	/**
	 * Will be invoked during runtime
	 */
	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
