package com.droidcon.graphqlmaster.domain.model

data class CollegeEntity(
    val id : Int,
    val name: String,
    val establishedYear: String,
    val location: String,
    val profileUrl:String,
    val studentEntity: List<StudentEntity> = listOf()
)