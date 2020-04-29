testCase("Login To Stack Overflow") {

    click("Click Login Button") {
        element = linkText("Log in")
    }

    enterText("Input email", value = "ashok@test.com") {
        element = id("email")
    }

    enterText("Input password", value = "test Password") {
        element = id("password")
    }

    click("Click Login Button") {
        element = id("submit-button")
    }

}