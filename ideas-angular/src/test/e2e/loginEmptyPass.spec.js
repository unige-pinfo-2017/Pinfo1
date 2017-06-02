describe("Login Test with empty password", function() {
	it("should stay on login cause password is empty", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("ideas");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("");
            
            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();
                expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/login');
            });
        });
    });
});
