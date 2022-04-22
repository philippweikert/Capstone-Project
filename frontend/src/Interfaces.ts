export interface Credentials {
    username:string,
    password:string
}

export interface RegisterCredentials {
    username:string,
    password:string,
    repeatPassword:string
}

export interface AuthInterface {
    token:string;
    login: (username:string, password:string) => Promise<void>
    logout: () => void
    username: string
}