package com.red_velvet.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State


class StoriesViewModel : BaseViewModel(), StoriesInteractionListener {
    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)
    private val _stories: MutableLiveData<State<List<StoryResponse>>> = MutableLiveData()
    val stories: LiveData<State<List<StoryResponse>>> get() = _stories

    fun getAllStories() {
        bindStateUpdates(
            repository.getStories(),
            onError = ::onGetStoriesError,
            onNext = ::onGetStoriesSuccess
        )
    }

    private fun onGetStoriesSuccess(state: State<List<StoryResponse>?>) {
        _stories.postValue(State.Loading)
        state.toData()?.let {
            _stories.postValue(State.Success(it))
        }
    }

    private fun onGetStoriesError(error: Throwable) {
        _stories.postValue(State.Failed(error.message.toString()))
    }

    override fun onStoryClicked(storyId: Int) {

    }
}