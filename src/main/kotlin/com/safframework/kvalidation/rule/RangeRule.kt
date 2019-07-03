package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule

/**
 * Created by tony on 2019-07-04.
 */
class RangeRule(private val min: Number, private val max: Number) : ValidateRule<Number> {

    override fun errorMessage() = "expected to be between $min and $max"

    override fun validate(data: Number): Boolean {
        val resultDouble = data.toDouble()
        return resultDouble >= min.toDouble() && resultDouble <= max.toDouble()
    }

}