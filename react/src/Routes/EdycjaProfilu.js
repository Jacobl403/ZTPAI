import React, { useState, useEffect } from 'react'
import '../css/edycjaprofilu.css'
import GetUserSubjects from "../Actions/GetUserSubjects"
import axios from 'axios'
import { useHistory } from 'react-router-dom'

let arr = []
const EdycjaProfilu = () => {
    const delay = ms => new Promise(res => setTimeout(res, ms));
    const [state, setState] = useState(1)
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [password2, setPassword2] = useState()
    const [amountOfSubjects, setAmountOfSubjects] = useState()
    const [errorState, setErrorState] = useState()
    const [loadedState, setLoadedState] = useState(false)
    const [userSubjects, setUserSubjects] = useState([])
    const [hiddenState, setHiddenState] = useState(true)
    const [przedmiotyAccepted, setPrzedmiotyAccepted] = useState(false)
    const [subsArray, setSubsArray] = useState(["arr"])

    let token = null
    if(localStorage.getItem('token')){
        token = localStorage.getItem('token')
    }

    let history = useHistory()

    function getListValue(){
        let sbArr = []
        arr = []
        arr.length = 0
        for(let i = 1; i <= state; i++){
            let val = document.getElementById('inputID'+i).value
            sbArr.push(val)
            arr.push(val)
        }
        setUserSubjects(sbArr)
        
        postSubjects()
        // localStorage.setItem('edycjaProfiluSubs', JSON.stringify(arr))
        // setPrzedmiotyAccepted(true)
    }

    const GetUserSubs = async () => {
        const response = await axios.get("http://localhost:8080/edycjaprofilu", {
            headers: { 'Authorization' : 'Bearer ' + token }
        })
        
        setSubsArray(response.data.subjects)
        localStorage.setItem('testStorage', JSON.stringify(response.data.subjects))
    }

    useEffect(() => {
        GetUserSubs()
    }, [userSubjects])

    const postSubjects = async () => {
        await delay(500)
        const data = await fetch ('http://localhost:8080/edycjaprofilu/zmianaprzedmiotow', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json',
                'Authorization' : 'Bearer ' + token 
            },
            body: JSON.stringify({
                email: null,
                password: null,
                amountofsubjects: state,
                subjects: arr
            })
        })
        localStorage.removeItem("state")
        
        const resp = await data.json();
        
        window.location.reload()
    }

    // useEffect(() => {
    //     if(przedmiotyAccepted){
    //         postSubjects()
    //     }
    // }, [userSubjects])


    //USEEFFECT OPERUJ??CY HIDDENSTATEM OKRE??LONYCH DIV??W
    useEffect(() => {
        if(JSON.stringify(localStorage.getItem('testStorage')) === 'null'){
            setHiddenState(false)
        }else{
            setHiddenState(true)
        }
    }, [localStorage.getItem('testStorage')])

    //POBIERANIE PRZEDMIOT??W WPISANYCH W INPUTY
    const handleClick = () => {
        
        if(loadedState === true){
            window.location.reload()
            setLoadedState(false)
        }
        const myDiv = document.getElementById("dodajPrzedmioty")

        const inf = document.createElement('div')
        inf.className = "infoDiv"
        inf.innerHTML = "Wprowad?? przedmioty!"
        myDiv.append(inf)
        
        const bt = document.createElement('button')
        bt.textContent = "Potwierd??"
        bt.className = "submitButton"
        bt.onclick = function() {getListValue()};
        for(let i = 1; i <= state; i++){
            const input = document.createElement('input')
            const label = document.createElement('label')
            const br = document.createElement('br')
            const brr = document.createElement('br')
            input.type = "text"
            input.id = "inputID"+i;
            input.className = "inputSubject"
            input.placeholder = "Wprowad?? nazw?? przedmiotu"
            label.textContent = "Przedmiot #" + i + " ";
            label.className = "labeledycja"
            myDiv.append(label, brr, input, br);
            setLoadedState(true)
        }
        myDiv.append(bt);
    }

    //POST DO SERWERA (ZMIANA EMAILA)
    const handleEmailChange = async () => {
        const data = await fetch ('http://localhost:8080/edycjaprofilu/zmianaemail', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json',
                'Authorization' : 'Bearer ' + token 
            },
            body: JSON.stringify({
                email: email,
                password: null,
                amountofsubjects: null,
                subjects: null
            })
        })
        const resp = await data.json();
       
        localStorage.removeItem('token')
        history.push('/')
    }

    //POST DO SERWERA (ZMIANA HASLA)
    const handlePasswordChange = async () => {
        if(password === password2){
            const data = await fetch ('http://localhost:8080/edycjaprofilu/zmianahasla', {
                method: 'POST',
                headers: {
                    'Content-type' : 'application/json',
                    'Authorization' : 'Bearer ' + token 
                },
                body: JSON.stringify({
                    email: null,
                    password: password,
                    amountofsubjects: null,
                    subjects: null
                })
            })

            const resp = await data.json()
            
            localStorage.removeItem('token')
            history.push('/')
        }else{
            setErrorState('Has??a nie s?? identyczne.')
        }
    }
    
    // FUNKCJA RESETUJ??CA PRZEDMIOTY W BAZIE
    const resetSubs = () => {
        setHiddenState(false)
        // localStorage.removeItem('testStorage')
        // window.location.reload()
    }

    return (
        <div className="mainEdycjaProfilu">
            <div>
            <h2>Edycja Profilu</h2>
            {/* DIV ZE ZMIANA EMAILA */}
            <div id="zmianaEmaila">
                Zmiana emaila przypisanego do konta:
                <input type="text" className="emailField" placeholder="Wprowad?? nowy email" value = {email} onChange={e => setEmail(e.target.value)}></input>
                <button onClick={handleEmailChange} className="submitEmail">Potwierd??</button>
            </div>
            {/**********************************************/}

            {/* DIV ZE ZMIANA HASLA */}
            <div id="zmianaHasla">
                Zmiana has??a przypisanego do konta:
                <input type="password" className="passwordField" placeholder="Wprowad?? nowe has??o" value = {password} onChange={e => setPassword(e.target.value)}></input>
                <input type="password" className="passwordConfirmField" placeholder="Potwierd?? nowe has??o" value = {password2} onChange={e => setPassword2(e.target.value)}></input>
                <button onClick={handlePasswordChange} className="submitEmail">Potwierd??</button>
            </div>
            {/**********************************************/}
            <h2>Moje przedmioty</h2>
            <div id="przedmioty">
            <div className="mojePrzedmioty">
                {/* WY??WIETLANIE PRZEDMIOT??W ZAPISANYCH DO BAZY */}
                {subsArray.map((e, i) => 
                    <div className="przedmiot" hidden={!hiddenState} key={i}>{e}</div>
                )}
                {/**********************************************/}
            </div>
                {/* WYBRANIE ILO??CI PRZEDMIOT??W JE??LI W BAZIE NIE MA ??ADNYCH */}
                <div id="noInput" hidden={hiddenState}>Nie wprowadzi??e?? jeszcze ??adnych przedmiot??w!</div><br></br>
                <div className="iloscPrzedmiotow" hidden={hiddenState}>
                    Wprowad?? ilo???? przedmiot??w:<br></br>
                    <input type="number" className="inputField" placeholder="Ilo???? przedmiot??w" min="1" max="15" value = {state} onChange={e => setState(e.target.value)}></input>
                    <button onClick={handleClick} className="submitButton">Potwierd??</button>
                </div>
                {/**********************************************/}
            </div>
                {/* PRZYCISK RESETOWANIA PRZEDMIOT??W */}
            <button id="resetButton" onClick={() => resetSubs()} hidden={!hiddenState}>Zresetuj przedmioty</button>
                {/**********************************************/}

            </div>
            <div className="wprowadzprzedmioty">
                {/* DIV POZWALAJ??CY NA DODAWANIE PRZEDMIOT??W DO BAZY PO WYBRANIU ICH ILO??CI */}
                <div id="dodajPrzedmioty"></div>
                {/**********************************************/}
            </div>
        </div>
    );
}
 
export default EdycjaProfilu;
