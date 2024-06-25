package com.petscreening.boatrentalservice.repositories.specifications

import com.petscreening.boatrentalservice.models.entities.Pet
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class PetSpecification {

    companion object {

        fun isUnderMaxWeight(isUnderMaxWeight: Boolean?): Specification<Pet> {
            return Specification { root, query, criteriaBuilder ->
                when (isUnderMaxWeight) {
                    true -> criteriaBuilder.lessThanOrEqualTo(root.get("weight"), 25.0)
                    false -> criteriaBuilder.greaterThan(root.get("weight"), 25.0)
                    null -> criteriaBuilder.conjunction()  // No criteria applied if isUnderMaxWeight is null
                }
            }
        }

        fun isVaccinated(isVaccinated: Boolean?): Specification<Pet> {
            return Specification { root: Root<Pet>, query, criteriaBuilder ->
                isVaccinated?.let {
                    criteriaBuilder.equal(root.get<Boolean>("isVaccinated"), isVaccinated)
                } ?: criteriaBuilder.conjunction()
            }
        }

        fun isNotPoodle(isNotPoodle: Boolean?): Specification<Pet> {
            return Specification { root: Root<Pet>, query, criteriaBuilder ->
                when (isNotPoodle) {
                    true -> criteriaBuilder.notEqual(root.get<String>("breed"), "Poodle")
                    false -> criteriaBuilder.equal(root.get<String>("breed"), "Poodle")
                    null -> criteriaBuilder.conjunction()  // No criteria applied if isUnderMaxWeight is null
                }
            }
        }

        fun isAboveMinTrainingLevel(isAboveMinTrainingLevel: Boolean?): Specification<Pet> {
            return Specification { root, query, criteriaBuilder ->
                when (isAboveMinTrainingLevel) {
                    true -> criteriaBuilder.greaterThanOrEqualTo(root.get("trainingLevel"), 3)
                    false -> criteriaBuilder.lessThan(root.get("trainingLevel"), 3)
                    null -> criteriaBuilder.conjunction()  // No criteria applied if isUnderMaxWeight is null
                }
            }
        }

        fun isBoatRentalEligible(isBoatRentalEligible: Boolean?): Specification<Pet> {
            return Specification { root: Root<Pet>, query, criteriaBuilder ->
                isBoatRentalEligible?.let {
                    criteriaBuilder.equal(root.get<Boolean>("isBoatRentalEligible"), isBoatRentalEligible)
                } ?: criteriaBuilder.conjunction()
            }
        }


    }
}