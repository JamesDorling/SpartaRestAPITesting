package org.sparta.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sparta.DTOs.TraineeDTO;

import java.io.File;
import java.net.URL;

public class Injector {

    public static TraineeDTO injectDTO(String URL) {
        TraineeDTO traineeDTO = new TraineeDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            traineeDTO = objectMapper.readValue(new URL(URL), TraineeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return traineeDTO;
    }

    public static TraineeDTO injectDTOFromFile(String file) {
        TraineeDTO traineeDTO = new TraineeDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            traineeDTO = objectMapper.readValue(new File(file), TraineeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return traineeDTO;
    }
}
