package ru.netology.diploma;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

import androidx.core.widget.NestedScrollView;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

public class AllClaimsTest {
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    public static ViewAction nestedScrollTo() {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(
                        isDescendantOfA(isAssignableFrom(NestedScrollView.class)),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE));
            }
            @Override
            public String getDescription() {
                return "View is not NestedScrollView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                try {
                    NestedScrollView nestedScrollView = (NestedScrollView)
                            findFirstParentLayoutOfClass(view, NestedScrollView.class);
                    if (nestedScrollView != null) {
                        nestedScrollView.scrollTo(0, view.getTop());
                    } else {
                        throw new Exception("Unable to find NestedScrollView parent.");
                    }
                } catch (Exception e) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(e)
                            .build();
                }
                uiController.loopMainThreadUntilIdle();
            }

        };
    }

    private static View findFirstParentLayoutOfClass(View view, Class<? extends View> parentClass) {
        ViewParent parent = new FrameLayout(view.getContext());
        ViewParent incrementView = null;
        int i = 0;
        while (parent != null && !(parent.getClass() == parentClass)) {
            if (i == 0) {
                parent = findParent(view);
            } else {
                parent = findParent(incrementView);
            }
            incrementView = parent;
            i++;
        }
        return (View) parent;
    }

    private static ViewParent findParent(View view) {
        return view.getParent();
    }

    private static ViewParent findParent(ViewParent view) {
        return view.getParent();
    }

    @Before
    public void login() {
        SystemClock.sleep(5000);
        loginField.perform(replaceText("login2"), closeSoftKeyboard());
        passwordField.perform(replaceText("password2"), closeSoftKeyboard());
        buttonEnter.perform(click());
    }

    @After
    public void logOut() {
        SystemClock.sleep(4000);
        buttonExit.perform(click());
        SystemClock.sleep(4000);
        buttonLogOut.perform(click());
    }

// Тест-кейсы для проведения функционального тестирования вкладки "Главная" мобильного приложения "Мобильный хоспис".

    //  TC - 12 - Переход во вкладку "Все Новости" через главное меню мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void openAllNews() {
        SystemClock.sleep(5000);
        buttonAllNews.check(matches(isDisplayed()));
        buttonAllNews.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isEnabled()));
    }

    //  TC - 13 - Переход во вкладку "Все Заявки" через главное меню мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void openAllClaims() {
        SystemClock.sleep(5000);
        ViewInteraction buttonAllClaims = onView(withId(R.id.all_claims_text_view)).perform(nestedScrollTo());
        buttonAllClaims.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isEnabled()));
    }

