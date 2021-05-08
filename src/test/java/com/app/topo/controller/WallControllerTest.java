package com.app.topo.controller;

import com.app.topo.domain.Wall;
import com.app.topo.service.WallService;
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
class WallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WallService wallService;

    @Test
    void shouldFetchAllResults() throws Exception {
        //Given
        List<Wall> list = List.of(
                new Wall("wall1"),
                new Wall("wall2"),
                new Wall("wall3")
        );

        when(wallService.getAllWalls()).thenReturn(list);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/walls")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("wall1")));
    }

    @Test
    void shouldFetchSingleResult() throws Exception {
        //Given
        List<Wall> list = List.of(
                new Wall("wall1"),
                new Wall("wall2"),
                new Wall("wall3")
        );

        when(wallService.getWall(2L)).thenReturn(java.util.Optional.ofNullable(list.get(1)));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/walls/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("wall2")));
    }

    @Test
    void shouldReturnOkWhenCreating() throws Exception {
        // Given
        Wall wall = new Wall("wall1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(wall);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/walls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnObjectWhenUpdating() throws Exception {
        // Given
        Wall wall = new Wall("wall1");

        when(wallService.saveWall(any(Wall.class))).thenReturn(wall);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(wall);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/" + ROOT +"/walls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("wall1")));
    }

    @Test
    void shouldReturnOkWhenDeleting() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/" + ROOT +"/walls/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
