package ru.netology.diploma.tests;

import static androidx.test.espresso.Espresso.onView;
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
import ru.netology.diploma.steps.AuthorizationSteps;
import ru.netology.diploma.steps.MainSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования вкладки Главная мобильного приложения Мобильный хоспис")
public class MainTest {
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
            AuthorizationSteps.textAuthorization();
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

// Тест-кейсы для проведения функционального тестирования вкладки "Главная" мобильного приложения "Мобильный хоспис".

    //  TC - 12 - Переход во вкладку "Все Новости" через главное меню мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    @Story("TC - 12")
    @Description("Переход во вкладку Все Новости через главное меню мобильного приложения Мобильный хоспис (Позитивный)")
    public void ButtonAllNews(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainSteps.clickButtonAllNews();
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()));
    }

    //  TC - 13 - Переход во вкладку "Все Заявки" через главное меню мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    @Story("TC - 13")
    @Description("Переход во вкладку \"Все Заявки\" через главное меню мобильного приложения \"Мобильный хоспис\"(Позитивный)")
    public void ButtonAllClaims(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainSteps.clickButtonClaims();
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }
}