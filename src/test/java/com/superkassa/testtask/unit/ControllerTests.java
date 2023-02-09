package com.superkassa.testtask.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superkassa.testtask.controller.ControllerClass;
import com.superkassa.testtask.dto.RequestDto;
import com.superkassa.testtask.dto.ResponseDto;
import com.superkassa.testtask.service.ServiceClassImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ControllerClass.class)
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ServiceClassImpl serviceClass;


    @Test
    public void shouldReturnCorrectResponse() throws Exception {
        RequestDto requestDto = new RequestDto(1L, 10.0);
        ResponseDto responseDto = new ResponseDto(10.0);
        Mockito.when(serviceClass.changeValue(Mockito.any())).thenReturn(responseDto);
        mockMvc.perform(post("/modify")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current").value("10.0"));
        Mockito.verify(serviceClass, Mockito.times(1))
                .changeValue(Mockito.any());
    }

    @Test
    public void shouldReturn418WhenIdIsNull() throws Exception {
        RequestDto requestDto = new RequestDto(null, 10.0);
        mockMvc.perform(post("/modify")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isIAmATeapot());
    }

    @Test
    public void shouldReturn418WhenAddIsNull() throws Exception {
        RequestDto requestDto = new RequestDto(1L, null);
        mockMvc.perform(post("/modify")
                        .content(objectMapper.writeValueAsString(requestDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isIAmATeapot());
    }

    @Test
    public void shouldReturn418WhenIdIsNotLong() throws Exception {
        String request = "{" +
                "\"id\":\"String\"," +
                "\"add\":10}";
        mockMvc.perform(post("/modify")
                        .content(request)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isIAmATeapot());
    }

    @Test
    public void shouldReturn418WhenAddIsNotDouble() throws Exception {
        String request = "{" +
                "\"id\":1," +
                "\"add\":\"String\"}";
        mockMvc.perform(post("/modify")
                        .content(request)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isIAmATeapot());
    }

}
