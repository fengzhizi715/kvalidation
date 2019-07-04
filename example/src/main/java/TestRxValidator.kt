import com.safframework.kvalidation.defineRxValidator
import com.safframework.kvalidation.rule.EmailRule

/**
 * Created by tony on 2019-07-04.
 */
fun main() {

    val email = "fengzhizi715@126"

    defineRxValidator<String>(email){

        this addRule EmailRule()
      }.toObservable( error = { println(it)})
      .subscribe{
          println(it)
      }
}