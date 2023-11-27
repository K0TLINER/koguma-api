package com.fiveguys.koguma.data.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name = "member_relationships")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class MemberRelationship {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @Column(name = "source_member_id")
    private Member sourceMemberId;

    @ManyToOne
    @Column(name = "target_member_id")
    private Member targetMemberId;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private Boolean type;


    @Builder
    public MemberRelationship memberRelationship(Member sourceMemberId, Member targetMemberId, String content, Boolean type) {
        this.sourceMemberId = sourceMemberId;
        this.targetMemberId = targetMemberId;
        this.content = content;
        this.type = type;

        return null;
    }
}

