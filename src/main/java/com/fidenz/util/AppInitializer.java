package com.fidenz.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fidenz.dao.Customer;
import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Kalana on May, 2018
 */
public class AppInitializer implements WebApplicationInitializer {
    public static final Logger LOGGER = Logger.getLogger(AppInitializer.class.getName());

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        File file = new File("C:\\assignment\\csv");
        if (!file.isDirectory()) {
            file.mkdir();
        } else {
            File csvFile = new File("C:\\assignment\\csv\\customers.csv");
            if (csvFile.exists()) {
                csvFile.delete();
            }
            File jsonFile = new File("C:\\assignment\\28.05.2018-JAVA.json");
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                TypeFactory typeFactory = objectMapper.getTypeFactory();
                CollectionType collectionType = typeFactory.constructCollectionType(
                        List.class, Customer.class);
                List<Customer> customers = objectMapper.readValue(jsonFile, collectionType);

                FileWriter fileWriter = new FileWriter("C:\\assignment\\csv\\customers.csv");

                String commaDelimeter = ",";
                String newLineSeperator = "\n";
                String fileHeader = "_id,index,age,eyeColor,name,gender,company,email,phone,number,street,city,state,zipcode,about,registered,latitude,longitude,tags";
                fileWriter.append(fileHeader);
                fileWriter.append(newLineSeperator);
                customers.forEach(customer -> {
                    try {

                        fileWriter.append(customer.get_id()).append(commaDelimeter);
                        fileWriter.append(customer.getIndex()).append(commaDelimeter);
                        fileWriter.append(customer.getAge()).append(commaDelimeter);
                        fileWriter.append(customer.getEyeColor()).append(commaDelimeter);
                        fileWriter.append(customer.getName()).append(commaDelimeter);
                        fileWriter.append(customer.getGender()).append(commaDelimeter);
                        fileWriter.append(customer.getCompany()).append(commaDelimeter);
                        fileWriter.append(customer.getEmail()).append(commaDelimeter);
                        fileWriter.append(customer.getPhone()).append(commaDelimeter);
                        fileWriter.append(customer.getAddress().getNumber()).append(commaDelimeter);
                        fileWriter.append(customer.getAddress().getStreet()).append(commaDelimeter);
                        fileWriter.append(customer.getAddress().getCity()).append(commaDelimeter);
                        fileWriter.append(customer.getAddress().getState()).append(commaDelimeter);
                        fileWriter.append(customer.getAddress().getZipcode()).append(commaDelimeter);
                        fileWriter.append(customer.getAbout().replaceAll("[\n\r]","")).append(commaDelimeter);
//                        fileWriter.append("ABOUT").append(commaDelimeter);
                        fileWriter.append(customer.getRegistered()).append(commaDelimeter);
                        fileWriter.append(customer.getLatitude()).append(commaDelimeter);
                        fileWriter.append(customer.getLongitude()).append(commaDelimeter);
                        fileWriter.append("\"" + String.join(",",customer.getTags()) + "\"");
                        fileWriter.append(newLineSeperator);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                });

                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    LOGGER.info("Error while flushing/closing fileWriter !!!");
                    LOGGER.error(e.getMessage(), e);
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
