import {User} from '../../entity/user';

export interface IAuthenticationService {
  getToken();

  getUser();

  saveUser(user: User);

  loginSystem();

  logOut();

  isLoginSystem();

  getRolesToPromise();

}
