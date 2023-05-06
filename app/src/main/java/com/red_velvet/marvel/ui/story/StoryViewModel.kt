package com.red_velvet.marvel.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseInteractionListener
import com.red_velvet.marvel.ui.base.BaseViewModel

class StoryViewModel : BaseViewModel(), BaseInteractionListener {

    private val _story: MutableLiveData<State<List<StoryResponse>>> = MutableLiveData()
    val story: LiveData<State<List<StoryResponse>>> = _story

    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicsResponse>>> get() = _comics

    private val _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorsResponse>>> get() = _creators

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        bindStateUpdates(
            repository.getStory(8),
            onNext = ::onGetStorySuccess,
            onError = ::onGetStoryError
        )
        bindStateUpdates(
            repository.getComicsByStoryId(8),
            onNext = ::onGetComicsSuccess,
            onError = ::onGetComicsError
        )
        bindStateUpdates(
            repository.getSerieCreatorsBySeriesId(8),
            onNext = ::onGetCreatorSuccess,
            onError = ::onGetSeriesError
        )
    }

    private fun onGetSeriesError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorSuccess(state: State<List<CreatorsResponse>?>) {
        _creators.postValue(State.Loading)
        state.toData()?.let {
            _creators.postValue(State.Success(it))
        }
    }

    private fun onGetComicsError(error: Throwable) {
        _comics.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetComicsSuccess(state: State<List<ComicsResponse>?>) {
        _comics.postValue(State.Loading)
        state.toData()?.let {
            _comics.postValue(State.Success(it))
        }
    }

    private fun onGetStoryError(error: Throwable) {
        _story.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetStorySuccess(state: State<List<StoryResponse>?>) {
        _story.postValue(State.Loading)
        state.toData()?.let {
            _story.postValue(State.Success(it))
        }
    }


}


