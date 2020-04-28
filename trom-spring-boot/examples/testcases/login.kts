import com.speelyaal.trom.dsl.*

testCase("my dynamic case") {
    testStep("testing my dynamic step"){

        click {
            element = xpath("summa xpath testing")
        }

    }
}