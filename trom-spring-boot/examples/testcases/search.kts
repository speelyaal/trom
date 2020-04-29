import com.speelyaal.trom.dsl.*

testCase("Search") {

    step("Enter Search Keyword"){
        enterText("kotlin") {
            element = xpath("//input[@name='q']")
        }
    }.pressEnter().waitForElement{
        element= xpath("//div[2]/div/h3/a")

    }

    step("Click On First Result"){
        click {
            element = xpath("//div[2]/div/h3/a")
        }
    }

}