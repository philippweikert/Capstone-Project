import {FormEvent, useState} from "react";
import {useAuth} from "../Authentification/AuthProvider";

export default function Login(){
    const [loginUsername, setLoginUsername] = useState("");
    const [loginPassword, setLoginPassword] = useState("");
    const [error, setError] = useState("");

    const auth = useAuth();

    const loginAction = (event: FormEvent) => {
        event.preventDefault()
        setError("")
        auth.login(loginUsername, loginPassword)
            .catch(error => setError(error.message))
    }

    return(
        <div>
            <h3>Log dich ein!</h3>
            <form onSubmit={loginAction}>
                <input type="text" placeholder={'Benutzername'} value={loginUsername} onChange={ev => setLoginUsername(ev.target.value)}/>
                <input type={'password'} placeholder={'Benutzerpasswort'} value={loginPassword} onChange={ev => setLoginPassword(ev.target.value)}/>
                <button type={'submit'}>Los geht's!</button>
            </form>
            {error && <h2>{error}</h2>}
        </div>
    )
}
