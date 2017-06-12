package my.company;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DummySiteTest {

    @Test
    public void demoTest() {

        //Settings to run tests in Chrome driver
        Configuration.browser="chrome";
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        //Go to test website
        open("http://demoqa.com/");

        //Go to registration page: click on Registration button
        $("#menu-item-374").click();

        //Make sure registration form is shown
        $("#pie_register_reg_form").shouldBe(visible);

        //Fill in registration form:
        //Fill in the first name and last name
        $("#name_3_firstname").setValue("Sasha");
        $("#name_3_lastname").setValue("Zubal");

        //Select radio button. Here you search for an element by the classname "radio_wrap",
        // then you move deeper into hierarchy and look for an element with tag "input" and select such an element with value = married
        $(".radio_wrap input").selectRadio("married");

        //Select checkbox
        $(byAttribute("value", "reading")).setSelected(true);

        //Select an option from a dropdown
        $("#dropdown_7").selectOption("Ukraine");
        $("#mm_date_8").selectOption("1");
        $("#dd_date_8").selectOption("19");
        $("#yy_date_8").selectOption("1986");

        //Set phone number
        $(byName("phone_9")).setValue("0630000000");

        //A very simplified approach to ensuring the username uniqueness.
        // Note: you consider other approaches such as databases clean up before test run
        //Create a variable which always starts with "lits" and ends with 9 random numeric symbols, e.g. lits123456789
        String username = "lits" + RandomStringUtils.randomNumeric(9);
        $(byText("Username")).parent().find(By.tagName("input")).setValue(username);

        //Email starts with whatever is stored in username variable and ends with @example.com, e.g. lits123456789@example.com
        $("#email_1").setValue(username + "@example.com");

        //Enter description into text area
        $("#description").setValue("We are running a Selenide demo");

        //Enter password and confirm password
        $("#password_2").setValue("123456789");
        $("#confirm_password_password_2").setValue("123456789");

        //Set path profile picture. It is not needed to use Browse button and select such a path, you set it directly into the field
        $("#profile_pic_10").setValue("C:\\Users\\Oleksandra_Zubal\\Pictures\\FullSizeRender.jpg");

        //Click on Register button
        $(byAttribute("name", "pie_submit")).click();

        //Alternatively, it is possible to press Submit button by classname. Compound (i.e. consisting of more than 1 word) classnames should be separate by '.' and NOT by space.
        // To try it out, comment the line above and uncomment a line below
        //$(".fieldset.piereg_submit_button").find(By.tagName("input")).click();

        //In case of failure, screenshot will be automatically taken.
        //But you can scroll to a particular element to make sure it is visible on the screenshot
        //$("#pie_register_reg_form").scrollTo();

        //Verify successful message
        $(".piereg_message").shouldBe(visible, text("Thank you for your registration"));

    }

}
