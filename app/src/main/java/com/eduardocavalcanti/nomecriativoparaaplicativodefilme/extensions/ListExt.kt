package com.eduardocavalcanti.nomecriativoparaaplicativodefilme.extensions

import com.eduardocavalcanti.nomecriativoparaaplicativodefilme.data.Result

fun List<Result>.cleanNullData(): List<Result>{

    val newList = mutableListOf<Result>()

    forEach {
        if(!it.hasEmptyField()) newList.add(it)
    }

    return  newList
}

