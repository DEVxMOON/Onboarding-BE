package com.hr.onboardingkt.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("Onboarding-HR-kt")
                .description("onboarding submission")
                .version("1.0.0")
        )
}
