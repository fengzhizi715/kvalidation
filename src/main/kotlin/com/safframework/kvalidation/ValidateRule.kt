package com.safframework.kvalidation

/**
 * Created by tony on 2019-07-03.
 */
interface ValidateRule<T> {

    fun validate(data: T): Boolean

    fun errorMessage(): String
}