package ru.restaurants.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResourceTest extends AbstractControllerTest {

    @Test
    void resource () throws Exception{
        perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("text/css")))
                .andExpect(status().isOk());
    }
}
