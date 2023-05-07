package com.red_velvet.marvel.ui.characterDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharacterSeriesAdapter(items: List<Series>, listener: SeriesInteractionListener) :
    BaseAdapter<Series>(items, listener) {
    override val layoutId: Int = R.layout.item_character_series
}

interface SeriesInteractionListener : BaseInteractionListener {
}