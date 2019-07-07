import com.safframework.kvalidation.definePropertyValidator
import com.safframework.kvalidation.defineValidator
import com.safframework.kvalidation.property.ValidationErrors
import com.safframework.kvalidation.rule.EmailRule
import com.safframework.kvalidation.validate

/**
 * Created by tony on 2019-07-06.
 */
data class User(val name: String = "tony",val password: String = "abcdefg", val confirmPassword: String = "abcdefg" ,val email:String = "abc#abc.com")

val propertyValidator = definePropertyValidator<User> {

    mustBe { name.isNotBlank() }

    field("password") {
        mustBe("password not blank") { password.isNotBlank() }
        mustBe("password length range") { password.length in 6..20 }
    }

    fields("password", "confirmPassword") {
        mustBe("password confirmPassword same") { password == confirmPassword }
    }

    field("email") {
        mustBe {

            email.validate{

                this addRule EmailRule()
            }
        }.errorMessage { "invalid email address" }
    }
}

fun main() {

    val user = User()

    val result: ValidationErrors = propertyValidator.validateAll(user)
    println(result)

    println(propertyValidator.isValid(user))
}

