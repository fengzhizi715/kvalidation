import com.safframework.kvalidation.defineValidator
import com.safframework.kvalidation.rule.EmailRule
import com.safframework.kvalidation.validate

/**
 * Created by tony on 2019-07-04.
 */
fun main() {

    val validator = defineValidator<String>{

        this addRule EmailRule()
    }

    val email = "fengzhizi715@126.com"

    val result1 = validator.validate(email,onError = { println(it)})

    println(result1)

    val invalidEmail = "fengzhizi715@126"

    val result2 = invalidEmail.validate{

        this addRule EmailRule()
    }

    println(result2)
}