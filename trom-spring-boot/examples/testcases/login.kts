testCase("Login To Stack Overflow") {

    click("Click Login Button") {
        element = linkText("Log")
    }

    enterText("Input email", value = "ashok@test.com") {
        element = id("email")
    }

    enterText("Input password", value = "test Password") {
        element = id("password")
    }

    click("Click Login Button") {
        element = id("submit-button")
    }.waitForElement {
        element = containsText("The email or password is incorrect.")
    }

}