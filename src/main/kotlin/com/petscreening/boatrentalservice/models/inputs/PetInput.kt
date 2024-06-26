package com.petscreening.boatrentalservice.models.inputs

import com.petscreening.boatrentalservice.models.entities.Pet

data class PetInput(
    var name: String,
    var weight: Double,
    var breed: String,
    var isVaccinated: Boolean,
    var trainingLevel: Int,
    var ownerId: Long
) {
    fun toEntity() = Pet(
        name = name,
        weight = weight,
        breed = breed,
        isVaccinated = isVaccinated,
        trainingLevel = trainingLevel
    )
}