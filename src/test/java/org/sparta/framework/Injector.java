package org.sparta.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sparta.DTOs.CourseDTO;
import org.sparta.DTOs.DTO;
import org.sparta.DTOs.DTOEnum;
import org.sparta.DTOs.TraineeDTO;

import java.io.File;
import java.net.URL;

public class Injector {


    public static DTO injectDTO(String URL, DTOEnum dtoType) {

        DTO dto = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            switch (dtoType) {
                case TRAINEE -> {
                    dto =  new TraineeDTO();
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
