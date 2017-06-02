import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute } from '@angular/router';
import { Subscription} from "rxjs";



@Injectable()
export class SubordinateGuard implements CanActivate {

    private sub: Subscription;

    constructor(
		private router: Router,
		private activatedroute: ActivatedRoute) { }

    canActivate(route: ActivatedRouteSnapshot) {
		// Limits access to user which userId belongs to its subotdinate
      let userId: string = route.params['userId'];
      let flag:boolean=this.check(userId);
      if(!flag){
        this.router.navigate(['/overview']);
      }
      return flag;
  }

	public check(userId: string): boolean{
		// Checks whether userId is in the list of subordinates of the current user
		let subordinates: any[] = JSON.parse(sessionStorage.getItem('subordinates'));
		for(let i =0; i < (subordinates.length); i++ ){
			if ((JSON.parse(sessionStorage.getItem('subordinates'))[i].id)==userId){
	    		return true;
			}
		}
		return false;
	}

}
