# kvalidation

kvalidation 是基于 Kotlin 特性实现的验证框架。

# 功能特点：

* DSL 风格
* 支持对象的验证
* 内含多个验证规则，也支持自定义验证规则
* 支持对象中属性的验证
* 支持 RxJava

详见：https://www.jianshu.com/p/23f34952f6a0

# 下载：

```groovy
implementation 'com.safframework.kvalidation:kvalidation-core:1.0.2'
```

# 使用：

### 使用 Validator

定义一个 defineValidator() 的函数：

```kotlin
fun <T> defineValidator(block: Validator<T>.() -> Unit): Validator<T> {
    val v = Validator<T>()
    block.invoke(v)
    return v
}
```

因此，定义一个 Validator 很简单，可以在 block 中添加 ValidateRule。

```kotlin
    val validator = defineValidator<String>{

        this addRule EmailRule()
    }

    val email = "fengzhizi715@126.com"

    val result = validator.validate(email,onError = { println(it)})
    
    println(result)
```

### Validator 中添加多个校验规则

由于 Validator 是一个 LinkedHashSet，因此可以在 block 中添加多个 ValidateRule。

例如下面的密码校验，使用了两个 ValidateRule：

```kotlin
    val validator = defineValidator<String>{

        this addRule MinLengthRule(6)                                       // 密码长度不能小于6位

        this addRule PatternRule("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$") // 密码必须数字和字母的组合
    }

    val password = "123456a"

    val result = validator.validate(password,onError = { println(it)})

    println(result)
```

### 支持 RxJava 的使用

由于定义了一个 RxValidator

```kotlin
class RxValidator<T>(private val data: T) : Validator<T>() {

    fun toObservable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Observable.create<T> {
               it.onNext(data)
            }
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message)})
            }

    fun toFlowable(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Flowable.create<T> ({
                it.onNext(data)
             }, BackpressureStrategy.BUFFER)
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message) })
            }

    fun toSingle(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Single.create<T> {
                it.onSuccess(data)
            }
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message) })
            }

    fun toMaybe(success: (() -> Unit)? = null,error: ((String) -> Unit)? = null) =
        Maybe.create<T> {
                it.onSuccess(data)
            }
            .map {
                validate(it, onSuccess = { success?.invoke() }, onError = { message -> error?.invoke(message) })
            }
}
```

并且定义了一个 defineRxValidator() 和扩展函数 rxValidator()

```kotlin
fun <T> defineRxValidator(data: T, block: RxValidator<T>.() -> Unit): RxValidator<T> {
    val v = RxValidator<T>(data)
    block.invoke(v)
    return v
}

fun <T> T.rxValidator(block: RxValidator<T>.() -> Unit): RxValidator<T> {
    val v = RxValidator<T>(this)
    block.invoke(v)
    return v
}
```

因此 RxJava 的结合使用变得很简单，下面分别使用两种方式展示了如何结合 RxJava 的使用：

```kotlin
    val email = "fengzhizi715@126.com"

    defineRxValidator(email){ this addRule EmailRule() }
        .toObservable( error = { println(it)})
        .subscribe{ println(it) }

    val invalidEmail = "fengzhizi715@126"

    invalidEmail.rxValidator { this addRule EmailRule() }
        .toObservable( error = { println(it)})
        .subscribe{ println(it) }
```

### 支持对象中属性的校验

参考上面的代码，在 [kvalidation](https://github.com/fengzhizi715/kvalidation) 中也事先定义了一个 definePropertyValidator()

```kotlin
fun <T> definePropertyValidator(block: PropertyValidator<T>.() -> Unit): PropertyValidator<T> {
    val v = PropertyValidator<T>()
    block.invoke(v)
    return v
}
```

因此，在定义一个 PropertyValidator 时，也可以在 block 中添加多个 mustBe()、field()、fields() 方法。

在 field()、fields() 中，还可以添加多个 mustBe() 方法

```kotlin
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
        mustBe("verify email") {

            email.validate{

                this addRule EmailRule()
            }
        }.errorMessage { "invalid email address" }
    }
}

fun main() {

    val user = User()

    val result = propertyValidator.validateAll(user)
    println(result)

    println(propertyValidator.validate(user))
}
```

在 email 字段中，mustBe() 里使用了

```kotlin
            email.validate{

                this addRule EmailRule()
            }
```

它是一个扩展函数：

```kotlin
fun <T> T.validate(block: Validator<T>.() -> Unit): Boolean {
    val v = Validator<T>()
    block.invoke(v)
    return v.validate(this)
}
```

它实际上是调用了类的验证，并添加了 EmailRule。

# TODO list：
* 支持 Android

# 联系方式:

Wechat：fengzhizi715

> Java与Android技术栈：每周更新推送原创技术文章，欢迎扫描下方的公众号二维码并关注，期待与您的共同成长和进步。

![](https://github.com/fengzhizi715/NetDiscovery/blob/master/images/gzh.jpeg)


License
-------

    Copyright (C) 2018 - present, Tony Shen.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
