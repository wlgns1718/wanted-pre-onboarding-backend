package com.wanted.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class UpdateDto {

    private Long jobPostingId;

    private String position;

    private int compensation;

    private String content;

    private String skill;

    @Override
    public String toString() {
        return "UpdateDto{" +
                "jobPostingId=" + jobPostingId +
                ", position='" + position + '\'' +
                ", compensation=" + compensation +
                ", content='" + content + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
