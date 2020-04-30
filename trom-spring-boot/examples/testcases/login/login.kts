testCase("Login To Github") {

    click("Click Sign In Link") {
        element = linkText("Sign")
    }

    enterText("Input email", value = "my@test.com") {
        element = id("login_field")
    }

    enterText("Input password", value = "test Password") {
        element = id("password")
    }

    click("Click Sign-In Button") {
        element = xpath("//input[@name='commit']")
    }.waitForElement {
        element = containsText("Incorrecteeee username or password.")
    }

}