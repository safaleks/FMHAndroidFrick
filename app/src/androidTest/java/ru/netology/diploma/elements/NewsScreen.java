package ru.netology.diploma.elements;


import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.netology.diploma.AllClaimsTest.withIndex;

import android.view.View;

import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;
import ru.netology.diploma.steps.NewsSteps;

public class NewsScreen extends NewsSteps {


    public static Matcher<View> getNewsElementsButtonNews() {
        return allOf(withId(android.R.id.title), withText("News"));
    }

    public static Matcher<View> getNewsElementsButtonExpandNews() {
        return allOf(withIndex(withId(R.id.view_news_item_image_view), 2));
    }

    public static Matcher<View> getNewsElementsButtonSorting() {
        return allOf(withId(R.id.sort_news_material_button));
    }

    public static Matcher<View> getNewsElementsButtonFilterNews() {
        return allOf(withId(R.id.filter_news_material_button));
    }

    public static Matcher<View> getNewsElementsButtonCategoryFilter() {
        return allOf(withId(R.id.filter_button));
    }

    public static Matcher<View> getNewsElementsButtonDateStart() {
        return  allOf(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    }

    public static Matcher<View> getNewsElementsButtonOkDateStart() {
        return allOf(withId(android.R.id.button1));
    }

    public static Matcher<View> getNewsElementsButtonDateEnd() {
        return allOf(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    }

    public static Matcher<View> getNewsElementsButtonOkWrongMessage() {
        return allOf(withId(android.R.id.button1));
    }


    public static Matcher<View> getNewsElementsTitleFilterNews() {
        return allOf(withId(R.id.filter_news_title_text_view));
    }
}