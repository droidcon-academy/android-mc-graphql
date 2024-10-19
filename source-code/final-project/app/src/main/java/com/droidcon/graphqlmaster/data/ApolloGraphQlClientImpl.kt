package com.droidcon.graphqlmaster.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloHttpException
import com.droidcon.DeleteCollegeMutation
import com.droidcon.GetCollegesByCollegeIdQuery
import com.droidcon.GetCollegesQuery
import com.droidcon.GetFragmentStudentsByCollegeIdQuery
import com.droidcon.GetPaginatedCollegesQuery
import com.droidcon.SubscribeCollegeSubscription
import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.CollegeEntity
import com.droidcon.graphqlmaster.domain.model.CollegeRequestEntity
import com.droidcon.graphqlmaster.domain.model.PaginationCollegeEntity
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ApolloGraphQlClientImpl (
    private val apolloClient: ApolloClient,
    private val apolloClientWS: ApolloClient
): IGraphQLClient {

    override suspend fun getCollegeByCollegeId(collegeId: Int): CollegeEntity? {
        return try {
            apolloClient
                .query(GetCollegesByCollegeIdQuery(collegeId))
                .execute()
                .data
                ?.collegeById
                ?.toCollegeEntity()
        } catch (e: ApolloHttpException) {
           null
        }

    }

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

    override suspend fun getFragmentStudentByCollegeId(collegeId: Int): CollegeEntity? {
        return try {
            apolloClient
                .query(GetFragmentStudentsByCollegeIdQuery(collegeId))
                .execute()
                .data
                ?.collegeWithStudents?.toCollegeEntity()
        } catch (e: ApolloHttpException) {
            null
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

    override suspend fun addCollege(collegeRequestEntity: CollegeRequestEntity): CollegeEntity? {
        return try {  apolloClient
            .mutation(collegeRequestEntity.toCreateCollegeMutation())
            .execute()
            .data
            ?.createCollege?.toCreateCollegeEntity()!!
    } catch (e: ApolloHttpException) {
        null
    }
    }

    override suspend fun updateCollege(collegeRequestEntity: CollegeRequestEntity): CollegeEntity? {
        return try {  apolloClient
            .mutation(collegeRequestEntity.toUpdateCollegeMutation())
            .execute()
            .data
            ?.updateCollege?.toUpdateCollegeEntity()!!
        } catch (e: ApolloHttpException) {
            null
        }
    }

    override suspend fun deleteCollege(collegeId: Int): Boolean {
        return try {  apolloClient
            .mutation(DeleteCollegeMutation(collegeId))
            .execute()
            .data
            ?.deleteCollege?.let { true } ?: false
        } catch (e: ApolloHttpException) {
            false
        }
    }

    override suspend fun subscribeToCollege(collegeId: Int): Flow<StudentEntity> = flow {
        val subscription = SubscribeCollegeSubscription(collegeId)
         try { apolloClientWS.subscription(subscription)
             .toFlow().catch {
            print(it.message) }
            .collect { response: ApolloResponse<SubscribeCollegeSubscription.Data> ->
                print(response)
                response.data?.subscribeCollege?.let {
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
