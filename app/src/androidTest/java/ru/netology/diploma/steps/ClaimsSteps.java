package ru.netology.diploma.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonAddClaim;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonAddComment;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonCancel;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonCancelClaim;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonClaims;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonEditComment;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonFilter;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonMainMenu;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonOk;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonOkDate;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonOkError;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonOkNotification;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonOkTime;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonSave;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonSettings;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsButtonTakeToWork;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsCheckBoxCancelled;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsCheckBoxExecuted;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsCommentField;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsDateField;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsDescriptionField;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsEditClaim;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsExecutorField;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsOpenClaim;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsRemoveCheckBoxInProgress;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsRemoveCheckBoxOpen;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsTimeField;
import static ru.netology.diploma.elements.ClaimsScreen.getClaimsElementsTitleField;

import io.qameta.allure.kotlin.Allure;

public class ClaimsSteps {

    public static void clickButtonMainMenu(){
        Allure.step("Нажать на кнопку Главное меню");
        onView(getClaimsElementsButtonMainMenu())
                .perform(click());
    }

    public static void clickButtonClaims(){
        Allure.step("Нажать на кнопку Заявки");
        onView(getClaimsElementsButtonClaims () )
                .perform(click());
    }

    public static void clickButtonFilter(){
        Allure.step("Нажать на кнопку Фильтрация");
        onView(getClaimsElementsButtonFilter ())
                .perform(click());
    }

    public static void clickRemoveCheckBoxOpen(){
        Allure.step("Снять флажок с чекбокса Открыта");
        onView(getClaimsElementsRemoveCheckBoxOpen() )
                .perform(click());
    }

    public static void clickButtonOk(){
        Allure.step("Нажать кнопку ОК");
        onView(getClaimsElementsButtonOk ())
                .perform(click());
    }

    public static void clickRemoveCheckBoxInProgress(){
        Allure.step("Снять флажок с чекбокса В работе");
        onView(getClaimsElementsRemoveCheckBoxInProgress ())
                .perform(click());
    }

    public static void clickCheckBoxExecuted(){
        Allure.step("Выбрать критерий для фильтрации Выполнена");
        onView(getClaimsElementsCheckBoxExecuted ())
                .perform(click());
    }

    public static void clickCheckBoxCancelled(){
        Allure.step("Выбрать критерий для фильтрации Отмененные");
        onView(getClaimsElementsCheckBoxCancelled())
                .perform(click());
    }

    public static void clickButtonAddClaim(){
        Allure.step("Нажать на кнопку Создание Заявки");
        onView(getClaimsElementsButtonAddClaim ())
                .perform(click());
    }

    public static void clickTitleField(){
        Allure.step("Ввести в поле Тема наименование темы");
        onView(getClaimsElementsTitleField ())
                .perform(replaceText("Осмотр"), closeSoftKeyboard());
    }

    public static void clickCheckBoxExecutorField(){
        Allure.step("В поле Исполнитель выбрать из списка ФИО исполнителя");
        onView(getClaimsElementsExecutorField ())
                .perform(replaceText("Смирнов Николай Петрович"), closeSoftKeyboard());
    }

    public static void clickDateField(){
        Allure.step("Нажать на поле Дата");
        onView(getClaimsElementsDateField ())
                .perform(click());
    }

    public static void clickButtonOkDate(){
        Allure.step("Нажать кнопку ОК");
        onView(getClaimsElementsButtonOkDate ())
                .perform(click());
    }

    public static void clickTimeField(){
        Allure.step("Нажать на поле Время");
        onView(getClaimsElementsTimeField ())
                .perform(click());
    }

    public static void clickButtonOkTime(){
        Allure.step("Нажать кнопку ОК");
        onView(getClaimsElementsButtonOkTime ())
                .perform(click());
    }

    public static void clickDescriptionField(){
        Allure.step("Ввести в поле Описание описание заявки");
        onView(getClaimsElementsDescriptionField ())
                .perform(replaceText("Срочный осмотр"), closeSoftKeyboard());
    }

    public static void clickButtonSave(){
        Allure.step("Нажать на кнопку Сохранить");
        onView(getClaimsElementsButtonSave ())
                .perform(click());
    }

