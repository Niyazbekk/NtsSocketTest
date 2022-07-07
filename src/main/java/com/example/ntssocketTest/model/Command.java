package com.example.ntssocketTest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Command {
    @JsonProperty
    private String command;
    @JsonProperty
    private String content;
}
