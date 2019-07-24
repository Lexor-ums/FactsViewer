package com.example.factviewer.ui.factslistfragment

import java.sql.Time

data class FactShortItem(val time: Time,
                         val author : String,
                         val fact  :String)