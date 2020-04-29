import com.speelyaal.trom.dsl.*

testSuite("Search-Suite") {
    browsers(firefox)
    url("https://github.com/search")

    testCases {
        //+"Login To Stack Overflow"
        //+"Click Users"
        +"search"
    }
}