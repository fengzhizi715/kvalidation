package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule
import java.util.regex.Pattern

/**
 * Created by tony on 2019-07-03.
 */
class EmailRule : ValidateRule<String> {

    override fun errorMessage() = "email must not be null"

    override fun validate(data: String) = emailPattern.matcher(data).matches()

    companion object {
        val emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
    }

}