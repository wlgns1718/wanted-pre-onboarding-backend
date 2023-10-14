package com.wanted.entity;


import com.wanted.dto.ApplyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Builder @AllArgsConstructor
public class Apply {

    @Id @Column(name = "APPLY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private JobPosting jobPosting;

    public static Apply toEntity(User user, JobPosting jobPosting){
        return Apply.builder()
                .user(user)
                .jobPosting(jobPosting)
                .build();
    }

}
