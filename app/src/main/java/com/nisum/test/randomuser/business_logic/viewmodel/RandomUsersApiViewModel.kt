package com.nisum.test.randomuser.business_logic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisum.test.randomuser.business_logic.repository.RandomUsersApiRepository
import com.nisum.test.randomuser.ui.data.PaginationState
import com.nisum.test.randomuser.ui.data.RandomUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject

@HiltViewModel
class RandomUsersApiViewModel @Inject constructor(
    private val repository: RandomUsersApiRepository
) : ViewModel() {
    private val _randomUsersListStateFlow =
        MutableStateFlow<MutableList<RandomUserModel>>(mutableListOf())
    val randomUsersListStateFlow: StateFlow<List<RandomUserModel>> get() = _randomUsersListStateFlow.asStateFlow()
    private val _pagingStateFlow = MutableStateFlow(PaginationState.REQUEST_INACTIVE)
    val pagingStateFlow: StateFlow<PaginationState>
        get() = _pagingStateFlow.asStateFlow()

    private var page = 1
    private var thereAreRemainingResults = false
    private val _canPaginateStateFlow = MutableStateFlow(false)
    val canPaginateStateFlow: StateFlow<Boolean> get() = _canPaginateStateFlow.asStateFlow()


    fun getRandomUsers(num: Int) {
        _pagingStateFlow.value =
            if (page == INITIAL_PAGE) PaginationState.LOADING else PaginationState.PAGINATING
        viewModelScope.launch {
            try {
                val randomUsersModel = if (num <= PAGE_SIZE) repository.getRandomUsers(page, num)
                else if (thereAreRemainingResults) {
                    repository.getRandomUsers(page, num % PAGE_SIZE)
                } else {
                    repository.getRandomUsers(page, PAGE_SIZE)
                }

                randomUsersModel.apply {
                    val results = results
                    if (page == INITIAL_PAGE) {
                        if (results!!.isEmpty()) {
                            _pagingStateFlow.value = PaginationState.EMPTY
                            return@apply
                        }
                        _randomUsersListStateFlow.value.clear()
                        _randomUsersListStateFlow.value.addAll(results.map { randomUser ->
                            RandomUserModel(
                                randomUser.name.first,
                                randomUser.name.last,
                                "${randomUser.location.street.number}-${randomUser.location.street.name} ${randomUser.location.city} ${randomUser.location.state} ${randomUser.location.country}",
                                randomUser.picture.large,
                                randomUser.gender,
                                randomUser.email,
                                randomUser.dob.age,
                                randomUser.phone
                            )
                        })
                    } else {
                        _randomUsersListStateFlow.value.addAll(results!!.map { randomUser ->
                            RandomUserModel(
                                randomUser.name.first,
                                randomUser.name.last,
                                "${randomUser.location.street.number}-${randomUser.location.street.name} ${randomUser.location.city} ${randomUser.location.state} ${randomUser.location.country}",
                                randomUser.picture.large,
                                randomUser.gender,
                                randomUser.email,
                                randomUser.dob.age,
                                randomUser.phone
                            )
                        })
                    }
                }
                _pagingStateFlow.value = PaginationState.REQUEST_INACTIVE
                _canPaginateStateFlow.value = _randomUsersListStateFlow.value.size < num
                if (_canPaginateStateFlow.value) {
                    if (num - _randomUsersListStateFlow.value.size < PAGE_SIZE) thereAreRemainingResults =
                        true
                    page++
                }
                if (!canPaginateStateFlow.value) {
                    _pagingStateFlow.value = PaginationState.PAGINATION_EXHAUST
                }


            } catch (e: Exception) {
                _pagingStateFlow.value =
                    if (page == INITIAL_PAGE) PaginationState.ERROR else PaginationState.PAGINATION_EXHAUST
            }

        }


    }


    fun clearPaging() {
        page = INITIAL_PAGE
        thereAreRemainingResults = false
        _pagingStateFlow.value = PaginationState.LOADING
        _canPaginateStateFlow.value = false
    }

    companion object {
        const val INITIAL_PAGE = 1
        const val PAGE_SIZE = 10
    }
}
