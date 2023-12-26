package demo.services;

import org.springframework.stereotype.Service;

@Service
public class CodeGenerationService {

    public String generateCode() {
        return """
                package demo.hello;
                                
                public class HelloWorld {
                                
                    public static void main(String[] args) {
                        System.out.println("Hello World!");
                        System.out.println("Have a great day!");
                    }
                }
                """;
    }

}
