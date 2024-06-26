package com.petscreening.boatrentalservice.services

import com.petscreening.boatrentalservice.excpetions.ResourceNotFoundException
import com.petscreening.boatrentalservice.models.dto.OwnerDto
import com.petscreening.boatrentalservice.models.dto.PetDto
import com.petscreening.boatrentalservice.models.entities.Owner
import com.petscreening.boatrentalservice.models.entities.Pet
import com.petscreening.boatrentalservice.models.filters.GetPetsFilter
import com.petscreening.boatrentalservice.models.inputs.PetInput
import com.petscreening.boatrentalservice.repositories.OwnerRepository
import com.petscreening.boatrentalservice.repositories.PetRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.springframework.data.jpa.domain.Specification
import java.util.*
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PetServiceTest {

    @Test
    fun `getPets should return a list of PetDto`() {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val ownerRepository = Mockito.mock(OwnerRepository::class.java)
        val petService = PetService(petRepository, ownerRepository)

        val expected = listOf(
            PetDto(
                id = 1,
                name = "Max",
                weight = 10.0,
                breed = "Poodle",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = false,
                owner = OwnerDto(
                    id = 1,
                    firstName = "John",
                    lastName = "Doe",
                    email = "test@test.com",
                    address = "1234 Test St",
                    phoneNumber = "123-456-7890"
                )
            ),
            PetDto(
                id = 2,
                name = "Buddy",
                weight = 20.0,
                breed = "Golden Retriever",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = true,
                owner = OwnerDto(
                    id = 1,
                    firstName = "John",
                    lastName = "Doe",
                    email = "test@test.com",
                    address = "1234 Test St",
                    phoneNumber = "123-456-7890"
                )
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
                isBoatRentalEligible = false,
                owner = getOwner()
            ),
            Pet(
                id = 2,
                name = "Buddy",
                weight = 20.0,
                breed = "Golden Retriever",
                isVaccinated = true,
                trainingLevel = 5,
                isBoatRentalEligible = true,
                owner = getOwner()
            )
        )

        val getPetsFilter = GetPetsFilter()

        // When
        `when`(petRepository.findAll(any<Specification<Pet>>())).thenReturn(petList)
        `when`(ownerRepository.findById(1)).thenReturn(Optional.of(getOwner()))
        val actual = petService.getPets(getPetsFilter)

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `addPet should return a boatRentalEligible PetDto`() {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val ownerRepository = Mockito.mock(OwnerRepository::class.java)
        val petService = PetService(petRepository, ownerRepository)

        val expected = PetDto(
            id = 1,
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
            isBoatRentalEligible = true,
            owner = OwnerDto(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                email = "test@test.com",
                address = "1234 Test St",
                phoneNumber = "123-456-7890"
            )
        )

        val petInput = PetInput(
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
            ownerId = 1
        )

        val pet = Pet(
            id = 1,
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
            isBoatRentalEligible = true,
            owner = getOwner()
        )

        // When
        `when`(petRepository.save(any())).thenReturn(pet)
        `when`(ownerRepository.findById(1)).thenReturn(Optional.of(getOwner()))
        val actual = petService.addPet(petInput)

        // Then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("providePetInputs")
    fun `addPet should return a not boatRentalEligible PetDto`(petInput: PetInput, expected: PetDto) {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val ownerRepository = Mockito.mock(OwnerRepository::class.java)
        val petService = PetService(petRepository, ownerRepository)

        val pet = Pet(
            id = 1,
            name = petInput.name,
            weight = petInput.weight,
            breed = petInput.breed,
            isVaccinated = petInput.isVaccinated,
            trainingLevel = petInput.trainingLevel,
            isBoatRentalEligible = expected.isBoatRentalEligible,
            owner = getOwner()
        )

        // When
        `when`(petRepository.save(any())).thenReturn(pet)
        `when`(ownerRepository.findById(any())).thenReturn(Optional.of(getOwner()))
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
                    ownerId = 1
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 20.0,
                    breed = "Poodle",
                    isVaccinated = true,
                    trainingLevel = 5,
                    isBoatRentalEligible = false,
                    owner = getOwnerDto()
                )
            ),
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 27.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 5,
                    ownerId = 1
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 27.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 5,
                    isBoatRentalEligible = false,
                    getOwnerDto()
                )
            ),
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 2,
                    ownerId = 1
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = true,
                    trainingLevel = 2,
                    isBoatRentalEligible = false,
                    getOwnerDto()
                )
            ),
            Arguments.of(
                PetInput(
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = false,
                    trainingLevel = 5,
                    ownerId = 1
                ),
                PetDto(
                    id = 1,
                    name = "Max",
                    weight = 20.0,
                    breed = "Husky",
                    isVaccinated = false,
                    trainingLevel = 5,
                    isBoatRentalEligible = false,
                    getOwnerDto()
                )
            ),

            // Add more test cases here
        )

        @JvmStatic
        fun getOwner() = Owner(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            email = "test@test.com",
            address = "1234 Test St",
            phoneNumber = "123-456-7890"
        )

        fun getOwnerDto() = OwnerDto(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            email = "test@test.com",
            address = "1234 Test St",
            phoneNumber = "123-456-7890"
        )
    }

    @Test
    fun `should throw exception when owner is not found`() {
        // Given
        val petRepository = Mockito.mock(PetRepository::class.java)
        val ownerRepository = Mockito.mock(OwnerRepository::class.java)
        val petService = PetService(petRepository, ownerRepository)

        val petInput = PetInput(
            name = "Max",
            weight = 20.0,
            breed = "Husky",
            isVaccinated = true,
            trainingLevel = 5,
            ownerId = 1
        )

        // When
        `when`(ownerRepository.findById(1)).thenReturn(Optional.empty())

        // Then
        assertThrows<ResourceNotFoundException> {
            petService.addPet(petInput)
        }
    }

}