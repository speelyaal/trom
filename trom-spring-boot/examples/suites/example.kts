
testSuite("Search-Suite") {
    browsers(chrome, firefox, edge,safari)
    url("https://github.com/search")

    testCases {
        +"search"
    }
}