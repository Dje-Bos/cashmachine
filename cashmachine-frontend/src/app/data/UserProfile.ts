export class UserProfile {
  public email: string;
  public name: string;
  public picture: string;


  constructor(email: string, name: string, picture: string) {
    this.email = email;
    this.name = name;
    this.picture = picture;
  }
}
