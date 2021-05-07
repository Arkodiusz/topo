package com.app.topo.service;

import com.app.topo.domain.Member;
import com.app.topo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMember(final Long id) {
        return memberRepository.findById(id);
    }

    public Member saveMember(final Member member) {
        return memberRepository.save(member);
    }

    public void deleteMember(final Long id) {
        memberRepository.deleteById(id);
    }
}
