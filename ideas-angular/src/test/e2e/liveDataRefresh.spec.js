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
                browser.sleep(2000);
                element.all(by.id("value")).click();
                browser.sleep(2000);


                //expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/overview');
            });
        });
    });
});
