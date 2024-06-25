package com.petscreening.boatrentalservice.models.inputs

import com.petscreening.boatrentalservice.models.entities.Owner

data class OwnerInput(
    var firstName: String,
    var lastName: String,
    var phoneNumber: String? = null,
    var email: String? = null,
    var address: String,
) {
    fun toEntity() = Owner(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        address = address
    )
}