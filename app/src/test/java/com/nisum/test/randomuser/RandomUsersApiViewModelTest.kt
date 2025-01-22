package com.nisum.test.randomuser

import app.cash.turbine.test
import com.nisum.test.randomuser.business_logic.ApiService
import com.nisum.test.randomuser.business_logic.data.Coordinates
import com.nisum.test.randomuser.business_logic.data.Dob
import com.nisum.test.randomuser.business_logic.data.Id
import com.nisum.test.randomuser.business_logic.data.Info
import com.nisum.test.randomuser.business_logic.data.Location
import com.nisum.test.randomuser.business_logic.data.Login
import com.nisum.test.randomuser.business_logic.data.Name
import com.nisum.test.randomuser.business_logic.data.Picture
import com.nisum.test.randomuser.business_logic.data.RandomUser
import com.nisum.test.randomuser.business_logic.data.RandomUsersModel
import com.nisum.test.randomuser.business_logic.data.Registered
import com.nisum.test.randomuser.business_logic.data.Street
import com.nisum.test.randomuser.business_logic.data.Timezone
import com.nisum.test.randomuser.business_logic.repository.RandomUsersApiRepository
import com.nisum.test.randomuser.business_logic.viewmodel.RandomUsersApiViewModel
import com.nisum.test.randomuser.ui.data.PaginationState
import com.nisum.test.randomuser.ui.data.RandomUserModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


@OptIn(ExperimentalCoroutinesApi::class)
class RandomUsersApiViewModelTest {

    private lateinit var viewModel: RandomUsersApiViewModel

