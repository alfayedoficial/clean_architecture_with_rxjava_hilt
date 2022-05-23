package com.fourdev.cleanarchitecturewithrxjavahilt.ui.feature.map.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.BaseViewModel
import com.fourdev.cleanarchitecturewithrxjavahilt.core.common.DataState
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.dto.LocationDto
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.interactor.GetRestaurants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:28 PM
 */
@HiltViewModel
class MapViewModel @Inject constructor(private val getRestaurants: GetRestaurants) : BaseViewModel() {

    private val _restaurantsState = MutableLiveData<DataState<List<Restaurant>>>()
     val restaurantsState : LiveData<DataState<List<Restaurant>>>
        get() = _restaurantsState


    fun getRestaurants(locationDto: LocationDto) {
        if (_restaurantsState.value != null)  return
        getRestaurants.execute(locationDto)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ restaurants ->
                _restaurantsState.postValue(restaurants)
            }
            .also { compositeDisposable.add(it) }
    }

}
