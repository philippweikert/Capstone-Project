import {FormEvent, useState} from "react";
import {useAuth} from "../Authentification/AuthProvider";

export default function Login(){
    const [username, setUsername] = useState("");
    const [usedPassword, setUsedPassword] = useState("");
    const [error, setError] = useState("");

    const auth = useAuth();

    const loginAction = (event: FormEvent) => {
        event.preventDefault()
        setError("")
        auth.login(username, usedPassword)
            .catch(error => setError(error.message))
    }

    return(
        <div>
            <h3>Log dich ein!</h3>
            <form onSubmit={loginAction}>
                <input type="text" placeholder={'Benutzername'} value={username} onChange={ev => setUsername(ev.target.value)}/>
                <input type={'password'} placeholder={'Benutzerpasswort'} value={usedPassword} onChange={ev => setUsedPassword(ev.target.value)}/>
                <button type={'submit'}>Los geht's!</button>
            </form>
            {error && <h2>{error}</h2>}
        </div>
    )
}
