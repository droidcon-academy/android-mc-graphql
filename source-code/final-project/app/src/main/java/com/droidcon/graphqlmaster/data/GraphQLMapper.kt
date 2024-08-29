package com.droidcon.graphqlmaster.data

import com.droidcon.CreateCollegeMutation
import com.droidcon.CreateStudentMutation
import com.droidcon.GetCollegesByCollegeIdQuery
import com.droidcon.GetCollegesQuery
import com.droidcon.GetFragmentStudentsByCollegeIdQuery
import com.droidcon.GetPaginatedCollegesQuery
import com.droidcon.GetStudentByCollegeIdQuery
import com.droidcon.StudentsQuery
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.withContext

fun GetCollegesQuery.College.toCollegeEntity(): CollegeEntity {
    return CollegeEntity(
       id = id,
        name = name,
        location = location,
        establishedYear = establishedYear,
        profileUrl = profileUrl
    )
}

fun GetCollegesByCollegeIdQuery.CollegeById.toCollegeEntity(): CollegeEntity {
    return CollegeEntity(
        id = id,
        name = name,
        location = location,
        establishedYear = establishedYear,
        profileUrl = profileUrl
    )
}

fun GetFragmentStudentsByCollegeIdQuery.CollegeWithStudents.toCollegeEntity(): CollegeEntity {
    return CollegeEntity(
        id = id,
        name = name,
        location = location,
        establishedYear = establishedYear,
        profileUrl = profileUrl,
        studentEntity = students.map {
           with(it.student) {
               StudentEntity(
                   id = id,
                   name = name,
                   dob = dob,
                   collegeId = collegeId,
                   gender = gender,
                   profileUrl = profileUrl
               )
           }
        }
    )
}

fun GetPaginatedCollegesQuery.PaginationColleges.toPaginationCollegeEntity(): PaginationCollegeEntity {
    return PaginationCollegeEntity(
        total = total,
        skip= size,
        nextPage = nextPage,
        limit = limit,
        collegeEntity = colleges.map {it.toCollegeEntity() }
    )
}

fun GetPaginatedCollegesQuery.College.toCollegeEntity(): CollegeEntity {
    return CollegeEntity(
        id = id,
        name = name,
        location = location,
        establishedYear = establishedYear,
        profileUrl = profileUrl
    )
}

fun StudentsQuery.Student.toStudentEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        name = name,
        dob = dob,
        collegeId = collegeId,
        gender = gender,
        profileUrl = profileUrl
    )
}

fun GetStudentByCollegeIdQuery.StudentsByCollegeId.toStudentEntity(): StudentEntity {
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
        establishedYear = establishedYear,
        profileUrl = profileUrl
    )
}
