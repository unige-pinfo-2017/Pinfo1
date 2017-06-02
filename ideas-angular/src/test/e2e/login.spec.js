describe("Login Test", function() {

	beforeEach(function() {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
    });

	it("should connect using mock id pass and redirect to overview", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("ideas");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("ideaspw");

            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();
                    var url = browser.getCurrentUrl();
                expect(url).toEqual('http://localhost:3000/login');

            });
        });
    });
});
