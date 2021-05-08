package com.app.topo.controller;

import com.app.topo.domain.Route;
import com.app.topo.service.RouteService;
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
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    void shouldFetchAllResults() throws Exception {
        //Given
        List<Route> list = List.of(
                new Route("route1", "IV"),
                new Route("route2", "V"),
                new Route("route3", "VI")
        );

        when(routeService.getAllRoutes()).thenReturn(list);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/routes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("route1")));
    }

    @Test
    void shouldFetchSingleResult() throws Exception {
        //Given
        List<Route> list = List.of(
                new Route("route1", "IV"),
                new Route("route2", "V"),
                new Route("route3", "VI")
        );

        when(routeService.getRoute(2L)).thenReturn(java.util.Optional.ofNullable(list.get(1)));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/" + ROOT +"/routes/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("route2")));
    }

    @Test
    void shouldReturnOkWhenCreating() throws Exception {
        // Given
        Route route = new Route("route1", "IV");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(route);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnObjectWhenUpdating() throws Exception {
        // Given
        Route route = new Route("route1", "IV");

        when(routeService.saveRoute(any(Route.class))).thenReturn(route);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(route);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/" + ROOT +"/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("route1")));
    }

    @Test
    void shouldReturnOkWhenDeleting() throws Exception {
        //Given & When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/" + ROOT +"/routes/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
