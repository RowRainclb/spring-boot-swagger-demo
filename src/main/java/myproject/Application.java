package myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by clb on 17-3-1.
 */
@SpringBootApplication
@RestController
//@Controller
public class Application {
	@RequestMapping("/")
	public String index() {
		return "hello word!!";
	}

	@RequestMapping("/hello")
	public  String hello(){
		return "hello w!";
	}

	@RequestMapping("/user/{userName}")
	public String userParam(@PathVariable("userName") String userName){
		return String.format("user %s",userName);
	}

	@RequestMapping("/num/{number}")
	public String userParam(@PathVariable("number") int number){
		return String.format("number %s",number);
	}


	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
