import { useState, useEffect } from 'react';
import "../css/rejestracja.css"

const Rejestracja = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [username, setUsername] = useState('');
    const [success, setSuccess] = useState()
    const [status, setStatus] = useState('')

    let token = null
    if(localStorage.getItem('token')){
        token = localStorage.getItem('token')
    }

    const stworzUzytkownika = async () => {


        if(password === password2){
            const data = await fetch ('http://25.41.160.138:8080/rejestracja/nowyuzytkownik', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-type' : 'application/json'
                },
                body: JSON.stringify({
                    email: email,
                    password: password,
                    status:null
                })
            })
            const resp = await data.json();
            setStatus(resp.status)
            console.log(resp.status)
        }

        if(password !== password2){
            setSuccess("Hasła się nie zgadzają.")
        }
    }

    return (
        <div className="mainRejestracja">
            <div className="subMainRejestracja">
                <h2>Wypełnij formularz aby utworzyć konto.</h2>
                <h4>{status}</h4>
                <input type="text" autoComplete="off"  placeholder="Email" value={email || ""} onChange={e => setEmail(e.target.value)}/>
                {/* <input type="text" autoComplete="off" placeholder="Nazwa użytkownika" value={username || ""} onChange={e => setUsername(e.target.value)}/> */}
                <input type="password" name="password" autoComplete="off" placeholder="Hasło" value={password || ""} onChange={e => setPassword(e.target.value)}/>
                <input type="password" name="password" autoComplete="off" placeholder="Potwierdź hasło" value={password2 || ""} onChange={e => setPassword2(e.target.value)}/>
                <button onClick={stworzUzytkownika} type ="registerme">Stwórz konto</button>
                <h3>{success}</h3>
            </div>
        </div>
    );
}
 
export default Rejestracja;
