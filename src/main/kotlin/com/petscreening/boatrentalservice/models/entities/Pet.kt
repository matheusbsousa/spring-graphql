package com.petscreening.boatrentalservice.models.entities

import com.petscreening.boatrentalservice.models.dto.PetDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(schema = "brs")
class Pet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var weight: Double,
    var breed: String,
    var isVaccinated: Boolean,
    var trainingLevel: Int,
    var isBoatRentalEligible: Boolean? = null
) {
    fun toDto() = PetDto(
        id = id,
        name = name,
        weight = weight,
        breed = breed,
        isVaccinated = isVaccinated,
        trainingLevel = trainingLevel,
        isBoatRentalEligible = isBoatRentalEligible!!
    )
}