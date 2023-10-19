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
import io.qameta.allure.kotlin.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.netology.diploma.elements.AuthorizationScreen;
import ru.netology.diploma.steps.AuthorizationSteps;
import ru.netology.diploma.steps.ClaimsSteps;
import ru.netology.diploma.steps.NewsSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования вкладки Новости мобильного приложения Мобильный хоспис")
public class NewsTest {

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

//  Тест-кейсы для проведения функционального тестирования вкладки "Новости" мобильного приложения "Мобильный хоспис".

    //  TC - 40 - Просмотр новостей во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
      @Test
      @Story("TC - 40")
      @Description("Просмотр новостей во вкладке Новости мобильного приложения Мобильный хоспис (Позитивный)")
    public void viewingNews() {
          try {
              Thread.sleep(5000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          ClaimsSteps.clickButtonMainMenu();
          NewsSteps.clickButtonNews();
          SystemClock.sleep(2000);
          NewsSteps.clickExpandNews();
          SystemClock.sleep(2000);
          onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()));
      }

    //  TC - 41 - Сортировка новостей во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 41")
    @Description("Сортировка новостей во вкладке Новости мобильного приложения Мобильный хоспис (Позитивный)")
    public void newsSorting() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          ClaimsSteps.clickButtonMainMenu();
          NewsSteps.clickButtonNews();
          SystemClock.sleep(2000);
          NewsSteps.clickButtonSorting();
          onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()));
      }

    //  TC - 50 - Фильтрация новостей без указания категории, во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 50")
    @Description("Фильтрация новостей без указания категории, во вкладке Новости мобильного приложения Мобильный хоспис (Позитивный)")
    public void filteringNewsNoCategoryPositive() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          ClaimsSteps.clickButtonMainMenu();
          NewsSteps.clickButtonNews();
          SystemClock.sleep(2000);
          NewsSteps.clickButtonFilterNews();
          NewsSteps.clickButtonTitleFilterNews();
          NewsSteps.clickButtonCategoryFilter();
          SystemClock.sleep(2000);
          onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
     }

    //  TC - 52 - Фильтрация новостей, без указания категории, в определенный период времени, во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 52")
    @Description("Фильтрация новостей, без указания категории, в определенный период времени, во вкладке Новости мобильного приложения Мобильный хоспис (Позитивный)")
    public void filteringNewsCertainPeriodTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          ClaimsSteps.clickButtonMainMenu();
          NewsSteps.clickButtonNews();
          SystemClock.sleep(2000);
          NewsSteps.clickButtonFilterNews();
          NewsSteps.clickButtonTitleFilterNews();
          NewsSteps.clickButtonDateStart();
          NewsSteps.clickButtonOkDateStart();
          SystemClock.sleep(2000);
          NewsSteps.clickButtonDateEnd();
          NewsSteps.clickButtonCategoryFilter();
          SystemClock.sleep(2000);
          onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
     }

    //  TC - 53 - Фильтрация новостей, без указания категории, в определенный период времени, во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 53")
    @Description("Фильтрация новостей, без указания категории, в определенный период времени, во вкладке Новости мобильного приложения Мобильный хоспис (Негативный)")
    public void filteringNewsCertainPeriodTimeNegative() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsSteps.clickButtonFilterNews();
        NewsSteps.clickButtonTitleFilterNews();
        NewsSteps.clickButtonDateStart();
        NewsSteps.clickButtonOkDateStart();
        SystemClock.sleep(2000);
        NewsSteps.clickButtonCategoryFilter();
        SystemClock.sleep(2000);
        NewsSteps.clickButtonOkWrongMessage();
        NewsSteps.clickButtonDateStart();
        NewsSteps.clickButtonOkDateStart();
        NewsSteps.clickButtonDateEnd();
        NewsSteps.clickButtonCategoryFilter();
        SystemClock.sleep(1000);
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }
}
