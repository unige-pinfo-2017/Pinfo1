import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute } from '@angular/router';
import { Subscription} from "rxjs";



@Injectable()
export class RoleGuard implements CanActivate {

    private sub: Subscription;


    constructor(private router: Router, private activatedroute: ActivatedRoute) { }

    canActivate(route: ActivatedRouteSnapshot) {
      let userId: string;
      this.sub = this.activatedroute.params.subscribe(params => {
            userId = params['userId'];
      });

      let flag:boolean=this.check(userId);

      if(!flag){
        this.router.navigate(['/overview']);
      }

      return flag;
  }

public check(userId: string): boolean{

  for(let i =0; i < (JSON.parse(sessionStorage.getItem('Users')).UsersA.length); i++ ){


    if ((JSON.parse(sessionStorage.getItem('Users')).UsersA[i].id)==userId){

        return true;
    }

  }


  return false;


}

}
