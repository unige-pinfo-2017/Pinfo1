describe("basic user test", function() {

it("shouldn't go to user list", function(){

        browser.get('http://localhost:3000/login');
        browser.driver.sleep(1000).then(function(){
            var inputUsername = element(by.css('input.user'))
            inputUsername.sendKeys("Antoine");
            var inputPassword = element(by.css('input.password'))
            inputPassword.sendKeys("Benquet");
            browser.driver.sleep(1000).then(function(){
                element.all(by.buttonText("Login")).click();
                browser.sleep(2000);
                browser.get('http://localhost:3000/table/user');
                // means that it's impossible for a basic user to go to localhost:3000/table/user            
                
					
                });
            }).then(function(){
            	console.log(browser.getCurrentUrl());
            expect(browser.getCurrentUrl()).toEqual('http://localhost:3000/overview');
            });
        
        });
    });