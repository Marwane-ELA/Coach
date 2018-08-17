package com.marwane.coach.view;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.marwane.coach.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=vXRoVIGttO4
 *
 * Test the transition between two activity
 * use de espresse library to do clicks on buttons
 */
public class InfoActivityTest {

    //Launch main activity
    @Rule
    public ActivityTestRule<InfoActivity> mActivityTestRule = new ActivityTestRule<InfoActivity>(InfoActivity.class);

    private InfoActivity mActivity = null;

    //create a monitor for the second activity(the acitvity where we want to go)
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception
    {

        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfMainActivityOnButtonClick()
    {
        assertNotNull(mActivity.findViewById(R.id.button));

        //perform click
        Espresso.onView(withId(R.id.button)).perform(click());

        //with for Mainactivity (5s)
        //Monitor return the mainActivity
       Activity MainActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

       assertNotNull(MainActivity);

       MainActivity.finish();

    }

    @After
    public void tearDown() throws Exception
    {

        mActivity = null;
    }
}