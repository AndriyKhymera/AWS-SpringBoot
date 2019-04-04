package com.example.demo.Conroller;

import com.example.demo.Model.Document;
import com.example.demo.Service.IndexMetaDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
@AllArgsConstructor
public class IndexMetaDataResource {

    IndexMetaDataService indexMetaDataService;

    @PostMapping("_upload")
    public void upload(@RequestBody Document document){
        return indexMetaDataService.upload(document);
    }
}
