package com.infoshareacademy.servlet;

import com.infoshareacademy.exception.JsonNotFound;
import com.infoshareacademy.imageFileUpload.ImageUploadProcessor;
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
import java.io.IOException;


@WebServlet("/add-image")
@MultipartConfig
public class AddingImagesServlet extends HttpServlet {

    private static final Logger packageLogger = LoggerFactory.getLogger(AddingImagesServlet.class.getName());


    @Inject
    ImageUploadProcessor imageUploadProcessor;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part image = req.getPart("image");
        String imageUrl = "";
        try {
            imageUrl = "/pictures/" + imageUploadProcessor
                    .uploadImageFile(image).getName();
        } catch (JsonNotFound userImageNotFound) {
            packageLogger.warn(userImageNotFound.getMessage());
        }

    }

}


