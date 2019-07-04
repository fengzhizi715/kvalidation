package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule

/**
 * Created by tony on 2019-07-04.
 */
class MinLengthRule(val count: Int) : ValidateRule<String> {

    override fun errorMessage() = "text must be smaller than $count"

    override fun validate(data: String) =  data.length >= count
}