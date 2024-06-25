package com.petscreening.boatrentalservice.models.filters

data class GetPetsFilter(
    val isUnderMaxWeight: Boolean? = null,
    val isVaccinated: Boolean? = null,
    val isNotPoodle: Boolean? = null,
    val isAboveMinTrainingLevel: Boolean? = null,
    val isBoatRentalEligible: Boolean? = null
)