package server.sopt.week2.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.clientconfig.HttpClient5FeignConfiguration;

@ImportAutoConfiguration({FeignAutoConfiguration.class, HttpClient5FeignConfiguration.class})
public class FegionConfig {
}
