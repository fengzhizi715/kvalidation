# kvalidation

kvalidation 是基于 Kotlin 特性实现的验证框架。

# 功能特点：

* 支持对象的校验
* 内含多个校验规则，也支持自定义校验规则
* 支持对象中属性的校验
* 支持 RxJava

# 使用：

定义 Validator
 
```kotlin
    val validator = defineValidator<String>{

        this addRule EmailRule()
    }

    val email = "fengzhizi715@126.com"

    val result = validator.validate(email,onError = { println(it)})
    
    println(result)
```

Validator 支持多个校验规则

```kotlin
    val validator = defineValidator<String>{

        this addRule MinLengthRule(6)                                       // 密码长度不能小于6位

        this addRule PatternRule("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$") // 密码必须数字和字母的组合
    }

    val password = "123456a"

    val result = validator.validate(password,onError = { println(it)})

    println(result)
```

支持使用 RxJava

```kotlin
    val email = "fengzhizi715@126.com"

    defineRxValidator(email){ this addRule EmailRule() }
        .toObservable( error = { println(it)})
        .subscribe{ println(it) }
```
