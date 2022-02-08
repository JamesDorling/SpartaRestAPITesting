package org.sparta.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.sparta.DTOs.*;
import org.sparta.framework.logging.LogManager;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
                case TRAINEE_LIST -> {
                    dto = new TraineeDTOList();
                    dto = objectMapper.readValue(new URL(URL), TraineeDTOList.class);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        LogManager.writeLog(Level.INFO, "DTO injected from URL");
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
                case TRAINEE_LIST -> {
                    dto = new TraineeDTOList();
                    dto = objectMapper.readValue(new File(file), TraineeDTOList.class);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        LogManager.writeLog(Level.INFO, "DTO injected from file");
        return dto;
    }
}
