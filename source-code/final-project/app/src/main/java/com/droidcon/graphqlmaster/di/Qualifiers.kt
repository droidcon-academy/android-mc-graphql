package com.droidcon.graphqlmaster.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Apollo

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApolloWS