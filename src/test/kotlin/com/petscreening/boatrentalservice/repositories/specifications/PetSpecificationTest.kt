package com.petscreening.boatrentalservice.repositories.specifications

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class PetSpecificationTest {

    @Test
    fun isUnderMaxWeight() {
        // Given
        val isUnderMaxWeight = true

        // When
        val result = PetSpecification.isUnderMaxWeight(isUnderMaxWeight)

        // Then
        assertNotNull(result)
    }

    @Test
    fun isVaccinated() {
        // Given
        val isVaccinated = true

        // When
        val result = PetSpecification.isVaccinated(isVaccinated)

        // Then
        assertNotNull(result)
    }

    @Test
    fun isNotPoodle() {
        // Given
        val isNotPoodle = true

        // When
        val result = PetSpecification.isNotPoodle(isNotPoodle)

        // Then
        assertNotNull(result)
    }

    @Test
    fun isAboveMinTrainingLevel() {
        // Given
        val isAboveMinTrainingLevel = true

        // When
        val result = PetSpecification.isAboveMinTrainingLevel(isAboveMinTrainingLevel)

        // Then
        assertNotNull(result)
    }

    @Test
    fun isBoatRentalEligible() {
        // Given
        val isBoatRentalEligible = true

        // When
        val result = PetSpecification.isBoatRentalEligible(isBoatRentalEligible)

        // Then
        assertNotNull(result)
    }
}