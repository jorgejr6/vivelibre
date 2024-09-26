package com.vivelibre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
@Data
public class VivelibreResponse {

    @JsonProperty("auth-vivelibre-token")
    private String authViveLibreToken;
    private String date;
}
