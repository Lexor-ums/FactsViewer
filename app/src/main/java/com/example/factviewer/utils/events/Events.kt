package com.example.factviewer.utils.events

import com.example.factviewer.data.application.AnimalFact

sealed class Events {
    class OnFactsListReceived(val list: List<AnimalFact>) : Events()
    class OnDetailsReceived(val  details : String?, val id : String) : Events()
}