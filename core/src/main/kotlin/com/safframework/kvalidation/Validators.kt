package com.safframework.kvalidation

/**
 * Created by tony on 2019-07-04.
 */
fun <T> defineValidator(block: Validator<T>.() -> Unit): Validator<T> {
    val v = Validator<T>()
    block.invoke(v)
    return v
}