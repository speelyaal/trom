import com.speelyaal.trom.dsl.*

testSuite("Search-Suite") {
    browsers(firefox)
    url("https://stackoverflow.com/")

    testCases {
        +"search"
    }
}