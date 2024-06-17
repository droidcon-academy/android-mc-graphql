package com.droidcon.graphqlmaster.domain.model

data class StudentEntity(
    val name: String,
    val id: Int,
    val collegeId: Int,
    val dob: String,
    val gender: String,
    val profileUrl: String
)