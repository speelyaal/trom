
testSuite("Invalid Login to Github Test") {
    browsers(chrome, firefox, edge,safari)
    url("https://github.com/")

    testCases {
        +"Login To Github"
    }
}