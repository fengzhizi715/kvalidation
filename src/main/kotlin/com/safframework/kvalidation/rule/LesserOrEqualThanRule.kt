package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule

/**
 * Created by tony on 2019-07-04.
 */
class LesserOrEqualThanRule(val target: Number) : ValidateRule<Number> {

    override fun errorMessage() = "expected value to be less than: $target"

    override fun validate(data: Number) =  data.toDouble() <= target.toDouble()
}