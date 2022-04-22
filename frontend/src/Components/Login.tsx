import {FormEvent, useState} from "react";
import {useAuth} from "../Authentification/AuthProvider";
import {useNavigate} from "react-router-dom";
import Button from "../CssComponents/Button";
import LrInput from "../CssComponents/LrInput";

export default function Login(){
    const [username, setUsername] = useState("");
    const [usedPassword, setUsedPassword] = useState("");
    const [error, setError] = useState("");

    const auth = useAuth();
    const navigation = useNavigate()

    const loginAction = (event: FormEvent) => {
        event.preventDefault()
        setError("")
        auth.login(username, usedPassword)
            .then(() => navigation("home"))
            .catch(error => setError(error.message))
    }

    return(
        <div>
            <h3 className="ml-2 text-slate-500">Log dich hier ein!</h3>
            <form onSubmit={loginAction}>
                <LrInput placeholder={'Benutzername'} value={username} onChange={setUsername} type='text' additionalCss="mr-4 ml-3"/>
                <LrInput placeholder={'Passwort'} value={usedPassword} onChange={setUsedPassword} type='password' additionalCss="mr-4"/>
                <div>
                <Button label='Los gehts!' onClick={() => loginAction}/>
                </div>
            </form>
            {error && <h2>{error}</h2>}
        </div>
    )
}
