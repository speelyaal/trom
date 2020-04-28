import com.speelyaal.trom.dsl.*

testSuite("Login-Suite") {

    browsers(firefox)
    url("https://www.google.com")

    testCases {
        +"search"
    }
}