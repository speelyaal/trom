import com.speelyaal.trom.dsl.*

testSuite("Login-Suite") {

    browsers(chrome,firefox)

    testCases {
        +"login"
        +"navigateToClouds"

    }
}