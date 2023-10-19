package ru.netology.diploma.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.netology.diploma.util.Util.nestedScrollTo;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
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
import ru.netology.diploma.elements.ClaimsScreen;
import ru.netology.diploma.steps.AuthorizationSteps;
import ru.netology.diploma.steps.ClaimsSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

@Epic("Тест-кейсы для проведения функционального тестирования вкладки Заявки мобильного приложения Мобильный хоспис")
public class ClaimsTest {

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

//  Тест-кейсы для проведения функционального тестирования вкладки "Заявки" мобильного приложения "Мобильный хоспис".

    //  TC - 14 - Фильтрация заявок по критерию "Открыта" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).

    @Test
    @Story("TC - 14")
    @Description("Фильтрация заявок по критерию Открыта во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void applicationFilteringInProgress() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxOpen();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 15 - Фильтрация заявок по критерию "В работе" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    @Story("TC - 15")
    @Description("Фильтрация заявок по критерию В работе во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void applicationFilteringOpen() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 16 - Фильтрация заявок по критерию "Выполнена" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    @Story("TC - 16")
    @Description(" Фильтрация заявок по критерию Выполнена во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void applicationFilteringExecuted() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxOpen();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickCheckBoxExecuted();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 17 - Фильтрация заявок по критерию "Отмененные" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    @Story("TC - 17")
    @Description("Фильтрация заявок по критерию Отмененные во вкладке Заявки мобильного приложения Мобильный хоспис(Позитивный)")
    public void applicationFilteringCancelled() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxOpen();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickCheckBoxCancelled();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 18 - Создание заявки во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    @Story("TC - 18")
    @Description("Создание заявки во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void addNewClaim() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickCheckBoxExecutorField();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 19 - Поле "Тема" пустое, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 19")
    @Description("Поле Тема пустое, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void titleFieldIsEmpty() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickCheckBoxExecutorField();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(allOf(withId(R.id.message), isFocusable()));
        ClaimsSteps.clickButtonOkError();
        SystemClock.sleep(2000);
        onView(allOf(withId(R.id.text_input_end_icon), isFocusable()));
        ClaimsSteps.clickButtonCancelClaim();
        ClaimsSteps.clickButtonOkNotification();
    }

    //  TC - 20 - Поле "Тема" состоит из одного символа, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 20")
    @Description("Поле Тема состоит из одного символа, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void titleFieldOneCharacter() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleFieldOneCharacter();
        ClaimsSteps.clickCheckBoxExecutorField();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 21 - Поле "Тема" состоит из максимально-допустимого количество символов, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 21")
    @Description("Поле Тема состоит из максимально-допустимого количество символов, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void titleFieldMaximumCharacters() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleFieldMaximumCharacters();
        ClaimsSteps.clickCheckBoxExecutorField();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 22 - Поле "Исполнитель" пустое, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 22")
    @Description("Поле Исполнитель пустое, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void executorFieldIsEmpty() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(1000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 23 - Ввод в поле "Исполнитель" данных, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 23")
    @Description("Ввод в поле Исполнитель данных, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void executorFieldOtherData() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickExecutorFieldOtherData();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 24 - Поле "Исполнитель" состоит из букв и цифр, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 24")
    @Description("Поле Исполнитель состоит из букв и цифр, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void ExecutorFieldConsistsLettersAndNumbers() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickExecutorFieldConsistsLettersAndNumbers();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 25 - Поле "Исполнитель" состоит из спецсимволов, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 25")
    @Description("Поле Исполнитель состоит из спецсимволов, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void ExecutorFieldSpecialCharacters() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickExecutorFieldSpecialCharacters();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 26 - Поле "Дата" состоит из даты будущего года, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 26")
    @Description("Поле Дата состоит из даты будущего года, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void DescriptionFieldIsEmpty() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickCheckBoxExecutorField();
        ClaimsSteps.clickDateFieldNextYear();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickDescriptionField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 29 - Поле "Описание" пустое, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    @Story("TC - 29")
    @Description(" Поле Описание пустое, при создании заявки, во вкладке Заявки мобильного приложения Мобильный хоспис (Негативный)")
    public void DateFieldNextYear() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddClaim();
        ClaimsSteps.clickTitleField();
        ClaimsSteps.clickCheckBoxExecutorField();
        ClaimsSteps.clickDateField();
        ClaimsSteps.clickButtonOkDate();
        SystemClock.sleep(2000);
        ClaimsSteps.clickTimeField();
        ClaimsSteps.clickButtonOkTime();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(allOf(withId(R.id.message), isFocusable()));
        ClaimsSteps.clickButtonOkError();
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.text_input_end_icon), isFocusable()));
        ClaimsSteps.clickButtonCancelClaim();
        ClaimsSteps.clickButtonOkNotification();
    }

    //  TC - 30 - Редактирование заявки, находящаяся в статусе "Открыта", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 30")
    @Description("Редактирование заявки, находящаяся в статусе Открыта, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void EditClaimStatusOpen() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(2000);
        ClaimsSteps.clickOpenClaim();
        SystemClock.sleep(1000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        ClaimsSteps.clickEditClaim();
        SystemClock.sleep(1000);
        ClaimsSteps.clickEditClaimStatusOpen();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(1000);
        ViewInteraction buttonBackClaim = onView(withId(R.id.close_image_button)).perform(nestedScrollTo());
        buttonBackClaim.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 31 - Добавление комментария к заявке, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 31")
    @Description("Добавление комментария к заявке, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void AddComment() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickOpenClaim();
        SystemClock.sleep(1000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(android.R.id.content)).perform(swipeUp());
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonAddComment();
        SystemClock.sleep(2000);
        ClaimsSteps.clickCommentField();
        ClaimsSteps.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(R.id.comments_material_card_view)).check(matches(isDisplayed()));
    }

    //  TC - 32 - Редактирование комментария к заявке, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 32")
    @Description("Редактирование комментария к заявке, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void EditComment() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickOpenClaim();
        SystemClock.sleep(1000);
        ClaimsSteps.clickButtonEditComment();
        ClaimsSteps.clickCommentField();
        ClaimsScreen.clickButtonSave();
        SystemClock.sleep(2000);
        onView(withId(R.id.comments_material_card_view)).check(matches(isDisplayed()));
    }

    //  TC - 33 - Смена статуса заявки, находящаяся в статусе "Открыта" на статус "В работе", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 33")
    @Description("Смена статуса заявки, находящаяся в статусе Открыта на статус В работе, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void ChangeStatusOpenForInProgress() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(2000);
        ClaimsSteps.clickOpenClaim();
        ClaimsSteps.clickButtonSettings();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonTakeToWork();
        SystemClock.sleep(2000);
        onView(withId(R.id.status_label_text_view))
                .check(matches(withText("In progress")))
                .check(matches(isDisplayed()));
    }

    //  TC - 34 - Смена статуса заявки, находящаяся в статусе "Открыта" на статус "В работе", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 34")
    @Description("Смена статуса заявки, находящаяся в статусе Открыта на статус В работе, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void ChangeStatusOpenForCanceled() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeDown());
        ClaimsSteps.clickOpenClaim();
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        ClaimsSteps.clickButtonSettings();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonCancel();
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeDown());
        onView(withId(R.id.status_label_text_view))
                .check(matches(withText("Canceled")))
                .check(matches(isDisplayed()));
    }

    //  TC - 35 - Смена статуса заявки, с истекшим сроком  исполнения, находящаяся в статусе "Открыта" на статус "В работу", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    @Story("TC - 35")
    @Description("Смена статуса заявки, с истекшим сроком  исполнения, находящаяся в статусе Открыта на статус В работу, во вкладке Заявки мобильного приложения Мобильный хоспис (Позитивный)")
    public void ChangeStatusOpenForInProgressExpired() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ClaimsSteps.clickButtonMainMenu();
        ClaimsSteps.clickButtonClaims();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonFilter();
        ClaimsSteps.clickRemoveCheckBoxInProgress();
        ClaimsSteps.clickButtonOk();
        SystemClock.sleep(2000);
        ClaimsSteps.clickOpenClaim();
        ClaimsSteps.clickButtonSettings();
        SystemClock.sleep(2000);
        ClaimsSteps.clickButtonTakeToWork();
        SystemClock.sleep(2000);
        onView(withId(R.id.status_label_text_view))
                .check(matches(withText("In progress")))
                .check(matches(isDisplayed()));
    }
}