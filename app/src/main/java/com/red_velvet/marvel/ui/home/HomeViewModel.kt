package com.red_velvet.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.domain.repository.MarvelRepository
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State

class HomeViewModel : BaseViewModel(), HomeInteractionListener {

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    private val _navigationToEventDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    private val _comics = MutableLiveData<State<List<ComicDto>>>(State.Loading)
    val comicsLiveData: LiveData<State<List<ComicDto>>> = _comics

    private val _events = MutableLiveData<State<List<EventDto>>>(State.Loading)
    val eventLiveData: LiveData<State<List<EventDto>>> = _events

    private val _characters = MutableLiveData<State<List<CharacterDto>>>(State.Loading)
    val characterLiveData: LiveData<State<List<CharacterDto>>> = _characters


    init {
        getComics()
        getEvents()
        getCharacters()
    }

    fun getComics() {
        bindStateUpdates(
            repository.getAllComics(),
            onError = ::onGetComicsFailure,
            onNext = ::onGetComicsState
        )
    }

    private fun onGetComicsFailure(throwable: Throwable) {
        _comics.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetComicsState(state: State<List<ComicDto>>) {
        _comics.postValue(state)
    }

    fun getEvents() {
        bindStateUpdates(
            repository.getAllEvents(),
            onError = ::onGetEventsFailure,
            onNext = ::onGetEventsState
        )
    }

    private fun onGetEventsFailure(throwable: Throwable) {
        _events.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetEventsState(state: State<List<EventDto>>) {
        _events.postValue(state)
    }

    fun getCharacters() {
        bindStateUpdates(
            repository.getAllCharacters(),
            onError = ::onGetCharactersFailure,
            onNext = ::onGetCharactersState
        )
    }

    private fun onGetCharactersFailure(throwable: Throwable) {
        _characters.postValue(State.Failed(throwable.message ?: UNKNOWN_ERROR))
    }

    private fun onGetCharactersState(state: State<List<CharacterDto>>) {
        _characters.postValue(state)
    }

    override fun doOnComicClicked(comicId: Int) {
        _navigationToComicDetails.postValue(SingleEvent(comicId))
    }

    override fun doOnEventClicked(eventId: Int) {
        _navigationToEventDetails.postValue(SingleEvent(eventId))
    }

    override fun doOnCharacterClicked(charId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(charId))
    }

    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}