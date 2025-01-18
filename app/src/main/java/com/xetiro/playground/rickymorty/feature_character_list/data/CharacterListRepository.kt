package com.xetiro.playground.rickymorty.feature_character_list.data

import com.xetiro.playground.rickymorty.feature_character_list.data.model.Character

interface CharacterListRepository {

    fun loadCharacters(): DataResult<List<Character>>

}
