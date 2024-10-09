package com.droidcon.graphqlmaster.di

import com.apollographql.apollo3.ApolloClient
import com.droidcon.graphqlmaster.data.ApolloGraphQlClientImpl
import com.droidcon.graphqlmaster.domain.IGraphQLClient
import com.droidcon.graphqlmaster.domain.usecase.CreateCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeByCollegeIdUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetFragmentStudentByCollegeIdUseCase
import com.droidcon.graphqlmaster.domain.usecase.GetPaginationCollegeUseCase
import com.droidcon.graphqlmaster.domain.usecase.UpdateCollegeUseCase
import com.droidcon.graphqlmaster.util.BASEURL
import com.droidcon.graphqlmaster.util.BASEURLWS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Apollo
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("${BASEURL}/graphql")
            .build()
    }

    @Provides
    @Singleton
    @ApolloWS
    fun provideApolloWsClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("${BASEURL}/graphql")
            .webSocketServerUrl("${BASEURLWS}/graphql")
        .build()
    }

    @Provides
    @Singleton
    fun provideGraphqlClient(@Apollo apolloClient: ApolloClient, @ApolloWS apolloClientWS: ApolloClient): IGraphQLClient {
        return ApolloGraphQlClientImpl(apolloClient, apolloClientWS)
    }

    @Provides
    @Singleton
    fun provideGetCollegeByCollegeIdUseCase(graphQLClient: IGraphQLClient): GetCollegeByCollegeIdUseCase {
        return GetCollegeByCollegeIdUseCase(graphQLClient)
    }

    @Provides
    @Singleton
    fun provideGetCollegeUseCase(graphQLClient: IGraphQLClient): GetCollegeUseCase {
        return GetCollegeUseCase(graphQLClient)
    }

    @Provides
    @Singleton
    fun provideGetFragmentStudentByCollegeIdUseCase(graphQLClient: IGraphQLClient): GetFragmentStudentByCollegeIdUseCase {
        return GetFragmentStudentByCollegeIdUseCase(graphQLClient)
    }

    @Provides
    @Singleton
    fun provideCreateCollegeUseCase(graphQLClient: IGraphQLClient): CreateCollegeUseCase {
        return CreateCollegeUseCase(graphQLClient)
    }

    @Provides
    @Singleton
    fun provideUpdateCollegeUseCase(graphQLClient: IGraphQLClient): UpdateCollegeUseCase {
        return UpdateCollegeUseCase(graphQLClient)
    }

    @Provides
    @Singleton
    fun providePaginationCollegeUseCase(graphQLClient: IGraphQLClient): GetPaginationCollegeUseCase {
        return GetPaginationCollegeUseCase(graphQLClient)
    }

}