package ru.netology.diploma;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

//@RunWith(AllureAndroidJUnit4.class)
@RunWith(AndroidJUnit4.class)
public class AuthorizationTest {

        @Rule
        public ActivityTestRule<AppActivity> activityTestRule =
                new ActivityTestRule<>(AppActivity.class);

        ViewInteraction loginField = onView(
                allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))));
        ViewInteraction passwordField = onView(
                allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))));
        ViewInteraction buttonEnter = onView(
                withId(R.id.enter_button));
        ViewInteraction buttonExit = onView(
                withId(R.id.authorization_image_button));
        ViewInteraction buttonLogOut = onView(
                allOf(withId(android.R.id.title), withText("Log out")));
        ViewInteraction textAuthorization = onView(
                allOf(withText("Authorization"), withParent(withParent(withId(R.id.nav_host_fragment)))));

// Тест-кейсы для проведения функционального тестирования вкладки "Авторизация" мобильного приложения "Мобильный хоспис".

        //  TC - 1 - Авторизация в мобильном приложении "Мобильный хоспис"(Позитивный).
        @Test
        public void successfulAuthorization() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            // Выход
            SystemClock.sleep(2000);
            buttonExit.perform(click());
            SystemClock.sleep(2000);
            buttonLogOut.perform(click());
        }

        //  TC - 2 - Поле "Логин" пустое, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void loginFieldIsEmpty() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText(" "), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Login and password cannot be empty"), isDisplayed()));
        }

        //  TC - 3 - Поле "Логин" заполнено данными незарегистрированного пользователя, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void loginFieldUnregisteredUser() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login123"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 4 - Поле "Логин" состоит из спецсимволов, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void loginFieldWithSpecialCharacters() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("@#$^&**"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 5 - Поле "Логин" состоит из одного символа, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void loginFieldOneLetter() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("l"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 6 - Поле "Логин" состоит из букв разного регистра, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void loginFieldLettersOfDifferentCase() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("LoGin2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 7 - Поле "Пароль" пустое, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void passwordFieldIsEmpty() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("  "), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Login and password cannot be empty"), isDisplayed()));
        }

        //  TC - 8 - Поле "Пароль" заполнено данными незарегистрированного пользователя, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void passwordFieldUnregisteredUser() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("password123"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 9 - Поле "Пароль" состоит из спецсимволов, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void passwordFieldWithSpecialCharacters() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("$%&(*^$"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 10 - Поле "Пароль" состоит из одного символа, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void passwordFieldOneLetter() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("p"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }

        //  TC - 11 - Поле "Пароль" состоит из букв разного регистра, при авторизации, в мобильном приложении "Мобильный хоспис"(Негативный).
        @Test
        public void passwordFieldLettersOfDifferentCase() {
            SystemClock.sleep(5000);
            textAuthorization.check(matches(isDisplayed()));
            loginField.check(matches(isDisplayed()));
            loginField.perform(replaceText("login2"), closeSoftKeyboard());
            passwordField.check(matches(isDisplayed()));
            passwordField.perform(replaceText("PassWord2"), closeSoftKeyboard());
            buttonEnter.perform(click());
            SystemClock.sleep(1000);
            onView(allOf(withContentDescription("Wrong login or password"), isDisplayed()));
        }
}