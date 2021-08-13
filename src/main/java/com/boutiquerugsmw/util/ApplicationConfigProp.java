package com.boutiquerugsmw.util;

import com.boutiquerugsmw.model.appconfigModel.BoutiqueRugsUser;
import com.boutiquerugsmw.model.appconfigModel.Scenarios;
import com.boutiquerugsmw.model.appconfigModel.ScheduledTest;
import com.boutiquerugsmw.model.appconfigModel.Selenium;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties( prefix = "app")
public class ApplicationConfigProp {

    private ScheduledTest scheduledTest;
    private Selenium selenium;
    private BoutiqueRugsUser boutiqueRugsUser;
    private Scenarios scenarios;

    @Override
    public String toString() {
        return "ApplicationConfigProp{" +
                "scheduledTest=" + scheduledTest +
                ", selenium=" + selenium +
                ", boutiqueRugsUser=" + boutiqueRugsUser +
                ", scenarios=" + scenarios +
                '}';
    }

    public ScheduledTest getScheduledTest() {
        return scheduledTest;
    }

    public void setScheduledTest(ScheduledTest scheduledTest) {
        this.scheduledTest = scheduledTest;
    }

    public Selenium getSelenium() {
        return selenium;
    }

    public void setSelenium(Selenium selenium) {
        this.selenium = selenium;
    }

    public BoutiqueRugsUser getBoutiqueRugsUser() {
        return boutiqueRugsUser;
    }

    public void setBoutiqueRugsUser(BoutiqueRugsUser boutiqueRugsUser) {
        this.boutiqueRugsUser = boutiqueRugsUser;
    }

    public Scenarios getScenarios() {
        return scenarios;
    }

    public void setScenarios(Scenarios scenarios) {
        this.scenarios = scenarios;
    }
}
