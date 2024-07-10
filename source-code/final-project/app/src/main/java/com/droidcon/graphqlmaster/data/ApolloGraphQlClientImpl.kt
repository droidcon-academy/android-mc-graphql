package com.droidcon.graphqlmaster.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.droidcon.CreateCollegeMutation
import com.droidcon.CreateStudentMutation
import com.droidcon.GetCollegesQuery
import com.droidcon.GetPaginatedCollegesQuery
import com.droidcon.GetStudentByCollegeIdQuery
import com.droidcon.StudentsQuery
import com.droidcon.SubscribeStudentSubscription
import com.droidcon.graphqlmaster.data.dto.CollegeRequestDTO
import com.droidcon.graphqlmaster.data.dto.StudentRequestDTO
import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ApolloGraphQlClientImpl (
    private val apolloClient: ApolloClient,
    private val apolloClientWS: ApolloClient
): IGraphQLClient {
    override suspend fun getColleges(): List<CollegeEntity> {
        return try {
             apolloClient
                .query(GetCollegesQuery())
                .execute()
                .data
                ?.colleges
                ?.map { it.toCollegeEntity() }
                ?: emptyList()
        } catch (e: ApolloHttpException) {
            emptyList()
        }
    }

    override suspend fun getPaginationColleges(limit: Int, skip: Int): PaginationCollegeEntity? {
        return try {  apolloClient
            .query(GetPaginatedCollegesQuery(limit, skip))
            .execute()
            .data
            ?.paginationColleges?.toPaginationCollegeEntity()
    } catch (e: ApolloHttpException) {
       null
    }
    }

    override suspend fun getStudents(): List<StudentEntity> {
        return try {  apolloClient
            .query(StudentsQuery())
            .execute()
            .data
            ?.students
            ?.map { it.toStudentEntity() }
            ?: emptyList()
    } catch (e: ApolloHttpException) {
    emptyList()
}
    }

    override suspend fun getStudentByCollegeId(collegeId: Int): List<StudentEntity> {
        return try {  apolloClient
            .query(GetStudentByCollegeIdQuery(collegeId))
            .execute()
            .data
            ?.studentsByCollegeId
            ?.map { it.toStudentEntity() }
            ?: emptyList()
    } catch (e: ApolloHttpException) {
    emptyList()
}
    }

    override suspend fun addStudent(studentRequestDTO: StudentRequestDTO): StudentEntity? {
        val createStudentMutation = CreateStudentMutation(
            collegeId = studentRequestDTO.collegeId,
            name = studentRequestDTO.name,
            dob = studentRequestDTO.dob,
            gender = studentRequestDTO.gender,
            profileUrl = "")
        return try {   apolloClient
            .mutation(createStudentMutation)
            .execute()
            .data
            ?.createStudent?.toCreateStudentEntity()!!
    } catch (e: ApolloHttpException) {
    null
}
    }

    override suspend fun addCollege(collegeRequestDTO: CollegeRequestDTO): CollegeEntity? {
        val createCollegeMutation = CreateCollegeMutation(
            name = collegeRequestDTO.name,
            location = collegeRequestDTO.location,
            establishedYear = collegeRequestDTO.establishedYear
        )
        return try {  apolloClient
            .mutation(createCollegeMutation)
            .execute()
            .data
            ?.createCollege?.toCreateCollegeEntity()!!
    } catch (e: ApolloHttpException) {
   null
}
    }

    override fun subscribeToStudentAdded(collegeId: Int): Flow<StudentEntity> = flow {
        val subscription = SubscribeStudentSubscription(collegeId)
         try { apolloClientWS.subscription(subscription).toFlow().catch {
            print(it.message)
        }
            .collect { response: ApolloResponse<SubscribeStudentSubscription.Data> ->
                print(response)
                response.data?.subscribeStudent?.let {
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
    } catch (e: ApolloHttpException) {
    // TODO
}
    }
}