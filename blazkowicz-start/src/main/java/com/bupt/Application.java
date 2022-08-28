package com.bupt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages = {"com.bupt"})
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class,args);
    }
}
