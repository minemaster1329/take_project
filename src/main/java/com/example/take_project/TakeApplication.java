package com.example.take_project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

//TODO Before presentation downgrade Java Version to 1.8 and WildFly server to 10.1.0
@ApplicationPath("/api")
@OpenAPIDefinition(
        info= @Info(
                title = "TAKE application",
                version = "0.1",
                description = "TAKE application API"
        )
)
public class TakeApplication extends Application {

}