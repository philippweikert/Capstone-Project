import {FormEvent, useState} from "react";
import {registerUser} from "../Landing/LoginService";
import {useNavigate} from "react-router-dom";

export default function Registerpage(){
    const [registerUsername, setRegisterUsername] = useState('');
    const [registerPassword, setRegisterPassword] = useState('');
    const [registerRepeatPassword, setRegisterRepeatPassword] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate()

    const handleRegister = (event:FormEvent) => {
        event.preventDefault()
        setError('')
        if(!(registerPassword===registerRepeatPassword)){
            setError('Passwörter stimmen nicht überein!')
        }else{
            registerUser({username:registerUsername, password:registerPassword, repeatPassword:registerRepeatPassword})
                /*.then(()=> {
                    setRegisterUsername("")
                    setRegisterPassword("")
                    setRegisterRepeatPassword("")
                })*/
                .then(() => navigate("/login"))
                .catch(er => setError(er.message))
        }
    }

    return(
    <div>
        <h3>Registriere dich hier!</h3>
        <form onSubmit={handleRegister}>
            <input type={'text'} placeholder={'Benutzername'} value={registerUsername} onChange={event => setRegisterUsername(event.target.value)}/>
            <input type={'password'} placeholder={'Benutzerpasswort'} value={registerPassword} onChange={event => setRegisterPassword(event.target.value)}/>
            <input type={'password'} placeholder={'Benutzerpasswort wiederholen'} value={registerRepeatPassword} onChange={event => setRegisterRepeatPassword(event.target.value)}/>
            <button type={'submit'}>Registrieren</button>
        </form>
        {error && <h2>{error}</h2>}
    </div>)
}