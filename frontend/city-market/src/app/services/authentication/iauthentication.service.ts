import {User} from '../../entities/user';

/**
 * The interface that defines the basic actions for user authentication in the application.
 */
export interface IAuthenticationService {
  /**
   * The method returns a token if the user is logged on.
   */
  getToken();

  /**
   * The method returns the User that is registered in the system.
   */
  getUser();

  /**
   *  The method establishes the user whose it is necessary to remember in the application.
   */
  saveUser(user: User);

  /**
   * The method of logging into the system.
   */
  loginSystem();

  /**
   * Method of logging out. The method sets all access values to the application as a user, and
   * erases the registration data.
   */
  logOut();

  /**
   * The method returns whether someone is logged on the system.
   */
  isLoginSystem();

  /**
   * The method returns a list of the role's roles available in the application.
   */
  getRolesToPromise();

  /**
   * Users registered in the system.
   */
  getUsers();

  /**
   * The controller method accepts a password request for the user.
   * @param {number} idUser
   */
  getUserPassword(idUser: number)
}
