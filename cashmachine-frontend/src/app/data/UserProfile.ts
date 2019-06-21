export class UserProfile {
  public email: string;
  public name: string;
  public pictureUrl: string;
  public roles: string[];


  constructor(email: string, name: string, picture: string, roles: string[]) {
    this.email = email;
    this.name = name;
    this.pictureUrl = picture;
    this.roles = roles;
  }
}
