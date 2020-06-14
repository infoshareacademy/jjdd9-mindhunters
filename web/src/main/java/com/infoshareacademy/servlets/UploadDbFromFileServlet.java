package com.infoshareacademy.servlets;

import com.infoshareacademy.cdi.FileUploadProcessor;
import com.infoshareacademy.cdi.JsonParserBean;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.exception.JsonNotFound;
import com.infoshareacademy.jsonSupport.CategoryJson;
import com.infoshareacademy.jsonSupport.JsonCategoryReader;
import com.infoshareacademy.mapper.DrinkMapper;
import com.infoshareacademy.service.DrinkService;
import com.infoshareacademy.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/upload-json-file")
public class UploadDbFromFileServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(UploadDbFromFileServlet.class.getName());

    @Inject
    private DrinkMapper drinkMapper;

    @Inject
    private JsonService jsonService;

    @Inject
    private DrinkService drinkService;

    @Inject
    private FileUploadProcessor fileUploadProcessor;

    @Inject
    private JsonParserBean jsonParserBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String filename = URLDecoder.decode(req.getPathInfo().substring(1), "UTF-8");
        File file = new File(fileUploadProcessor.getUploadJsonFilesPath(), filename);
        resp.setHeader("Content-Type", Files.probeContentType(file.toPath()));
        resp.setHeader("Content-Length", String.valueOf(file.length()));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        Part jsonPath = req.getPart("jsonFile");
        List<DrinkJson> drinkJsons = new ArrayList<>();
        try {
            drinkJsons = jsonParserBean.jsonDrinkReader(fileUploadProcessor.uploadJsonFile(jsonPath));
        } catch (JsonNotFound jsonNotFound) {
            packageLogger.error(jsonNotFound.getMessage());
        }

        Part jsonCatPath = req.getPart("jsonCatFile");
        List<CategoryJson> categoryJson = new ArrayList<>();
        try {
            categoryJson = JsonCategoryReader.jsonCategoryReader(fileUploadProcessor.uploadJsonFile(jsonCatPath).getPath());
        } catch (JsonNotFound jsonNotFound) {
            packageLogger.error(jsonNotFound.getMessage());
        }

        Drink drink = new Drink();
        for (DrinkJson drinkJson : drinkJsons) {
            drink = drinkMapper.toEntity(drinkJson, categoryJson.get(1));
            drinkService.save(drink);
        }
        resp.sendRedirect("/adminPage");
    }


}
