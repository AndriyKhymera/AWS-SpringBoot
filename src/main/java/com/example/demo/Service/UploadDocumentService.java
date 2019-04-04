package com.example.demo.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.example.demo.Model.Document;
import com.example.demo.Model.Ticket;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Slf4j
@Component
@AllArgsConstructor
@EnableConfigurationProperties(UploadDocumentService.Properties.class)
public class UploadDocumentService {

    private static final DateTimeFormatter EXPIRATION_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            .withZone(ZoneId.systemDefault());

    Properties properties;
    AmazonS3 storageClient;

    Ticket generateUr(Document document) {
        log.info("Generate URL for '{}'", document);

        String bucket = properties.getBucket();
        String key = document.getOwner() + "/" + document.getId();
        log.info("Use key '{}' and bucket '{}'", key, bucket);

        Instant expiration = Instant.now().plusSeconds(properties.getUploadTimeout());
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(Date.from(expiration));
        String url = storageClient.generatePresignedUrl(request).toString();

        Ticket ticket = Ticket.builder()
                .url(url)
                .expiration(EXPIRATION_FORMATTER.format(expiration))
                .build();
        log.info("Generated ticket '{}'", ticket);

        return ticket;
    }

    @Getter
    @Setter
    @ConfigurationProperties("documents")
    static class Properties {
        String bucket;
        int uploadTimeout;
    }

}
