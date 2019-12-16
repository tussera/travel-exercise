package com.afkl.travel.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.afkl.travel.exercise.converter.LocationTypeStringConverter;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MeterRegistry meterRegistry;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocationTypeStringConverter());
        addCustomMetrics();
    }

    private void addCustomMetrics() {
        Counter.builder("custom.request.processed.total")
                .description("Total number of requests processed")
                .register(meterRegistry);
        Counter.builder("custom.request.ok.total")
                .description("Total number of requests resulted in an OK response")
                .register(meterRegistry);
        Counter.builder("custom.request.4xx.total")
                .description("Total number of requests resulted in a 4xx response")
                .register(meterRegistry);
        Counter.builder("custom.request.5xx.total")
                .description("Total number of requests resulted in a 5xx response")
                .register(meterRegistry);
        Timer.builder("custom.request.response.time.avg")
                .description("Average response time of all requests")
                .register(meterRegistry);
        Timer.builder("custom.request.response.time.max")
                .description("Max response time of all requests")
                .register(meterRegistry);
    }
}