//  Тест-кейсы для проведения функционального тестирования вкладки "Заявки" мобильного приложения "Мобильный хоспис".

    //  TC - 14 - Фильтрация заявок по критерию "Открыта" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void applicationFilteringOpen() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        removeCheckBoxInProgress.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isEnabled()));
    }

    //  TC - 15 - Фильтрация заявок по критерию "В работе" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void applicationFilteringInProgress() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        removeCheckBoxOpen.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isEnabled()));
    }

    //  TC - 16 - Фильтрация заявок по критерию "Выполнена" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void applicationFilteringExecuted() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        removeCheckBoxOpen.perform(click());
        removeCheckBoxInProgress.perform(click());
        checkBoxExecuted.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isEnabled()));
    }

    //  TC - 17 - Фильтрация заявок по критерию "Отмененные" во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void applicationFilteringCancelled() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        removeCheckBoxOpen.perform(click());
        removeCheckBoxInProgress.perform(click());
        checkBoxCancelled.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isEnabled()));
    }

    //  TC - 18 - Создание заявки во вкладке "Заявки" мобильного приложения "Мобильный хоспис"(Позитивный).
    @Test
    public void addNewClaim() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("Осмотр"), closeSoftKeyboard());
        executorField.perform(replaceText("Смирнов Николай Петрович"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        SystemClock.sleep(2000);
        buttonSave.perform(click());
        onView(withId(R.id.claim_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 19 - Поле "Тема" пустое, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void titleFieldIsEmpty() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("  "), closeSoftKeyboard());
        executorField.perform(replaceText("Смирнов Николай Петрович"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.message), isFocusable()));
        buttonOkError.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.text_input_end_icon), isFocusable()));
        //  Выход
        buttonCancelClaim.perform(click());
        buttonOkNotification.perform(click());
    }

    //  TC - 20 - Поле "Тема" состоит из одного символа, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void titleFieldOneCharacter() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(2000);
        titleField.perform(replaceText("1"), closeSoftKeyboard());
        executorField.perform(replaceText("Цветкова Алия Валерьяновна"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        SystemClock.sleep(2000);
        buttonSave.perform(click());
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 21 - Поле "Тема" состоит из максимально-допустимого количество символов, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void titleFieldMaximumCharacters() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("123456789АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЭЮЯ1234567891011"), closeSoftKeyboard());
        executorField.perform(replaceText("Горбунов Егор Богданович"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 22 - Поле "Исполнитель" пустое, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void executorFieldIsEmpty() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(2000);
        titleField.perform(replaceText("Поле Исполнитель пустое"), closeSoftKeyboard());
        executorField.perform(replaceText(" "), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        SystemClock.sleep(2000);
        buttonSave.perform(click());
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 23 - Ввод в поле "Исполнитель" данных, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void executorFieldOtherData() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("Прочие данные в поле Исполнитель"), closeSoftKeyboard());
        executorField.perform(replaceText("Иванов Иванович Иванов"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 24 - Поле "Исполнитель" состоит из букв и цифр, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void executorFieldConsistsLettersAndNumbers() {
        SystemClock.sleep(6000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(2000);
        titleField.perform(replaceText("Поле Исполнитель состоит из букв и цифр"), closeSoftKeyboard());
        executorField.perform(replaceText("И123ванов И456ванович И789ванов"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 25 - Поле "Исполнитель" состоит из спецсимволов, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void executorFieldSpecialCharacters() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("Поле Исполнитель состоит из спецсимволов"), closeSoftKeyboard());
        executorField.perform(replaceText("@#$$%%^&**"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    String nextYear = "30.05.2023";
    //  TC - 26 - Поле "Дата" состоит из даты будущего года, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void dateFieldNextYear() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("Поле Дата состоит из даты будущего года"), closeSoftKeyboard());
        executorField.perform(replaceText("Горбунов Егор Богданович"), closeSoftKeyboard());
        dateField.perform(replaceText(nextYear));
        timeField.perform(click());
        buttonOkTime.perform(click());
        descriptionField.perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 29 - Поле "Описание" пустое, при создании заявки, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void descriptionFieldIsEmpty() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonAddClaim.perform(click());
        SystemClock.sleep(1000);
        titleField.perform(replaceText("Поле Описание пустое"), closeSoftKeyboard());
        executorField.perform(replaceText("Горбунов Егор Богданович"), closeSoftKeyboard());
        dateField.perform(click());
        buttonOkDate.perform(click());
        timeField.perform(click());
        buttonOkTime.perform(click());
        SystemClock.sleep(1000);
        descriptionField.perform(replaceText("  "), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.message), isFocusable()));
        buttonOkError.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.text_input_end_icon), isFocusable()));
        //  Выход
        buttonCancelClaim.perform(click());
        buttonOkNotification.perform(click());
    }

    //  TC - 30 - Редактирование заявки, находящаяся в статусе "Открыта", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void editClaimStatusOpen() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        removeCheckBoxInProgress.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        openClaim.perform(click());
        SystemClock.sleep(1000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        editClaim.perform(click());
        SystemClock.sleep(1000);
        descriptionField.perform(clearText(),
                replaceText("Редактирование заявки, находящаяся в статусе Открыта"),
                closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        ViewInteraction buttonBackClaim = onView(withId(R.id.close_image_button)).perform(nestedScrollTo());
        buttonBackClaim.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_claims_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 31 - Добавление комментария к заявке, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void addComment() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        openClaim.perform(click());
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        SystemClock.sleep(2000);
        buttonAddComment.perform(click());
        SystemClock.sleep(2000);
        commentField.perform(clearText(), replaceText("Новый комментарий"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(2000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        onView(withId(R.id.comments_material_card_view)).check(matches(isDisplayed()));
    }

    //  TC - 32 - Редактирование комментария к заявке, во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void editComment() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        openClaim.perform(click());
        SystemClock.sleep(5000);
        buttonEditComment.perform(click());
        commentField.perform(click(), clearText(), replaceText("Отредактированный комментарий"), closeSoftKeyboard());
        buttonSave.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.comments_material_card_view)).check(matches(isDisplayed()));
    }

    //  TC - 33 - Смена статуса заявки, находящаяся в статусе "Открыта" на статус "В работе", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void changeStatusOpenForInProgress() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        SystemClock.sleep(1000);
        removeCheckBoxInProgress.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        openClaim.perform(click());
        buttonSettings.perform(click());
        SystemClock.sleep(1000);
        buttonTakeToWork.perform(click());
        onView(withId(R.id.status_label_text_view))
                .check(matches(withText("In progress")))
                .check(matches(isDisplayed()));
    }

    //  TC - 34 - Смена статуса заявки, находящаяся в статусе "Открыта" на статус "В работе", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void changeStatusOpenForCanceled() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        SystemClock.sleep(1000);
        removeCheckBoxInProgress.perform(click());
        buttonOk.perform(click());
        onView(withId(android.R.id.content)).perform(swipeDown());
        openClaim.perform(click());
        SystemClock.sleep(1000);
        onView(withId(android.R.id.content)).perform(swipeUp());
        buttonSettings.perform(click());
        SystemClock.sleep(1000);
        buttonCancel.perform(click());
        SystemClock.sleep(1000);
        onView(withId(android.R.id.content)).perform(swipeDown());
        onView(withId(R.id.status_label_text_view))
                .check(matches(withText("Canceled")))
                .check(matches(isDisplayed()));
    }

    //  TC - 35 - Смена статуса заявки, с истекшим сроком  исполнения, находящаяся в статусе "Открыта" на статус "В работу", во вкладке "Заявки" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void changeStatusOpenForInProgressExpired() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonClaims.perform(click());
        buttonFilter.perform(click());
        SystemClock.sleep(1000);
        removeCheckBoxInProgress.perform(click());
        buttonOk.perform(click());
        SystemClock.sleep(1000);
        openClaim.perform(click());
        buttonSettings.perform(click());
        SystemClock.sleep(1000);
        buttonTakeToWork.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.status_label_text_view))
                .check(matches(withText("In progress")))
                .check(matches(isDisplayed()));
    }

