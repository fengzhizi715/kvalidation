import com.safframework.kvalidation.defineRxValidator
import com.safframework.kvalidation.rule.EmailRule
import com.safframework.kvalidation.rxValidator

/**
 * Created by tony on 2019-07-04.
 */
fun main() {

    val email = "fengzhizi715@126.com"

    defineRxValidator(email){ this addRule EmailRule() }
        .toObservable( error = { println(it)})
        .subscribe{ println(it) }

    val invalidEmail = "fengzhizi715@126"

    invalidEmail.rxValidator { this addRule EmailRule() }
        .toObservable( error = { println(it)})
        .subscribe{ println(it) }
}