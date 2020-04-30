
testSuite("Search-Suite") {
    browsers(firefox)
    url("https://github.com/search")

    testCases {
        +"search"
    }
}