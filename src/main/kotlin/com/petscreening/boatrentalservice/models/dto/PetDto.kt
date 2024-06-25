package com.petscreening.boatrentalservice.models.dto

data class PetDto(
    var id: Long? = null,
    var name: String,
    var weight: Double,
    var breed: String,
    var isVaccinated: Boolean,
    var trainingLevel: Int,
    var isBoatRentalEligible: Boolean
)