//  Тест-кейсы для проведения функционального тестирования вкладки "Новости" мобильного приложения "Мобильный хоспис".

    //  TC - 40 - Просмотр новостей во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void viewingNews() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonExpandNews.perform(doubleClick());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()));
    }

    //  TC - 41 - Сортировка новостей во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void newsSorting() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonSorting.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()));
    }

    //  TC - 50 - Фильтрация новостей без указания категории, во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  filteringNewsNoCategoryPositive() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonFilterNews.perform(click());
        titleFilterNews.check(matches(allOf(withText("Filter news"), isDisplayed())));
        buttonCategoryFilter.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    String date = "30.05.2022";
    //  TC - 52 - Фильтрация новостей, без указания категории, в определенный период времени, во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  filteringNewsCertainPeriodTime() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonFilterNews.perform(click());
        SystemClock.sleep(1000);
        titleFilterNews.check(matches(allOf(withText("Filter news"), isDisplayed())));
        buttonDateStart.perform(click());
        buttonOkDateStart.perform(click());
        SystemClock.sleep(1000);
        buttonDateEnd.perform(replaceText(date));
        buttonCategoryFilter.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

    //  TC - 53 - Фильтрация новостей, без указания категории, в определенный период времени, во вкладке "Новости" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  filteringNewsCertainPeriodTimeNegative() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonFilterNews.perform(click());
        SystemClock.sleep(1000);
        titleFilterNews.check(matches(allOf(withText("Filter news"), isDisplayed())));
        buttonDateStart.perform(click());
        buttonOkDateStart.perform(click());
        buttonCategoryFilter.perform(click());
        SystemClock.sleep(1000);
        // Выход
        buttonOkWrongMessage.perform(click());
        buttonDateStart.perform(click());
        buttonOkDateStart.perform(click());
        buttonDateEnd.perform(replaceText(date));
        buttonCategoryFilter.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.all_news_cards_block_constraint_layout)).check(matches(isDisplayed()));
    }

