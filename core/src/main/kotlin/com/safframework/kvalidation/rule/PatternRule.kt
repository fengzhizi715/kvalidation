package com.safframework.kvalidation.rule

import com.safframework.kvalidation.ValidateRule
import java.util.regex.Pattern

/**
 * Created by tony on 2019-07-03.
 */
open class PatternRule(val pattern: Pattern) : ValidateRule<String> {

    constructor(regex:String):this(Pattern.compile(regex))

    override fun errorMessage() = "Pattern $pattern not valid"

    override fun validate(data: String) = pattern.matcher(data).matches()
}