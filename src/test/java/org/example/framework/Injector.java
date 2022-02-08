package org.example.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTOs.CourseDTO;
import org.example.DTOs.DTO;
import org.example.DTOs.DTOEnum;
import org.example.DTOs.TraineeDTO;

import java.io.File;
import java.net.URL;

public class Injector {


    public static DTO injectDTO(String URL, DTOEnum dtoType) {

        DTO dto = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            switch (dtoType) {
                case TRAINEE -> {
                    dto = new TraineeDTO();
                    dto = objectMapper.readValue(new URL(URL), TraineeDTO.class);
                }
                case COURSE -> {
                    dto = new CourseDTO();
                    dto = objectMapper.readValue(new URL(URL), CourseDTO.class);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return dto;
    }

    public static DTO injectDTOFromFile(String file, DTOEnum dtoType) {

        DTO dto = null;
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            switch (dtoType) {
                case TRAINEE -> {
                    dto = new TraineeDTO();
                    dto = objectMapper.readValue(new File(file), TraineeDTO.class);
                }
                case COURSE -> {
                    dto = new CourseDTO();
                    dto = objectMapper.readValue(new File(file), CourseDTO.class);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return dto;
    }

}
