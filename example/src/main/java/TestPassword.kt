import com.safframework.kvalidation.defineValidator
import com.safframework.kvalidation.rule.MinLengthRule
import com.safframework.kvalidation.rule.PatternRule
import java.util.regex.Pattern

/**
 * Created by tony on 2019-07-04.
 */
fun main() {

    val validator = defineValidator<String>{

        this addRule MinLengthRule(6)

        this addRule PatternRule("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$")
    }

    val password = "123456a"

    val result = validator.validate(password,onError = { println(it)})

    println(result)
}