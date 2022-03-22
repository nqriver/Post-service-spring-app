package pl.nqriver.restapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDto {
    private Long id;
    private String title;
    private LocalDateTime created;
}
