package com.droidcon.graphqlmaster.domain.model

import com.droidcon.CreateCollegeMutation
import com.droidcon.UpdateCollegeMutation

data class CollegeRequestEntity(
    val id: Int? = null,
    val name: String,
    val establishedYear: String,
    val location: String,
    val profileUrl: String
) {

    fun toUpdateCollegeMutation(): UpdateCollegeMutation {
        return UpdateCollegeMutation (
            collegeId = id!!,
            name = name,
            establishedYear = establishedYear,
            location = location,
            profileUrl = profileUrl
        )
    }

    fun toCreateCollegeMutation(): CreateCollegeMutation {
        return CreateCollegeMutation (
            name = name,
            establishedYear = establishedYear,
            location = location,
            profileUrl = profileUrl
        )
    }
}
