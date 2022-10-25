package hertz.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

}
