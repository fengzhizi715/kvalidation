package com.safframework.kvalidation

/**
 * Created by tony on 2019-07-03.
 */
interface ValidateRule<in T> {

    fun validate(data: T): Boolean

    fun errorMessage(): String
}