package com.petscreening.boatrentalservice.services

import com.petscreening.boatrentalservice.models.dto.PetDto
import com.petscreening.boatrentalservice.models.entities.Pet
import com.petscreening.boatrentalservice.models.filters.GetPetsFilter
import com.petscreening.boatrentalservice.models.inputs.PetInput
import com.petscreening.boatrentalservice.repositories.PetRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.data.jpa.domain.Specification
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PetServiceTest {

    @Test
    fun `getPets should return a list of PetDto`() {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val petService = PetService(petRepository)

        val expected = listOf(
            PetDto(
                id = 1,
                name = "Max",
                weight = 10.0,
                breed = "Poodle",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = false
            ),
            PetDto(
                id = 2,
                name = "Buddy",
                weight = 20.0,
                breed = "Golden Retriever",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = true
            )
        )

        val petList = listOf(
            Pet(
                id = 1,
                name = "Max",
                weight = 10.0,
                breed = "Poodle",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = false
            ),
            Pet(
                id = 2,
                name = "Buddy",
                weight = 20.0,
                breed = "Golden Retriever",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = true
            )
        )

        val getPetsFilter = GetPetsFilter()

        // When
        `when`(petRepository.findAll(any<Specification<Pet>>())).thenReturn(petList)
        val actual = petService.getPets(getPetsFilter)

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `addPet should return a boatRentalEligible PetDto`() {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val petService = PetService(petRepository)

        val expected = PetDto(
            id = 1,
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
            isBoatRentalEligible = true
        )

        val petInput = PetInput(
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
        )

        val pet = Pet(
            id = 1,
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
            isBoatRentalEligible = true
        )

        // When
        `when`(petRepository.save(any())).thenReturn(pet)
        val actual = petService.addPet(petInput)

        // Then
        assertEquals(expected, actual)
    }


    @ParameterizedTest
    @MethodSource("providePetInputs")
    fun `addPet should return a not boatRentalEligible PetDto`(petInput: PetInput, expected: PetDto) {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val petService = PetService(petRepository)

        val pet = Pet(
            id = 1,
            name = petInput.name,
            weight = petInput.weight,
            breed = petInput.breed,
            isVaccinated = petInput.isVaccinated,
            trainingLevel = petInput.trainingLevel,
            isBoatRentalEligible = expected.isBoatRentalEligible
        )

        // When
        `when`(petRepository.save(any())).thenReturn(pet)
        val actual = petService.addPet(petInput)

        // Then
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun providePetInputs() = listOf(
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 20.0,
                    breed = "Poodle",
                    isVaccinated = true,
                    trainingLevel = 5,
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 20.0,
                    breed = "Poodle",
                    isVaccinated = true,
                    trainingLevel = 5,
                    isBoatRentalEligible = false
                )
            ),
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 27.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 5,
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 27.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 5,
                    isBoatRentalEligible = false
                )
            ),
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 2,
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 2,
                    isBoatRentalEligible = false
                )
            ),
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = false,
                    trainingLevel = 5,
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = false,
                    trainingLevel = 5,
                    isBoatRentalEligible = false
                )
            ),

            // Add more test cases here
        )
    }

}