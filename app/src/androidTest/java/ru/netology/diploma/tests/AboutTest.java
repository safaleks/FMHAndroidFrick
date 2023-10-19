package ru.netology.diploma.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.diploma.elements.AuthorizationScreen;
import ru.netology.diploma.steps.AboutSteps;
import ru.netology.diploma.steps.AuthorizationSteps;
import ru.netology.diploma.steps.ClaimsSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования вкладки О приложении")
public class AboutTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void Authorization () {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            AuthorizationScreen.textAuthorization();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.textAuthorization();
        AuthorizationSteps.clickLoginField();
        AuthorizationSteps.clickPasswordField();
        SystemClock.sleep(1000);
        AuthorizationScreen.clickButton(AuthorizationScreen.getAuthorizationElementsButton());
    }

    @After
    public void Exit () {
        SystemClock.sleep(2000);
        AuthorizationScreen .clickButtonExit(AuthorizationScreen.getAuthorizationElementsButtonExit());
        SystemClock.sleep(2000);
        AuthorizationSteps.clickButtonLogOut();
    }

//  Тест-кейсы для проведения функционального тестирования вкладки "О приложении" мобильного приложения "Мобильный хоспис".

    //  TC - 74 - Просмотр ссылки "Политика конфиденциальности" во вкладке "О приложении" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 74")
    @Description("Просмотр ссылки Политика конфиденциальности во вкладке О приложении (Позитивный)")
    public void transitionPrivacyPolicy() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        AboutSteps.clickButtonAbout();
        SystemClock.sleep(2000);
        AboutSteps.clickButtonPrivacyPolicy();
        SystemClock.sleep(1000);
        onView(withId(R.id.action_bar_root)).check(matches(isDisplayed()));
        //Выход
        pressBack();
    }

    //  TC - 75 - Просмотр ссылки "Пользовательское соглашение" во вкладке "О приложении" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 75")
    @Description("Просмотр ссылки Пользовательское соглашение во вкладке О приложении (Позитивный)")
    public void transitionTermsOfUse() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        AboutSteps.clickButtonAbout();
        SystemClock.sleep(2000);
        AboutSteps.clickButtonTermsOfUse();
        SystemClock.sleep(1000);
        onView(withId(R.id.action_bar_root)).check(matches(isDisplayed()));
        //Выход
        pressBack();
    }
}