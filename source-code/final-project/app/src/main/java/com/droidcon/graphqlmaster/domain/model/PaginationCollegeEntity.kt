package com.droidcon.graphqlmaster.domain.model

data class PaginationCollegeEntity(
    val total:Int,
    val limit:Int,
    val skip:Int,
    val nextPage:Int?,
    val collegeEntity: List<CollegeEntity>
)