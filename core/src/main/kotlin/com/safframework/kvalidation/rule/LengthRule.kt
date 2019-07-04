package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule

/**
 * Created by tony on 2019-07-04.
 */
class LengthRule<T>(private val min: Int, private val max: Int) : ValidateRule<T> {

    override fun errorMessage() = "expected text length between: $min - $max"

    override fun validate(data: T) = data.toString().length in min..max
}