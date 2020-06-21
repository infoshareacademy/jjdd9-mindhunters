package com.infoshareacademy.email;


import com.infoshareacademy.context.ContextHolder;
import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.FullDrinkView;

import javax.ejb.Stateless;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless
public class UserDrinkProposalEmailBuilder{



    public String createContent(List<Drink> drinks) {


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<html>\n" +
                "  <head>\n" +
                "    <style>\n" +
                "      .colored {\n" +
                "        color: #ffc107;\n" +
                "      }\n" +
                "      #body {\n" +
                "        font-size: 14px;\n" +
                "      background-color: #3e1010;" +
                "        font-family: 'Cinzel', serif;" +
                "      color: #ffc107;}\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div id='body'>\n" +
                "      <p>Dear User of Mindhunters Virtual Bartender" + ",</p>\n" +
                "      <p>Following proposals were accepted by our admins:</p><ul class='colored'>Recipe: ");

        stringBuilder.append(drinks.get(0).getDrinkName().toUpperCase()).append(", created: ").append(drinks.get(0).getDate().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")));

        stringBuilder.append("</ul>" +
                "      <p>All the beast,<br>" +
                "Mindhunters Virtual Bartender<br>http://mindhunters.jjdd9.is-academy.pl/</p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>");

        return stringBuilder.toString();
    }
}
