package com.example.ntssocketTest.model;
import lombok.*;

import javax.persistence.*;

@Entity(name = "content")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String content;

    public ContentEntity(String content) {
        this.content=content;
    }
}
