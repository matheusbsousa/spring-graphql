package com.petscreening.boatrentalservice.services

import com.petscreening.boatrentalservice.models.dto.OwnerDto
import com.petscreening.boatrentalservice.models.entities.Owner
import com.petscreening.boatrentalservice.models.inputs.OwnerInput
import com.petscreening.boatrentalservice.repositories.OwnerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`

class OwnerServiceTest {

    @Test
    fun `getOwners() should return a list of owners`() {
        // Given
        val ownerRepository = Mockito.mock(OwnerRepository::class.java)
        val ownerService = OwnerService(ownerRepository)

        val expected = listOf(
            OwnerDto(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                phoneNumber = "1234567890",
                address = "1234 Elm St",
                email = "test@test.com"
            ),
            OwnerDto(
                id = 2,
                firstName = "Jane",
                lastName = "Doe",
                phoneNumber = "1234567890",
                address = "1234 Elm St",
                email = "test@test.com"
            )
        )

        val owners = listOf(
            Owner(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                phoneNumber = "1234567890",
                address = "1234 Elm St",
                email = "test@test.com"
            ),
            Owner(
                id = 2,
                firstName = "Jane",
                lastName = "Doe",
                phoneNumber = "1234567890",
                address = "1234 Elm St",
                email = "test@test.com"
            )
        )

        // When
        `when`(ownerRepository.findAll()).thenReturn(owners)
        val actual = ownerService.getOwners()

        // Then
        assertEquals(expected, actual)
    }

    @Test
    fun `addOwner() should return a new owner`() {
        // Given
        val ownerRepository = Mockito.mock(OwnerRepository::class.java)
        val ownerService = OwnerService(ownerRepository)

        val expected = OwnerDto(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "1234567890",
            address = "1234 Elm St",
            email = "test@test.com"
        )

        val ownerInput = OwnerInput(
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "1234567890",
            address = "1234 Elm St",
            email = "test@test.com"
        )

        val owner = Owner(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "1234567890",
            address = "1234 Elm St",
            email = "test@test.com"
        )

        // When
        `when`(ownerRepository.save(any())).thenReturn(owner)
        val actual = ownerService.addOwner(ownerInput)

        // Then
        assertEquals(expected, actual)
    }
}