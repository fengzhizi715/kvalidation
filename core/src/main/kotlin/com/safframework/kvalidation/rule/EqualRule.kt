package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule

/**
 * Created by tony on 2019-07-04.
 */
class EqualRule<T>(val other: T) : ValidateRule<T> {

    override fun errorMessage() = "expected equal to: $other"

    override fun validate(data: T): Boolean {
        return data == other
    }
}