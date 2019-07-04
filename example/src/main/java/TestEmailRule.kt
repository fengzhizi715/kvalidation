import com.safframework.kvalidation.defineValidator
import com.safframework.kvalidation.rule.EmailRule

/**
 * Created by tony on 2019-07-04.
 */
fun main() {

    val validator = defineValidator<String>{

        this addRule EmailRule()
    }

    val email = "fengzhizi715@126.com"

    val result = validator.validate(email,onError = { println(it)})

    println(result)
}