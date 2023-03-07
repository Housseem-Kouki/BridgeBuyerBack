package com.example.demandeachatservice.Controller;

import com.example.demandeachatservice.Services.DemandeAchat.Article.ExportArticleExcel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController

public class ExportController {

    private final ExportArticleExcel exportArticleExcel;

    public ExportController(ExportArticleExcel exportArticleExcel) {
        this.exportArticleExcel = exportArticleExcel;
    }

    @GetMapping(value = "/export/articles", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<InputStreamResource> exportArticles() throws IOException {
        ByteArrayInputStream in = exportArticleExcel.exportArticles();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=articles.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

}
