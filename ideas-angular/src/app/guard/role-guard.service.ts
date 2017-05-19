import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute } from '@angular/router';
import { Subscription} from "rxjs";



@Injectable()
export class RoleGuard implements CanActivate {

    private sub: Subscription;


    constructor(
		private router: Router,
		private activatedroute: ActivatedRoute) { }

    canActivate(route: ActivatedRouteSnapshot) {
      let userId: string = route.params['userId'];
      let flag:boolean=this.check(userId);
      if(!flag){
        this.router.navigate(['/overview']);
      }
      return flag;
  }

	public check(userId: string): boolean{
		let subordinates: any[] = JSON.parse(sessionStorage.getItem('subordinates'));
		for(let i =0; i < (subordinates.length); i++ ){
			if ((JSON.parse(sessionStorage.getItem('subordinates'))[i].id)==userId){
	    		return true;
			}
		}
		return false;
	}

}
