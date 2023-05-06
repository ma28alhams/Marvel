package com.red_velvet.marvel.ui.story

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class StoryCreatorAdapter(items: List<CreatorsResponse>, listener: BaseInteractionListener) :
    BaseAdapter<CreatorsResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_story_creator
}