package com.droidcon.graphqlmaster.domain.usecase

import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.model.StudentEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscribeToCollegeUseCase @Inject constructor(private val graphQLClient: IGraphQLClient) {
    suspend fun execute(collegeId: Int): Flow<StudentEntity> {
        return graphQLClient.subscribeToCollege(collegeId)
    }
}