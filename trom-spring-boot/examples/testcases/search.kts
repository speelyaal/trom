import com.speelyaal.trom.dsl.*

testCase("Search") {

    step("Enter Search Keyword"){
        enterText {
            element = xpath("//input[@name='q']")
        }
    }

}