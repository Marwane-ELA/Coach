package com.marwane.coach.view;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.marwane.coach.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Bronnen
 * https://youtu.be/_TR6QcRozAg
 * test if the activity is launched
 */
public class InfoActivityButtonTest {

    @Rule
    public ActivityTestRule<InfoActivity> mActivityTestRule = new ActivityTestRule<InfoActivity>(InfoActivity.class);

    private InfoActivity mActivity = null;


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){

        View view = mActivity.findViewById(R.id.txtTest);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}