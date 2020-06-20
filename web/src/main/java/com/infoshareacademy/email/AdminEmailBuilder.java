package com.infoshareacademy.email;

import javax.ejb.Stateless;

@Stateless
public class AdminEmailBuilder implements EmailBuildStrategy {


    @Override
    public String createContent() {

        String html = "<html>\n" +
                "  <head>\n" +
                "    <style>\n" +
                "      .colored {\n" +
                "        color: blue;\n" +
                "      }\n" +
                "      #body {\n" +
                "        font-size: 14px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id='body'>\n" +
                "      <p>Hi Pierce,</p>\n" +
                "      <p class='colored'>This text is blue.</p>\n" +
                "      <p>Jerry</p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";


        return html;
    }
}
