package com.wanted.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id @Column(name = "COMPANY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NATION")
    private String nation;

    @Column(name = "REGION")
    private String region;



}
