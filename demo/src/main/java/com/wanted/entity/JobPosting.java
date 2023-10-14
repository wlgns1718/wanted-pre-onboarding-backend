package com.wanted.entity;


import com.wanted.dto.RegistDto;
import com.wanted.dto.UpdateDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor
public class JobPosting {

    @Id @Column(name = "JOB_POSTING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobPostingId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "COMPANY")
    private Company company;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "COMPENSATION")
    private int compensation;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SKILL")
    private String skill;

    public static JobPosting toEntity(Company company, RegistDto registDto){
        return JobPosting.builder()
                .company(company)
                .position(registDto.getPosition())
                .compensation(registDto.getCompensation())
                .content(registDto.getContent())
                .skill(registDto.getSkill())
                .build();
    }
}
