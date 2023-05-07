package com.red_velvet.marvel.ui.series

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class SeriesAdapter(items: List<SeriesResponse>, listener: SeriesInteractionListener) :
    BaseAdapter<SeriesResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_series
}

interface SeriesInteractionListener : BaseInteractionListener {
    fun onSeriesClicked(seriesId: Int)
}