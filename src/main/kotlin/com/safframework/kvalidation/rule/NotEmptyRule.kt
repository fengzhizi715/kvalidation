package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule

/**
 * Created by tony on 2019-07-03.
 */
class NotEmptyRule : ValidateRule<String> {

    override fun errorMessage() = "Text must not be null"

    override fun validate(data: String) = data.isNotEmpty()

}