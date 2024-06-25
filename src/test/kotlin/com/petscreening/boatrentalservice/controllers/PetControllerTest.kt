package com.petscreening.boatrentalservice.controllers

import com.petscreening.boatrentalservice.models.filters.GetPetsFilter
import com.petscreening.boatrentalservice.models.inputs.PetInput
import com.petscreening.boatrentalservice.services.PetService
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import kotlin.test.Test

class PetControllerTest {


    @Test
    fun `getPets() should be called once`() {
        // Given
        val petService = Mockito.mock(PetService::class.java)
        val petController = PetController(petService)

        val getPetsFilter = GetPetsFilter(isUnderMaxWeight = true)

        // When
        petController.getPets(getPetsFilter)

        // Then
        verify(petService, times(1)).getPets(getPetsFilter)
    }

    @Test
    fun `addPet() should be called once`() {
        // Given

        val petService = Mockito.mock(PetService::class.java)
        val petController = PetController(petService)

        val petInput = PetInput(
            name = "Max",
            weight = 30.0,
            breed = "Poodle",
            isVaccinated = true,
            trainingLevel = 5
        )

        // When
        petController.addPet(petInput)

        // Then
        verify(petService, times(1)).addPet(petInput)
    }
}