    public static void clickButtonOkError(){
        Allure.step("Нажать кнопку ОК");
        onView(getClaimsElementsButtonOkError ())
                .perform(click());
    }

    public static void clickButtonCancelClaim(){
        Allure.step("Нажать кнопку Отмена");
        onView(getClaimsElementsButtonCancelClaim ())
                .perform(click());
    }

    public static void clickButtonOkNotification(){
        Allure.step("Нажать кнопку ОК Уведомления");
        onView(getClaimsElementsButtonOkNotification ())
                .perform(click());
    }

    public static void clickTitleFieldOneCharacter(){
        Allure.step("Ввести в поле Тема один символ");
        onView(getClaimsElementsTitleField())
                .perform(replaceText("1"), closeSoftKeyboard());
    }

    public static void clickTitleFieldMaximumCharacters(){
        Allure.step("Ввести в поле Тема максимально-допустимое количество символов");
        onView(getClaimsElementsTitleField())
                .perform(replaceText("123456789АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЭЮЯ1234567891011"), closeSoftKeyboard());
    }

    public static void clickExecutorFieldConsistsLettersAndNumbers(){
        Allure.step("Ввести в поле Исполнитель буквы латинского алфавита и цифры");
        onView(getClaimsElementsExecutorField())
                .perform(replaceText("И123ванов И456ванович И789ванов"), closeSoftKeyboard());
    }

    public static void clickExecutorFieldOtherData(){
        Allure.step("Ввести в поле Исполнитель другие данные");
        onView(getClaimsElementsExecutorField())
                .perform(replaceText("Иванов Иванович Иванов"), closeSoftKeyboard());
    }

    public static void clickExecutorFieldSpecialCharacters(){
        Allure.step("Ввести в поле Исполнитель спецсимволы");
        onView(getClaimsElementsExecutorField())
                .perform(replaceText("@#$$%%^&**"), closeSoftKeyboard());
    }

    static String nextYear = "30.05.2023";
    public static void clickDateFieldNextYear(){
        Allure.step("В поле Дата выбрать дату будущего года");
        onView(getClaimsElementsDateField ())
                .perform(replaceText(nextYear));
    }

    public static void clickOpenClaim(){
        Allure.step("Открыть Заявку");
        onView(getClaimsElementsOpenClaim())
                .perform(click());
    }

    public static void clickEditClaim(){
        Allure.step("Нажать на кнопку Редактирование Заявки");
        onView(getClaimsElementsEditClaim())
                .perform(click());
    }

    public static void clickEditClaimStatusOpen(){
        Allure.step("Нажать на заявку, находящаяся в статусе Открыта, для редактирования");
        onView(getClaimsElementsDescriptionField())
                .perform(clearText(),replaceText("Редактирование заявки, находящаяся в статусе Открыта"),
                closeSoftKeyboard());
    }

    public static void clickButtonAddComment(){
        Allure.step("Нажать на кнопку Добавить комментарий");
        onView(getClaimsElementsButtonAddComment())
                .perform(click());
    }

    public static void clickCommentField(){
        Allure.step("В поле Комментарий добавить комментарий");
        onView(getClaimsElementsCommentField())
                .perform(clearText(), replaceText("Новый комментарий"), closeSoftKeyboard());
    }

    public static void clickButtonEditComment(){
        Allure.step("Нажать на кнопку Редактировать комментарий");
        onView(getClaimsElementsButtonEditComment())
                .perform(click());
    }

    public static void clickButtonSettings(){
        Allure.step("Нажать на кнопку Настройки");
        onView(getClaimsElementsButtonSettings())
                .perform(click());
    }

    public static void clickButtonTakeToWork(){
        Allure.step("Сменить статус заявки, находящаяся в статусе Открыта на статус В работе");
        onView(getClaimsElementsButtonTakeToWork())
                .perform(click());
    }

    public static void clickButtonCancel(){
        Allure.step("Нажать на кнопку Отмена");
        onView(getClaimsElementsButtonCancel())
                .perform(click());
    }
}