package com.wanted.dto;


import lombok.*;

@Setter @Getter
@Builder
@AllArgsConstructor
public class RegistDto {

    private Long companyId;

    private String position;

    private int compensation;

    private String content;

    private String skill;

    @Override
    public String toString() {
        return "RegistDto{" +
                "companyId=" + companyId +
                ", position='" + position + '\'' +
                ", compensation=" + compensation +
                ", content='" + content + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
