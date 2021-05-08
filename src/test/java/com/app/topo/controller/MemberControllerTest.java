package com.app.topo.controller;

import com.app.topo.domain.Member;
import com.app.topo.service.MemberService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.app.topo.TopoApplication.ROOT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    void shouldFetchAllResults() throws Exception {
        //Given
        List<Member> list = List.of(
                new Member("member1"),
                new Member("member2"),
                new Member("member3")
        );

        when(memberService.getAllMembers()).thenReturn(list);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("member1")));
    }

    @Test
    void shouldFetchSingleResult() throws Exception {
        //Given
        List<Member> list = List.of(
                new Member("member1"),
                new Member("member2"),
                new Member("member3")
        );

        when(memberService.getMember(2L)).thenReturn(java.util.Optional.ofNullable(list.get(1)));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/members/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("member2")));
    }

    @Test
    void shouldReturnOkWhenCreating() throws Exception {
        // Given
        Member member = new Member("member1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(member);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnObjectWhenUpdating() throws Exception {
        // Given
        Member member = new Member("member1");

        when(memberService.saveMember(any(Member.class))).thenReturn(member);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(member);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/" + ROOT +"/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("member1")));
    }

    @Test
    void shouldReturnOkWhenDeleting() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/" + ROOT +"/members/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
