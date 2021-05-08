package com.app.topo.controller;

import com.app.topo.domain.Rock;
import com.app.topo.service.RockService;
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
class RockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RockService rockService;

    @Test
    void shouldFetchAllResults() throws Exception {
        //Given
        List<Rock> list = List.of(
                new Rock("rock1"),
                new Rock("rock2"),
                new Rock("rock3")
        );

        when(rockService.getAllRocks()).thenReturn(list);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/rocks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("rock1")));
    }

    @Test
    void shouldFetchSingleResult() throws Exception {
        //Given
        List<Rock> list = List.of(
                new Rock("rock1"),
                new Rock("rock2"),
                new Rock("rock3")
        );

        when(rockService.getRock(2L)).thenReturn(java.util.Optional.ofNullable(list.get(1)));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/rocks/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("rock2")));
    }

    @Test
    void shouldReturnOkWhenCreating() throws Exception {
        // Given
        Rock rock = new Rock("rock1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(rock);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/rocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnObjectWhenUpdating() throws Exception {
        // Given
        Rock rock = new Rock("rock1");

        when(rockService.saveRock(any(Rock.class))).thenReturn(rock);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(rock);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/" + ROOT +"/rocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("rock1")));
    }

    @Test
    void shouldReturnOkWhenDeleting() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/" + ROOT +"/rocks/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
