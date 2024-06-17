package com.droidcon.graphqlmaster.data.dto

data class StudentRequestDTO(
    val name: String,
    val collegeId: Int,
    val dob: String,
    val gender: String,
    val profileUrl: String
)