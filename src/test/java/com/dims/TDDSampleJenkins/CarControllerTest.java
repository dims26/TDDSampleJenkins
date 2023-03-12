package com.dims.TDDSampleJenkins;

import com.dims.TDDSampleJenkins.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)//Doesn't start up entire app, wires up necessary Controllers for WebMVC tests, you can specify specific classes if you don't want them all to be started up
public class CarControllerTest {
    // TYPICALLY YOU TEST CORNER CASES IN SLICE TESTS LIKE THIS BECAUSE THEY'RE FASTER TO SETUP COMPARED TO STARTING UP YOUR APPLICATION IN AN INTEGRATION TEST

    @Autowired
    //You get this because it's a Web MVC test
    private MockMvc mockMvc;
    @MockBean//pretty descriptive, mocks a bean/class
    private CarService carService;

    @Test
    public void getCar_shouldReturnCar() throws Exception{
        //If the CarService's getCarDetails() is called, this returns a specified value
        given(carService.getCarDetails(anyString())).willReturn(new Car("prius", "hybrid"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));
    }

    @Test
    public void getCar_notFound() throws Exception{
        given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isNotFound());
    }
}
