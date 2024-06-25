package com.petscreening.boatrentalservice.models.dto

data class OwnerDto(
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String? = null,
    var email: String? = null,
    var address: String,
)