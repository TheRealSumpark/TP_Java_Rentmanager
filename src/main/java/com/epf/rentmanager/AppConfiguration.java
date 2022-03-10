package com.epf.rentmanager;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.epf.rentmanager.dao","com.epf.rentmanager.service","com.epf.rentmanager.persistence","com.epf.rentmanager.utils"})

public class AppConfiguration  {
}
