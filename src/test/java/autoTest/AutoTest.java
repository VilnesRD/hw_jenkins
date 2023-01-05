package autoTest;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static io.qameta.allure.Allure.step;

public class AutoTest extends TestBase {
    Faker faker = new Faker(new Locale("en"));
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
    String[] birthday = dateFormat.format(faker.date().birthday()).split(" ");
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            emailAddress = faker.internet().emailAddress(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            birthDay = birthday[0],
            birthMonth = birthday[1],
            birthYear = birthday[2],
            userAddress = faker.address().fullAddress();

    @Test
    @Owner("Dmitry Rodichev")
    @Severity(value = io.qameta.allure.SeverityLevel.CRITICAL)
    @Story("Проверка работы страницы регистрации")
    @DisplayName("Проверка работы страницы регистрации")
    @Description("Проверка работы страницы регистрации")
    void formTest() {
        step("Открытие страницы", () -> {
            new PageObjects().openPage();
        });
        step("Заполнение формы", () -> {
            new PageObjects()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(emailAddress)
                    .setGender("Male")
                    .setNumber(phoneNumber)
                    .setBirthday(birthDay, birthMonth, birthYear)
                    .setSubjekt("Math", "Civics")
                    .setHobbies("Reading")
                    .uploadPicture("test_pictures.png")
                    .setAddress(userAddress)
                    .chooseState("Haryana", "Karnal");
        });
        step("Проверка результатов заполнения формы", () -> {
            new PageObjects().verifyResultsModal()
                    .checkResults("Student Name", firstName + " " + lastName)
                    .checkResults("Student Email", emailAddress)
                    .checkResults("Gender", "Male")
                    .checkResults("Mobile", phoneNumber)
                    .checkResults("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                    .checkResults("Subjects", "Maths, Civics")
                    .checkResults("Hobbies", "Reading")
                    .checkResults("Picture", "test_pictures.png")
                    .checkResults("Address", userAddress)
                    .checkResults("State and City", "Haryana Karnal");
        });
    }
}
