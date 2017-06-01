describe("Display editing menu from user", function() {

	it("should display the editing menu from user page", function(){
        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("Luca");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("Joss");
            
            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();

                    element.all(by.buttonText("View user list")).click();
                    expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/table/user');    
						
            });
        });
    });
});
