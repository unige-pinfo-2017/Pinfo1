describe("Login Test with empty username password", function() {

	beforeEach(function() {
        jasmine.DEFAULT_TIMEOUT_INTERVAL = 1000000;
    });

	it("should stay on login cause username and password are empty", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("");

            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();
                expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/login');
            });
        });
    });
});
