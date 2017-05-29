describe("go to device page", function() {
	it("should login and redirect to device list page", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("ideas");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("ideaspw");
            
            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();

                    element.all(by.buttonText("View device list")).click();
                    expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/table/device');                	
            });
        });
    });
});