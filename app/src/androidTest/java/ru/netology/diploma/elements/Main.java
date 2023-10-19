package ru.netology.diploma.elements;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import ru.netology.diploma.steps.MainSteps;

public class Main extends MainSteps {

    public static Matcher<View> getMainElementsButtonAllNews() {
        return withId(R.id.all_news_text_view);
    }

    public static Matcher<View> getMainElementsButtonMainMenu() {
        return allOf(withId(R.id.main_menu_image_button));
    }

    public static Matcher<View> getMainElementsButtonClaims() {
        return withId(R.id.all_claims_text_view);
    }
}