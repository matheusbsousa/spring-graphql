package com.petscreening.boatrentalservice.excpetions

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
class GlobalExceptionHandler : DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return GraphqlErrorBuilder.newError()
            .errorType(ErrorType.NOT_FOUND)
            .message(ex.message)
            .path(env.executionStepInfo.path)
            .location(env.field.sourceLocation)
            .build()
    }
}