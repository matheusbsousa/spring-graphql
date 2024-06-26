package com.petscreening.boatrentalservice.controllers

import com.petscreening.boatrentalservice.models.inputs.OwnerInput
import com.petscreening.boatrentalservice.services.OwnerService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class OwnerControllerTest {

    @Test
    fun `getOwners() should be called once`() {
        // Given
        val ownerService = Mockito.mock(OwnerService::class.java)
        val ownerController = OwnerController(ownerService)

        // When
        ownerController.getOwners()

        // Then
        verify(ownerService, times(1)).getOwners()
    }

    @Test
    fun `addPet() should be called once`() {
        // Given
        val ownerService = Mockito.mock(OwnerService::class.java)
        val ownerController = OwnerController(ownerService)

        val ownerInput = OwnerInput(
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "1234567890",
            address = "1234 Elm St",
            email = "test@test.com"
        )

        // When
        ownerController.addOwner(ownerInput)

        // Then
        verify(ownerService, times(1)).addOwner(ownerInput)
    }
}