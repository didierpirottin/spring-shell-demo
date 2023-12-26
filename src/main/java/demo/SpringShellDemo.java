package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class SpringShellDemo {

    public static void main(String[] args) throws Exception {
        new SpringApplication(SpringShellDemo.class).run(args);
    }
}
