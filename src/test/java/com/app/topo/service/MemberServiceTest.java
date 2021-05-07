package com.app.topo.service;

import com.app.topo.domain.Member;
import com.app.topo.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testGetAllWalls() {
        //Given
        Member member1 = new Member("m1");
        Member member2 = new Member("m2");

        //When
        memberRepository.save(member1);
        memberRepository.save(member2);
        Long id1 = member1.getId();
        Long id2 = member2.getId();

        //Then
        assertEquals(2, memberService.getAllMembers().size());
        assertTrue(memberRepository.findById(id1).isPresent());
        assertTrue(memberRepository.findById(id2).isPresent());

        //CleanUp
        memberRepository.deleteById(id1);
        memberRepository.deleteById(id2);
    }

    @Test
    void testGetWall() {
        //Given
        Member member1 = new Member("m1");

        //When
        memberRepository.save(member1);
        Long id = member1.getId();

        //Then
        assertTrue(memberService.getMember(id).isPresent());

        //CleanUp
        memberRepository.deleteById(id);
    }

    @Test
    void testSaveWall() {
        //Given
        Member member1 = new Member("m1");

        //When
        memberService.saveMember(member1);
        Long id = member1.getId();

        //Then
        assertTrue(memberRepository.findById(id).isPresent());

        //CleanUp
        memberRepository.deleteById(id);

    }

    @Test
    void testDeleteWall() {
        //Given
        Member member1 = new Member("m1");

        //When //Then
        memberRepository.save(member1);
        Long id = member1.getId();

        assertTrue(memberRepository.findById(id).isPresent());

        memberService.deleteMember(id);

        assertFalse(memberRepository.findById(id).isPresent());

        //CleanUp
        if (memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
        }
    }
}
