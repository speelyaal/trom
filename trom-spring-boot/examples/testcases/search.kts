
testCase("Search") {


        enterText(value = "speelyaal") {
            element = xpath("//input[@name='q']")
        }.pressEnter().waitForElement{
            element= linkText("Users")
        }

        click("click Users"){
            element = linkText("Users")
        }.waitForElement{
            element= linkText("speelyaal")
        }

        click("Click SpeelYaal Organization"){
            element= linkText("speelyaal")
        }.waitForElement{
            element= linkText("trom")
        }

        click ("Click Trom repository Link"){
            element= linkText("trom")
        }.waitForElement{
            element= linkText("trom-spring-boot2")
        }

        click("Trom Spring Boot Appliation"){
            element= linkText("trom-spring-boot2")
        }




}



