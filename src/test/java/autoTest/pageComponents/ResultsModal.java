package autoTest.pageComponents;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsModal {
    public void verifyResultsModal () {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

    }

    public void checkResults (String key, String value) {
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));

    }
}
