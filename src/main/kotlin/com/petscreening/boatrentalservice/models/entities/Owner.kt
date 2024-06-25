package com.petscreening.boatrentalservice.models.entities

import com.petscreening.boatrentalservice.models.dto.OwnerDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(schema = "brs")
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String? = null,
    var email: String? = null,
    var address: String,
) {
    fun toDto() = OwnerDto(
        id = id,
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        address = address
    )
}