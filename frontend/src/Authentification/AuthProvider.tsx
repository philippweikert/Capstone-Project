import {ReactNode, useContext, useEffect, useState} from "react";
import {loginUser} from "../service/FrontendService"
import AuthContext from "./AuthContext";
import {useNavigate} from "react-router-dom";

export default function AuthProvider({children}:{children:ReactNode}) {

    const [token, setToken] = useState('');
    const nav = useNavigate()

    useEffect(() => {
        if(token){
            nav("/")
        }else {
            nav("/login")
        }
    }, [token, nav])


const login = (username:string, usedPassword:string) => {
    return loginUser({username: username, password: usedPassword})
        .then(data => setToken(data.token))
}

const logout = () => {
  setToken('')
}

return <AuthContext.Provider value={{token, login, logout}}>{children}</AuthContext.Provider>

}

export const useAuth = () => useContext(AuthContext)