// Тест-кейсы для проведения функционального тестирования "Панели Управления Новостей" мобильного приложения "Мобильный хоспис".

    //  TC - 54 - Сортировка новостей во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  sortingNewsControlPanel() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        buttonSorting.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 55 - Просмотр новостей во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  viewingNewsControlPanel() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        buttonClickNews.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 56 - Удаление активной новости во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  deletingActiveNews() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        buttonClickNews.perform(click());
        buttonDeleteNews.perform(click());
        buttonOkDeleteNews.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }
    
    //  TC - 57 - Редактирование новости во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  editNewsControlPanel() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        buttonClickNews.perform(click());
        buttonEditNews.perform(click());
        SystemClock.sleep(1000);
        buttonTitleNewsControlPanel.perform(clearText(), replaceText("Отредактированный текст новости"), closeSoftKeyboard());
        onView(withId(android.R.id.content)).perform(swipeUp());
        buttonSaveEditingNews.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
   }

   //  TC - 58 - Смена статуса новости, находящаяся в статусе "АКТИВНА" на статус "НЕ АКТИВНА", во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
   @Test
   public void  statusChangeNews() {
       SystemClock.sleep(5000);
       buttonMainMenu.check(matches(isDisplayed()));
       buttonMainMenu.perform(click());
       buttonNews.perform(click());
       buttonControlPanel.perform(click());
       buttonClickNews.perform(click());
       buttonEditNews.perform(click());
       buttonSwitcher.perform(click());
       onView(withId(android.R.id.content)).perform(swipeUp());
       onView(withId(R.id.switcher))
                .check(matches(withText("Not active")))
                .check(matches(isDisplayed()));
       //  Выход
       onView(withId(android.R.id.content)).perform(swipeDown());
       buttonSaveEditingNews.perform(click());
       onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
   }

   //  TC - 59 - Фильтрация новостей по критерию "Активна", во вкладке "Панель управления" новостей мобильного приложения "Мобильный хоспис" (Позитивный).
   @Test
   public void  filterNewsByCriterionActive() {
       SystemClock.sleep(5000);
       buttonMainMenu.check(matches(isDisplayed()));
       buttonMainMenu.perform(click());
       buttonNews.perform(click());
       buttonControlPanel.perform(click());
       filterNewsControlPanel.perform(click());
       removeCheckBoxNotActive.perform(click());
       buttonFilterNewsControlPanel.perform(click());
       SystemClock.sleep(1000);
       onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
   }

    //  TC - 60 - Фильтрация новостей по критерию "Не активна", во вкладке "Панель управления" новостей мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  filterNewsByCriterionNotActive() {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        filterNewsControlPanel.perform(click());
        removeCheckBoxActive.perform(click());
        buttonFilterNewsControlPanel.perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

    //  TC - 61 - Создание новости во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  creationNewsInControlPanel () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click());
        SystemClock.sleep(1000);
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Главные новости сегодня"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Новое объявление"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withIndex(withId(R.id.news_item_material_card_view), 0))).check(matches(isDisplayed()));
    }

    //  TC - 62 - Поле "Категория" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldCategoryEmptyCreationNewsInControlPanel () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        addNews.perform(click());
        SystemClock.sleep(1000);
        buttonTitleCreatingNews.perform(replaceText("Поле Категория пустое"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Категория пустое"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 63 - Поле "Заголовок" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldTitleEmptyCreationNewsInControlPanel () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        addNews.perform(click());
        SystemClock.sleep(1000);
        buttonCategoryCreatingNews.perform(click());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("  "), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Заголовок пустое"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 64 - Поле "Дата публикации" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldDateEmptyCreationNewsInControlPanel () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Поле Дата публикации пустое"), closeSoftKeyboard());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Дата публикации пустое"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 65 - Поле "Время" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldTimeEmptyCreationNewsInControlPanel () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Поле Время пустое"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Время пустое"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 66 - Поле "Описание" пустое, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldDescriptionEmptyCreationNewsInControlPanel () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Поле Описание пустое"), closeSoftKeyboard());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonSaveCreatingNews.perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Fill empty fields"), isDisplayed()));
        pressBack();
    }

    //  TC - 67 - Ввод в поле "Категория" собственного названия категории, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  customCategoryName () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click(), replaceText("Собственное название категории"), closeSoftKeyboard());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Собственное название категории"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Ввод в поле Категория собственного названия категории"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(scrollTo(), click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Saving failed. Try again later"), isDisplayed()));
        pressBack();
    }

    //  TC - 68 - Поле "Категория" состоит из цифр, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldCategoryConsistsOfNumbers () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click(), replaceText("123456"), closeSoftKeyboard());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Поле Категория состоит из цифр"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Категория состоит из цифр"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(scrollTo(), click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Saving failed. Try again later"), isDisplayed()));
        pressBack();
    }

    //  TC - 69 - Поле "Категория" состоит из спецсимволов, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Негативный).
    @Test
    public void  fieldCategoryConsistsOfSpecialCharacters () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click(), replaceText("@#$%^&**"), closeSoftKeyboard());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Поле Категория состоит из спецсимволов"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(click());
        buttonOkDateCreatingNews.perform(click());
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Категория состоит из спецсимволов"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(scrollTo(), click());
        SystemClock.sleep(1000);
        onView(allOf(withContentDescription("Saving failed. Try again later"), isDisplayed()));
        pressBack();
    }

    //  TC - 70 - Поле "Дата публикации" состоит из даты будущего года, при создании новости, во вкладке "Панель управления" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  fieldDateConsistsOfNextYearCreatingNews () {
        SystemClock.sleep(5000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonNews.perform(click());
        buttonControlPanel.perform(click());
        SystemClock.sleep(1000);
        addNews.perform(click());
        buttonCategoryCreatingNews.perform(click());
        buttonTitleCreatingNews.perform(click(), clearText(), replaceText("Поле Дата публикации состоит из даты будущего года"), closeSoftKeyboard());
        buttonDateCreatingNews.perform(replaceText(nextYear));
        buttonTimeCreatingNews.perform(click());
        buttonOkTimeCreatingNews.perform(click());
        descriptionCreatingNews.perform(replaceText("Поле Дата публикации состоит из даты будущего года"), closeSoftKeyboard());
        buttonSaveCreatingNews.perform(scrollTo(), click());
        SystemClock.sleep(1000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }

//  Тест-кейсы для проведения функционального тестирования вкладки "Тематические цитаты" мобильного приложения "Мобильный хоспис".

    //  TC - 73 - Развернуть/свернуть тематическую цитату, во вкладке "Главное - жить любя", мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  expandThematicQuote () {
        SystemClock.sleep(5000);
        buttonThematicQuote.check(matches(isDisplayed()));
        buttonThematicQuote.perform(click());
        SystemClock.sleep(1000);
        titleThematicQuote.check(matches(allOf(withText("Love is all"), isDisplayed())));
        buttonExpandThematicQuote.perform(click());
        descriptionThematicQuote.check(matches(isDisplayed()));
        SystemClock.sleep(1000);
        onView(withId(R.id.our_mission_item_list_recycler_view)).check(matches(isDisplayed()));
    }

