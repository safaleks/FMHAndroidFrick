package ru.netology.diploma.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.diploma.util.Util.withIndex;

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
import ru.netology.diploma.elements.NewsControlPanelScreen;
import ru.netology.diploma.steps.ClaimsSteps;
import ru.netology.diploma.steps.NewsControlPaneSteps;
import ru.netology.diploma.steps.NewsSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования Панели Управления Новостей мобильного приложения Мобильный хоспис")
public class NewsControlPaneTest {

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
        AuthorizationScreen.textAuthorization();
        AuthorizationScreen.clickLoginField();
        AuthorizationScreen.clickPasswordField();
        SystemClock.sleep(1000);
        AuthorizationScreen.clickButton(AuthorizationScreen.getAuthorizationElementsButton());
    }

    @After
    public void Exit () {
        SystemClock.sleep(2000);
        AuthorizationScreen .clickButtonExit(AuthorizationScreen.getAuthorizationElementsButtonExit());
        SystemClock.sleep(2000);
        AuthorizationScreen.clickButtonLogOut();
    }

// Тест-кейсы для проведения функционального тестирования "Панели Управления Новостей" мобильного приложения "Мобильный хоспис".

    //  TC - 54 - Сортировка новостей во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 54")
    @Description("Сортировка новостей во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void sortingNewsControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPanelScreen.clickButtonControlPanel();
        NewsControlPanelScreen.clickButtonSorting();
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 55 - Просмотр новостей во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 55")
    @Description("Просмотр новостей во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void viewingNewsControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickClickNews();
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 56 - Удаление активной новости во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 56")
    @Description("Удаление активной новости во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void deletingActiveNews() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickClickNews();
        NewsControlPaneSteps.clickButtonDeleteNews();
        NewsControlPaneSteps.clickOkDeleteNews();
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 57 - Редактирование новости во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 57")
    @Description("Редактирование новости во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void editNewsControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickClickNews();
        NewsControlPaneSteps.clickButtonEditNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTitleNewsControlPanel();
        onView(withId(android.R.id.content)).perform(swipeUp());
        SystemClock.sleep(1000);
        NewsControlPaneSteps.clickButtonSaveEditingNews();
        SystemClock.sleep(2000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 58 - Смена статуса новости, находящаяся в статусе "АКТИВНА" на статус "НЕ АКТИВНА", во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 58")
    @Description("Смена статуса новости, находящаяся в статусе АКТИВНА на статус НЕ АКТИВНА, во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void statusChangeNews() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickClickNews();
        NewsControlPaneSteps.clickButtonEditNews();
        NewsControlPaneSteps.clickButtonSwitcher();
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(R.id.switcher))
                .check(matches(withText("Not active")))
                .check(matches(isDisplayed()));
        onView(withId(android.R.id.content)).perform(swipeDown());
        NewsControlPaneSteps.clickButtonSaveEditingNews();
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 59 - Фильтрация новостей по критерию "Активна", во вкладке "Панель управления" новостей мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 59")
    @Description("Фильтрация новостей по критерию Активна, во вкладке Панель управления новостей мобильного приложения Мобильный хоспис (Позитивный)")
    public void filterNewsByCriterionActive() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickFilterNewsControlPanel();
        NewsControlPaneSteps.clickRemoveCheckBoxActive();
        NewsControlPaneSteps.clickButtonFilterNewsControlPanel();
        SystemClock.sleep(2000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 60 - Фильтрация новостей по критерию "Не активна", во вкладке "Панель управления" новостей мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 60")
    @Description("Фильтрация новостей по критерию Не активна, во вкладке Панель управления новостей мобильного приложения Мобильный хоспис (Позитивный)")
    public void filterNewsByCriterionNotActive() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickFilterNewsControlPanel();
        NewsControlPaneSteps.clickRemoveCheckBoxNotActive();
        NewsControlPaneSteps.clickButtonFilterNewsControlPanel();
        SystemClock.sleep(2000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 61 - Создание новости во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 61")
    @Description("Создание новости во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void creationNewsInControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        NewsControlPaneSteps.clickButtonCategoryCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withIndex(withId(R.id.news_item_material_card_view), 0))).check(matches(isDisplayed()));
    }

    //  TC - 62 - Поле "Категория" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 62")
    @Description("Поле Категория пустое, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void fieldCategoryEmptyCreationNewsInControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 63 - Поле "Заголовок" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 63")
    @Description("Поле Заголовок пустое, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void fieldTitleEmptyCreationNewsInControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNew();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 64 - Поле "Дата публикации" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 64")
    @Description("Поле Дата публикации пустое, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void fieldDateEmptyCreationNewsInControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNews();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 65 - Поле "Время" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 65")
    @Description("Поле Время пустое, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")

    public void fieldTimeEmptyCreationNewsInControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNews();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 66 - Поле "Описание" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 66")
    @Description("Поле Описание пустое, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void fieldDescriptionEmptyCreationNewsInControlPanel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNews();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 67 - Ввод в поле "Категория" собственного названия категории, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 67")
    @Description("Ввод в поле Категория собственного названия категории, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void customCategoryName() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNewsEmpty();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Saving failed. Try again later"), isDisplayed()));
        pressBack();

    }
    //  TC - 68 - Поле "Категория" состоит из цифр, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 68")
    @Description("Поле Категория состоит из цифр, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void fieldCategoryConsistsOfNumbers() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNewsNumbers();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Saving failed. Try again later"), isDisplayed()));
        pressBack();

    }
    //  TC - 69 - Поле "Категория" состоит из спецсимволов, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 69")
    @Description("Поле Категория состоит из спецсимволов, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Негативный)")
    public void fieldCategoryConsistsOfSpecialCharacters() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingCharacters();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNews();
        NewsControlPaneSteps.clickButtonOkDateCreatingNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(allOf(withContentDescription("Saving failed. Try again later"), isDisplayed()));
        pressBack();
    }

    //  TC - 70 - Поле "Дата публикации" состоит из даты будущего года, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 70")
    @Description("Поле Дата публикации состоит из даты будущего года, при создании новости, во вкладке Панель управления мобильного приложения Мобильный хоспис (Позитивный)")
    public void fieldDateConsistsOfNextYearCreatingNews() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        NewsSteps.clickButtonNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonControlPanel();
        NewsControlPaneSteps.clickAddNews();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonCategoryCreatingNews();
        NewsControlPaneSteps.clickButtonTitleCreatingNews();
        NewsControlPaneSteps.clickButtonDateCreatingNextDate();
        SystemClock.sleep(2000);
        NewsControlPaneSteps.clickButtonTimeCreatingNews();
        NewsControlPaneSteps.clickButtonOkTimeCreatingNews();
        NewsControlPaneSteps.clickDescriptionCreatingNews();
        NewsControlPaneSteps.clickButtonSaveCreatingNews();
        SystemClock.sleep(2000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }
}