    private lateinit var mockApiService: ApiService
    private lateinit var randomUsersApiRepository: RandomUsersApiRepository


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        mockApiService = Mockito.mock(ApiService::class.java)
        randomUsersApiRepository = RandomUsersApiRepository(mockApiService)
        viewModel = RandomUsersApiViewModel(randomUsersApiRepository)
    }

    @Test
    fun `test randomUsers success`() = runTest {
        //Arrange
        val listRandomUser = mutableListOf<RandomUser>(
        )
        for (i in 1..2) {
            listRandomUser.add(
                RandomUser(
                    cell = "",
                    dob = Dob(age = 30, date = ""),
                    email = "santosh@gmail.com",
                    gender = "male",
                    id = Id("cpf", "156.8"),
                    location = Location(
                        city = "Vizag",
                        coordinates = Coordinates(latitude = "0.0", longitude = "0.0"),
                        country = "India",
                        postcode = "",
                        state = "AP",
                        street = Street("Padmavathi colony", 2),
                        timezone = Timezone("", "")
                    ),
                    login = Login("", "", "", "", "", "", ""),
                    name = Name(first = "Santosh", last = "Kumar", title = "SV"),
                    nat = "Indian",
                    phone = "8468809378",
                    picture = Picture(
                        large = "https://randomuser.me/api/portraits/men/40.jpg",
                        medium = "",
                        thumbnail = ""
                    ),
                    registered = Registered(30, "")
                )
            )
        }
        val mockUsers = RandomUsersModel(Info(1, 2, "abc", "1.4"), listRandomUser, "")
        `when`(mockApiService.getRandomUsers(page = 1, results = 2)).thenReturn(
            mockUsers
        )
        //Act
        viewModel.getRandomUsers(2)
        delay(1000)
        //Assert
        viewModel.randomUsersListStateFlow.test {
            assertEquals(
                mutableListOf(
                    RandomUserModel(
                        "Santosh",
                        "Kumar",
                        "2-Padmavathi colony Vizag AP India",
                        "https://randomuser.me/api/portraits/men/40.jpg",
                        "male",
                        "santosh@gmail.com",
                        30,
                        "8468809378"
                    ), RandomUserModel(
                        "Santosh",
                        "Kumar",
                        "2-Padmavathi colony Vizag AP India",
                        "https://randomuser.me/api/portraits/men/40.jpg",
                        "male",
                        "santosh@gmail.com",
                        30,
                        "8468809378"
                    )
                ), awaitItem()
            )
            cancelAndConsumeRemainingEvents()
        }
        verify(mockApiService).getRandomUsers(page = 1, results = 2, seed = "abc")
    }

    @Test
    fun `test randomUsers fail`() = runTest {
        //Arrange
        val mockUsers = RandomUsersModel(
            Info(1, 1, "abc", "1.4"),
            null,
            "Uh oh, something has gone wrong. Please tweet us @randomapi about the issue. Thank you."
        )
        `when`(mockApiService.getRandomUsers(page = 1, results = 1, seed = "abc")).thenReturn(
            mockUsers
        )
        //Act
        viewModel.getRandomUsers(1)
        delay(1000)
        //Assert
        viewModel.pagingStateFlow.test {
            assertEquals(PaginationState.ERROR, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify(mockApiService).getRandomUsers(page = 1, results = 1, seed = "abc")
    }

    @Test
    fun `test randomUsers canPaginate`() = runTest {
        //Arrange
        val listRandomUser = mutableListOf<RandomUser>(

        )
        for (i in 1..10) listRandomUser.add(
            RandomUser(
                cell = "",
                dob = Dob(age = 30, date = ""),
                email = "santosh@gmail.com",
                gender = "male",
                id = Id("cpf", "156.8"),
                location = Location(
                    city = "Vizag",
                    coordinates = Coordinates(latitude = "0.0", longitude = "0.0"),
                    country = "India",
                    postcode = "",
                    state = "AP",
                    street = Street("Padmavathi colony", 2),
                    timezone = Timezone("", "")
                ),
                login = Login("", "", "", "", "", "", ""),
                name = Name(first = "Santosh", last = "Kumar", title = "SV"),
                nat = "Indian",
                phone = "8468809378",
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/men/40.jpg",
                    medium = "",
                    thumbnail = ""
                ),
                registered = Registered(30, "")
            )
        )

        val mockUsers = RandomUsersModel(Info(1, 10, "abc", "1.4"), listRandomUser, "")
        `when`(mockApiService.getRandomUsers(page = 1, results = 10, seed = "abc")).thenReturn(
            mockUsers
        )
        //Act
        viewModel.getRandomUsers(11)
        delay(1000)
        //Assert
        viewModel.canPaginateStateFlow.test {
            assertEquals(true, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify(mockApiService).getRandomUsers(page = 1, results = 10, seed = "abc")
    }

    @Test
    fun `test randomUsers cantPaginate`() = runTest {
        //Arrange
        val listRandomUser = mutableListOf<RandomUser>(

        )
        for (i in 1..10) listRandomUser.add(
            RandomUser(
                cell = "",
                dob = Dob(age = 30, date = ""),
                email = "santosh@gmail.com",
                gender = "male",
                id = Id("cpf", "156.8"),
                location = Location(
                    city = "Vizag",
                    coordinates = Coordinates(latitude = "0.0", longitude = "0.0"),
                    country = "India",
                    postcode = "",
                    state = "AP",
                    street = Street("Padmavathi colony", 2),
                    timezone = Timezone("", "")
                ),
                login = Login("", "", "", "", "", "", ""),
                name = Name(first = "Santosh", last = "Kumar", title = "SV"),
                nat = "Indian",
                phone = "8468809378",
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/men/40.jpg",
                    medium = "",
                    thumbnail = ""
                ),
                registered = Registered(30, "")
            )
        )

        val mockUsers = RandomUsersModel(Info(1, 10, "abc", "1.4"), listRandomUser, "")
        `when`(mockApiService.getRandomUsers(page = 1, results = 10, seed = "abc")).thenReturn(
            mockUsers
        )
        //Act
        viewModel.getRandomUsers(10)
        delay(1000)
        //Assert
        viewModel.canPaginateStateFlow.test {
            assertEquals(false, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify(mockApiService).getRandomUsers(page = 1, results = 10, seed = "abc")

    }


    @Test
    fun `test randomUsers paginationExhaust`() = runTest {
        //Arrange
        val listRandomUser = mutableListOf<RandomUser>(

        )
        for (i in 1..10) listRandomUser.add(
            RandomUser(
                cell = "",
                dob = Dob(age = 30, date = ""),
                email = "santosh@gmail.com",
                gender = "male",
                id = Id("cpf", "156.8"),
                location = Location(
                    city = "Vizag",
                    coordinates = Coordinates(latitude = "0.0", longitude = "0.0"),
                    country = "India",
                    postcode = "",
                    state = "AP",
                    street = Street("Padmavathi colony", 2),
                    timezone = Timezone("", "")
                ),
                login = Login("", "", "", "", "", "", ""),
                name = Name(first = "Santosh", last = "Kumar", title = "SV"),
                nat = "Indian",
                phone = "8468809378",
                picture = Picture(
                    large = "https://randomuser.me/api/portraits/men/40.jpg",
                    medium = "",
                    thumbnail = ""
                ),
                registered = Registered(30, "")
            )
        )

        val mockUsers = RandomUsersModel(Info(1, 10, "abc", "1.4"), listRandomUser, "")
        `when`(mockApiService.getRandomUsers(page = 1, results = 10, seed = "abc")).thenReturn(
            mockUsers
        )
        //Act
        viewModel.getRandomUsers(10)
        delay(1000)
        //Assert
        viewModel.pagingStateFlow.test {
            assertEquals(PaginationState.PAGINATION_EXHAUST, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
        verify(mockApiService).getRandomUsers(page = 1, results = 10, seed = "abc")

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}