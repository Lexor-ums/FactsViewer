package com.example.factviewer.data.adapters

import com.example.factviewer.data.application.AnimalFact
import com.example.factviewer.data.database.entity.AnimalFactUnit
import com.example.factviewer.data.network.Fact

object DataAdapters {
    private fun fromNetworkToAppEntity(fact: Fact) =
        AnimalFact(
            "",
            fact._id,
            fact.text,
            false
        )

    private fun fromDBToAppEntity(animlaFactUnit: AnimalFactUnit) =
        AnimalFact(
            "",
            animlaFactUnit.id,
            animlaFactUnit.fact,
            false
        )

    private fun fromAppToDb(fact: AnimalFact, animal: String) =
        AnimalFactUnit(
            fact.id,
            animal,
            fact.content
        )

    fun fromAnimalFactListToDbUnitList(facts: List<AnimalFact>, animal: String): List<AnimalFactUnit>? {
        var appList = mutableListOf<AnimalFactUnit>()
        if (facts != null) {
            for (it in facts) {
                appList.add(fromAppToDb(it, animal))
            }
        }
        return if (appList.size != 0)
            appList
        else
            return null
    }

    fun dbUnitListToAnimalFactList(units: List<AnimalFactUnit>?): MutableList<AnimalFact>? {
        var appList = mutableListOf<AnimalFact>()
        if (units != null) {
            for (it in units) {
                appList.add(fromDBToAppEntity(it))
            }
        }
        return if (appList.size != 0)
            appList
        else
            return null
    }

    fun factListToAnimalList(factUnits: List<Fact>?): MutableList<AnimalFact>? {
        var appList = mutableListOf<AnimalFact>()
        if (factUnits != null) {
            for (it in factUnits) {
                appList.add(fromNetworkToAppEntity(it))
            }
        }
        return if (appList.size != 0)
            appList
        else
            null
    }
}