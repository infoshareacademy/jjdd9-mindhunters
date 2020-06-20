package com.infoshareacademy.email;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;

@Local
public interface EmailBuildStrategy {

    String createContent();

}
