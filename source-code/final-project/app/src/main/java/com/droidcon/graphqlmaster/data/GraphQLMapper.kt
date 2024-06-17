package com.droidcon.graphqlmaster.data

import com.droidcon.CollegesQuery
import com.droidcon.CreateCollegeMutation
import com.droidcon.CreateStudentMutation
import com.droidcon.PaginationCollegesQuery
import com.droidcon.StudentsQuery
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity

fun CollegesQuery.College.toCollegeEntity(): CollegeEntity {
    return CollegeEntity(
       id = id,
        name = name,
        location = location,
        establishedYear = establishedYear
    )
}

fun PaginationCollegesQuery.PaginationColleges.toPaginationCollegeEntity(): PaginationCollegeEntity {
    return PaginationCollegeEntity(
        total = total,
        skip= size,
        nextPage = nextPage,
        limit = limit,
        collegeEntity = colleges.map {it.toCollegeEntity() }
    )
}

fun PaginationCollegesQuery.College.toCollegeEntity(): CollegeEntity {
    return CollegeEntity(
        id = id,
        name = name,
        location = location,
        establishedYear = establishedYear
    )
}

fun StudentsQuery.Student.toCollegeEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        name = name,
        dob = dob,
        collegeId = collegeId,
        gender = gender,
        profileUrl = profileUrl
    )
}

fun CreateStudentMutation.CreateStudent.toCreateStudentEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        name = name,
        dob = dob,
        collegeId = collegeId,
        gender = gender,
        profileUrl = profileUrl
    )
}

fun CreateCollegeMutation.CreateCollege.toCreateCollegeEntity(): CollegeEntity {
    return CollegeEntity(
        id = id,
        name = name,
        location = location,
        establishedYear = establishedYear
    )
}
