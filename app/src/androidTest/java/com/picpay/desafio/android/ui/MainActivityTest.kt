package com.picpay.desafio.android.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.internal.ContextUtils.getActivity
import com.picpay.desafio.android.R
import com.picpay.desafio.android.base.BaseUITest
import com.picpay.desafio.android.di.generateTestApp
import com.picpay.desafio.android.helpers.RecyclerViewMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import java.net.HttpURLConnection


class MainActivityTest : BaseUITest() {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun start() {
        super.setup()

        loadKoinModules(generateTestApp(getMockWebServerUrl()))
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
        unloadKoinModules(generateTestApp(getMockWebServerUrl()))
    }

    @Test
    fun shouldDisplayListItem() {
        launchActivity<MainActivity>().apply {
            mockNetworkResponseWithFileContent("success_resp_list.json", HttpURLConnection.HTTP_OK)

            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.rvUserList,
                0,
                isDisplayed()
            )
            onView(withText("Eduardo Santos")).check(matches(isDisplayed()))
            onView(withText("@eduardo.santos")).check(matches(isDisplayed()))
        }
        unloadKoinModules(generateTestApp(getMockWebServerUrl()))
    }

    @Test
    fun shouldDisplayErrorMessage() {
        launchActivity<MainActivity>().apply {
            mockNetworkResponseWithFileContent("error_resp.json", 418)

            onView(withText("I'm a teapot")).inRoot(
                withDecorView(not(`is`(getActivity(context)?.window?.decorView)))
            ).check(matches(isDisplayed()))
        }
        unloadKoinModules(generateTestApp(getMockWebServerUrl()))
    }
}