package autoTest;

import autoTest.pageComponents.CalendarComponent;
import autoTest.pageComponents.ResultsModal;
import autoTest.pageComponents.StateComponent;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageObjects {
    CalendarComponent calendarComponent = new CalendarComponent();
    StateComponent stateComponent = new StateComponent();
    ResultsModal resultsModal = new ResultsModal();
    private SelenideElement
            firstNameImput = $("#firstName"),
            lastNameImput = $("#lastName"),
            emailImput = $("#userEmail"),
            numberImput = $("#userNumber"),
            adressImput = $("[id=currentAddress]"),
            uploadPicturesImput = $("#uploadPicture"),
            hobbiesInput = $("#hobbiesWrapper");

    public PageObjects openPage() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public PageObjects setFirstName(String value) {
        firstNameImput.setValue(value);

        return this;
    }

    public PageObjects setLastName(String value) {
        lastNameImput.setValue(value);

        return this;

    }

    public PageObjects setEmail(String value) {
        emailImput.setValue(value);

        return this;
    }

    public PageObjects setGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();

        return this;
    }

    public PageObjects setNumber(String value) {
        numberImput.setValue(value);

        return this;
    }

    public PageObjects setBirthday(String day, String month, String year) {
        $("#dateOfBirthInput").doubleClick();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public PageObjects setSubjekt(String subjekt_1, String subjekt_2) {
        $("#subjectsInput").click();
        $("#subjectsInput").setValue(subjekt_1).pressTab();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue(subjekt_2).pressTab();

        return this;
    }

    public PageObjects setHobbies (String hobbies) {
        hobbiesInput.find(byText(hobbies)).click();

        return this;
    }

    public PageObjects uploadPicture (String png) {
        uploadPicturesImput.uploadFromClasspath (png);

        return this;
    }

    public PageObjects setAddress (String value) {
        adressImput.setValue(value);

        return this;
    }

    public PageObjects chooseState (String state, String city){
        stateComponent.setState(state, city);

        return this;
    }

    public PageObjects verifyResultsModal () {
        resultsModal.verifyResultsModal ();

        return this;

    }

    public  PageObjects checkResults (String key, String value) {
        resultsModal.checkResults(key, value);

        return this;
    }


}