//  Тест-кейсы для проведения функционального тестирования вкладки "О приложении" мобильного приложения "Мобильный хоспис".

    //  TC - 74 - Просмотр ссылки "Политика конфиденциальности" во вкладке "О приложении" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  transitionPrivacyPolicy () {
        SystemClock.sleep(6000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonAbout.perform(click());
        buttonPrivacyPolicy.check(matches(allOf(withText("https://vhospice.org/#/privacy-policy/"), isDisplayed(), isClickable())));
        SystemClock.sleep(1000);
        onView(withId(R.id.action_bar_root)).check(matches(isDisplayed()));
        //Выход
        pressBack();
    }

    //  TC - 75 - Просмотр ссылки "Пользовательское соглашение" во вкладке "О приложении" мобильного приложения "Мобильный хоспис" (Позитивный).
    @Test
    public void  transitionTermsOfUse () {
        SystemClock.sleep(6000);
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        buttonAbout.perform(click());
        buttonTermsOfUse.check(matches(allOf(withText("https://vhospice.org/#/terms-of-use"), isDisplayed(), isClickable())));
        SystemClock.sleep(1000);
        onView(withId(R.id.action_bar_root)).check(matches(isDisplayed()));
        //Выход
        buttonBackAbout.perform(click());
    }

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
    ViewInteraction buttonAllNews = onView(
            withId(R.id.all_news_text_view));
    ViewInteraction buttonMainMenu = onView(
            allOf(withId(R.id.main_menu_image_button)));
    ViewInteraction buttonClaims = onView(
            allOf(withId(android.R.id.title), withText("Claims")));
    ViewInteraction buttonFilter = onView(
            allOf(withId(R.id.filters_material_button)));
    ViewInteraction removeCheckBoxOpen = onView(
            allOf(withId(R.id.item_filter_open)));
    ViewInteraction removeCheckBoxInProgress = onView(
            allOf(withId(R.id.item_filter_in_progress)));
    ViewInteraction checkBoxExecuted = onView(
            allOf(withId(R.id.item_filter_executed)));
    ViewInteraction checkBoxCancelled = onView(
            allOf(withId(R.id.item_filter_cancelled)));
    ViewInteraction buttonOk = onView(
            allOf(withId(R.id.claim_list_filter_ok_material_button)));
    ViewInteraction buttonAddClaim = onView(
            allOf(withId(R.id.add_new_claim_material_button)));
    ViewInteraction titleField = onView(
            allOf(withHint("Title"), withParent(withParent(withId(R.id.title_text_input_layout)))));
    ViewInteraction executorField = onView(
            allOf(withId(R.id.executor_drop_menu_auto_complete_text_view)));
    ViewInteraction dateField = onView(
            allOf(withId(R.id.date_in_plan_text_input_edit_text)));
    ViewInteraction buttonOkDate = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction timeField = onView(
            allOf(withId(R.id.time_in_plan_text_input_edit_text)));
    ViewInteraction buttonOkTime = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction descriptionField = onView(
            allOf(withId(R.id.description_edit_text)));
    ViewInteraction buttonSave = onView(
            allOf(withId(R.id.save_button)));
    ViewInteraction buttonOkError = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction buttonCancelClaim = onView(
            allOf(withId(R.id.cancel_button)));
    ViewInteraction buttonOkNotification = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction editClaim = onView(
            allOf(withId(R.id.edit_processing_image_button)));
    ViewInteraction openClaim = onView(
            allOf(withIndex(withId(R.id.title_material_text_view), 0)));
    ViewInteraction buttonAddComment = onView(
            allOf(withId(R.id.add_comment_image_button)));
    ViewInteraction buttonEditComment = onView(
            allOf(withIndex(withId(R.id.edit_comment_image_button), 0)));
    ViewInteraction commentField = onView(
            allOf(withHint("Comment"), withParent(withParent(withId(R.id.comment_text_input_layout)))));
    ViewInteraction buttonSettings = onView(
            allOf(withId(R.id.status_processing_image_button)));
    ViewInteraction buttonTakeToWork = onView(
            allOf(withId(android.R.id.title), withText("take to work")));
    ViewInteraction buttonCancel = onView(
            allOf(withId(android.R.id.title), withText("Cancel")));
    ViewInteraction buttonNews = onView(
            allOf(withId(android.R.id.title), withText("News")));
    ViewInteraction buttonExpandNews = onView(
            allOf(withIndex(withId(R.id.news_item_title_text_view), 1)));
    ViewInteraction buttonSorting = onView(
            allOf(withId(R.id.sort_news_material_button)));
    ViewInteraction buttonFilterNews = onView(
            allOf(withId(R.id.filter_news_material_button)));
    ViewInteraction buttonCategoryFilter = onView(
            allOf(withId(R.id.filter_button)));
    ViewInteraction buttonDateStart = onView(
            allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text)));
    ViewInteraction buttonOkDateStart = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction buttonDateEnd = onView(
            allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text)));
    ViewInteraction buttonOkWrongMessage = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction buttonControlPanel = onView(
            allOf(withId(R.id.edit_news_material_button)));
    ViewInteraction buttonClickNews = onView(
            allOf(withIndex(withId(R.id.news_item_material_card_view), 0)));
    ViewInteraction buttonDeleteNews = onView(
            allOf(withIndex(withId(R.id.delete_news_item_image_view), 0)));
    ViewInteraction buttonOkDeleteNews = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction buttonEditNews = onView(
            allOf(withIndex(withId(R.id.edit_news_item_image_view), 0)));
    ViewInteraction buttonTitleNewsControlPanel = onView(
            allOf(withId(R.id.news_item_title_text_input_edit_text)));
    ViewInteraction buttonSaveEditingNews = onView(
            allOf(withId(R.id.save_button)));
    ViewInteraction buttonSwitcher = onView(
            allOf(withId(R.id.switcher)));
    ViewInteraction filterNewsControlPanel = onView(
            allOf(withId(R.id.filter_news_material_button)));
    ViewInteraction removeCheckBoxNotActive = onView(
            allOf(withId(R.id.filter_news_inactive_material_check_box)));
    ViewInteraction buttonFilterNewsControlPanel = onView(
            allOf(withId(R.id.filter_button)));
    ViewInteraction removeCheckBoxActive = onView(
            allOf(withId(R.id.filter_news_active_material_check_box)));
    ViewInteraction addNews = onView(
            allOf(withId(R.id.add_news_image_view)));
    ViewInteraction buttonCategoryCreatingNews = onView(
            allOf(withId(R.id.news_item_category_text_auto_complete_text_view)));
    ViewInteraction buttonTitleCreatingNews = onView(
            allOf(withId(R.id.news_item_title_text_input_edit_text)));
    ViewInteraction buttonDateCreatingNews = onView(
            allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));
    ViewInteraction buttonOkDateCreatingNews = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction buttonTimeCreatingNews = onView(
            allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
    ViewInteraction buttonOkTimeCreatingNews = onView(
            allOf(withId(android.R.id.button1)));
    ViewInteraction descriptionCreatingNews = onView(
            allOf(withId(R.id.news_item_description_text_input_edit_text)));
    ViewInteraction buttonSaveCreatingNews = onView(
            allOf(withId(R.id.save_button)));
    ViewInteraction buttonThematicQuote = onView(
            allOf(withId(R.id.our_mission_image_button)));
    ViewInteraction buttonExpandThematicQuote = onView(
            allOf(withIndex(withId(R.id.our_mission_item_open_card_image_button), 0)));
    ViewInteraction titleThematicQuote = onView(
            allOf(withId(R.id.our_mission_title_text_view)));
    ViewInteraction descriptionThematicQuote = onView(
            allOf(withIndex(withId(R.id.our_mission_item_description_text_view), 0)));
    ViewInteraction buttonAbout = onView(
            allOf(withId(android.R.id.title), withText("About")));
    ViewInteraction buttonPrivacyPolicy = onView(
            allOf(withId(R.id.about_privacy_policy_value_text_view)));
    ViewInteraction buttonTermsOfUse = onView(
            withId(R.id.about_terms_of_use_value_text_view));
    ViewInteraction titleFilterNews = onView(
            allOf(withId(R.id.filter_news_title_text_view)));
    ViewInteraction buttonBackAbout = onView(
            allOf(withId(R.id.about_back_image_button)));
}