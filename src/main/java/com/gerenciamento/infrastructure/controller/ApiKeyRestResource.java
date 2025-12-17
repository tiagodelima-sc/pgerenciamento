package com.gerenciamento.infrastructure.controller;

import com.gerenciamento.domain.applicationservice.ApiKeyService;
import com.gerenciamento.domain.document.ApiKey;
import com.gerenciamento.infrastructure.dto.ApiKeyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.gerenciamento.infrastructure.controller.RestConstants.PATH_API_KEYS;

@RestController
@RequestMapping(PATH_API_KEYS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ApiKeyRestResource {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyDTO> createApiKey() {
        ApiKey apiKey = apiKeyService.createApiKey();

        return ResponseEntity
                .created(URI.create(PATH_API_KEYS + apiKey.getId()))
                .body   (ApiKeyDTO.create(apiKey));
    }

    @PutMapping("{id}/revoke")
    public ResponseEntity<Void> revokeApikey(@PathVariable("id") String id) {
        apiKeyService.revokeApiKey(id);

        return ResponseEntity.noContent().build();
    }
}
