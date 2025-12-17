package com.gerenciamento.domain.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiKey {

    @Id
    private String id;

    private String value;

    private Instant expiresWhen;

    @CreatedDate
    private Instant createdWhen;

    public boolean isExpired(Instant now) {
        return now.isAfter(expiresWhen);
    }

}
