export class UserProfile {
  public email: string;
  public name: string;
  public picture: string;
  public token: string;


  constructor(email: string, name: string, picture: string, token: string) {
    this.email = email;
    this.name = name;
    this.picture = picture;
    this.token = token;
  }
}
