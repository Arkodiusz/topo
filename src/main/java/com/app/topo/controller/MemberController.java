package com.app.topo.controller;

import com.app.topo.domain.Member;
import com.app.topo.exception.MemberNotFoundException;
import com.app.topo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.topo.TopoApplication.ROOT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(ROOT)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;

    @RequestMapping(method = RequestMethod.GET, value = "/members")
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/members/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMember(id).orElseThrow(MemberNotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/members", consumes = APPLICATION_JSON_VALUE)
    public void createMember(@RequestBody Member member) {
        memberService.saveMember(member);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/members", consumes = APPLICATION_JSON_VALUE)
    public Member updateMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/members/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
