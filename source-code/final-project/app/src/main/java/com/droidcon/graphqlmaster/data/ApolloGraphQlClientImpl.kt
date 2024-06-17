package com.droidcon.graphqlmaster.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.droidcon.CollegesQuery
import com.droidcon.CreateCollegeMutation
import com.droidcon.CreateStudentMutation
import com.droidcon.PaginationCollegesQuery
import com.droidcon.StudentAddedSubscription
import com.droidcon.StudentsQuery
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.data.dto.StudentRequestDTO
import com.droidcon.graphqlmaster.di.Apollo
import com.droidcon.graphqlmaster.di.ApolloWS
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApolloGraphQlClientImpl (
    private val apolloClient: ApolloClient,
    private val apolloClientWS: ApolloClient
): IGraphQLClient {
    override suspend fun getColleges(): List<CollegeEntity> {
        return apolloClient
            .query(CollegesQuery())
            .execute()
            .data
            ?.colleges
            ?.map { it.toCollegeEntity() }
            ?: emptyList()
    }

    override suspend fun getPaginationColleges(limit: Int, skip: Int): PaginationCollegeEntity? {
        return apolloClient
            .query(PaginationCollegesQuery(limit, skip))
            .execute()
            .data
            ?.paginationColleges?.toPaginationCollegeEntity()
    }

    override suspend fun getStudents(collegeId: Int): List<StudentEntity> {
        return apolloClient
            .query(StudentsQuery())
            .execute()
            .data
            ?.students
            ?.map { it.toCollegeEntity() }
            ?: emptyList()
    }

    override suspend fun addStudent(studentRequestDTO: StudentRequestDTO): StudentEntity {
        val createStudentMutation = CreateStudentMutation(
            collegeId = studentRequestDTO.collegeId,
            name = studentRequestDTO.name,
            dob = studentRequestDTO.dob,
            gender = studentRequestDTO.gender,
            profileUrl = "")
        return apolloClient
            .mutation(createStudentMutation)
            .execute()
            .data
            ?.createStudent?.toCreateStudentEntity()!!
    }

    override suspend fun addCollege(collegeRequestDTO: CollegeRequestDTO): CollegeEntity {
        val createCollegeMutation = CreateCollegeMutation(
            name = collegeRequestDTO.name,
            location = collegeRequestDTO.location,
            establishedYear = collegeRequestDTO.establishedYear
        )
        return apolloClient
            .mutation(createCollegeMutation)
            .execute()
            .data
            ?.createCollege?.toCreateCollegeEntity()!!
    }

    override fun subscribeToStudentAdded(collegeId: Int): Flow<StudentEntity> = flow {
        val subscription = StudentAddedSubscription(collegeId)
        apolloClientWS.subscription(subscription).toFlow()
            .collect { response: ApolloResponse<StudentAddedSubscription.Data> ->
                response.data?.studentAdded?.let {
                    println("+++++$it")
                    emit(StudentEntity(
                        id = it.id,
                        name = it.name,
                        dob = it.dob,
                        collegeId = it.collegeId,
                        profileUrl = it.profileUrl,
                        gender = it.gender
                    ))
                }
            }
    }

}