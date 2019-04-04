package com.example.demo.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(UploadDocumentService.Properties.class)
public class IndexMetaDataService {

    void upload(){}

    @Getter
    @Setter
    @ConfigurationProperties("documents")
    static class Properties {
        String index;
    }
}
