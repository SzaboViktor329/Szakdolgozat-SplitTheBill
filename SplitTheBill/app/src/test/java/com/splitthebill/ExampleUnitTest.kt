package com.splitthebill

import com.splitthebill.ui.viewmodels.UserViewModel
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val mockuserViewModel: UserViewModel = Mockito.mock(UserViewModel::class.java, Mockito.RETURNS_DEEP_STUBS)
        Mockito.`when`(mockuserViewModel.email).thenReturn("dasdsa@ssad.com")
        mockuserViewModel.searchUserByUsername("asda", {asd, asdd->})
        val asdsa = mockuserViewModel.email
        val k = 10
        assertEquals(4, 2 + 2)
    }
}