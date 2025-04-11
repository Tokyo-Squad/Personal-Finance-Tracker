package test.common

import utils.withGreenColor
import utils.withRedColor


fun <T> check(name: String, result: T, correctResult: T) {
    if (result == correctResult) {
        println("Success: $name".withGreenColor())
    } else {
        println("Fail: $name".withRedColor())
    }
}