import { useEffect, useState } from 'react'
import '../css/planzajec.css'
import axios from 'axios'

const PlanZajec = () => {
    const [selectedColor, setSelectedColor] = useState()
    const [active, setActive] = useState(false)
    const [taskColor, setTaskColor] = useState()
    const [classesName, setClassesName] = useState()
    const [userSubjects, setUserSubjects] = useState(['Brak przedmiotów'])
    const [schedule, setSchedule] = useState()
    const [pobrano, setPobrano] = useState(false)
    const [zapisano, setZapisano] = useState('')
    let arr = []
    //LISTA KOLORÓW Z KTÓREJ KORZYSTAMY PRZY TWORZENIU BLOKÓW PRZEDMIOTÓW
    let colorList = ['orange', 'red', 'green', 'lightblue', '#00B3E6', 
		  '#E6B333', '#3366E6', '#999966', '#99FF99', '#B34D4D',
		  '#80B300', '#809900', '#E6B3B3', '#6680B3', '#66991A'];

    let token = null
    if(localStorage.getItem('token')){
        token = localStorage.getItem('token')
    }
    
    //ZCZYTANIE PRZEDMIOTÓW Z LISTY
    var taskList = [];
    for(var i = 0; i < userSubjects.length; i++){
        taskList[i] = document.getElementById(userSubjects[i]);
    }

    //POBRANIE PRZEDMIOTÓW Z BAZY
    const GetUserSubs = async () => {
        const response = await axios.get("http://25.41.160.138:8080/edycjaprofilu", {
            headers: { 'Authorization' : 'Bearer ' + token }
        })
        setUserSubjects(response.data.subjects)
        setPobrano(true)
    }

    //USEEFFECT DO POBRANIA PRZEDMIOTÓW I PLANU Z BAZY
    useEffect(() => {
        if(!pobrano){
            GetUserSubs()
            getUserSchedule()
        }
    }, [userSubjects])

    //POBRANIE PLANU Z BAZY 
    const getUserSchedule = async () => {
        const response = await axios.get("http://25.41.160.138:8080/planzajec", {
            headers: { 'Authorization' : 'Bearer ' + token }
        })
        if(response.data.state){

        if(response.data.state !== null){
            console.log(JSON.parse(response.data.state))
            let stateArray = []
            if(!localStorage.getItem('state')){
                stateArray = JSON.parse(response.data.state)
                localStorage.setItem('state', JSON.stringify(stateArray))
            }
        }
     }
    }

    //ZAPISANIE PLANU DO BAZY
    const saveUserSchedule = async () => {
        let stanPlanu = localStorage.getItem('state')
        console.log(stanPlanu)
        const data = await fetch('http://25.41.160.138:8080/planzajec/zapiszstan', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json', 
                'Authorization' : 'Bearer ' + token
            },
            body: JSON.stringify({
                state: stanPlanu
            })
        })
        const resp = await data.json();
        console.log(resp)
        setZapisano('Plan zajęć został zapisany!')
    }

    //RESETOWANIE PLANU W BAZIE
    const ResetUserSchedule = async () => {
        const data = await fetch('http://25.41.160.138:8080/planzajec/zapiszstan', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json', 
                'Authorization' : 'Bearer ' + token
            },
            body: JSON.stringify({
                state: null
            })
        })
        const resp = await data.json();
        console.log(resp)
    }

    //WYBRANIE PRZEDMIOTU Z LISTY
    function selectTask (e){
        resetTask()
        setTaskColor(e.target.style.backgroundColor)
        for(let i = 0; i < taskList.length; i++){
            switch(e.target.id){
                case userSubjects[i]:
                    activeTask(taskList[i], e.target.style.backgroundColor);
                    setClassesName(userSubjects[i])
                break;
            }
        }
    }

    //RESET WYBRANEGO PRZEDMIOTU W PRZYPADKU GDY KLIKNIEMY W NASTEPNEGO
    function resetTask(){ 
        const allTasks = document.querySelectorAll('.task__name');
        allTasks.forEach(e => {
            e.className = 'task__name';
        })
    }

    //AKTYWUJ TASKA PO WYBRANIU (CZARNA OBWÓDKA WOKÓŁ PRZEDMIOTU)
    function activeTask(task, color){
        if(task){
            task.classList.toggle('selected');
        if(task.classList.contains('selected')){
            setActive(true);
            setSelectedColor(color)
            return selectedColor;
        } else {
            setActive(false);
        }
        }
    }

    //WYBIERZ MIEJSCE W KTÓRYM USTAWIĆ PRZEDMIOT
    function setTask(e){
        if(e.target.classList.contains('task') && active === true){
            e.target.style.backgroundColor = selectedColor;
            e.target.innerHTML = classesName;
        }else if(e.target.classList.contains('fas') && active === true){
            e.target.parentElement.style.backgroundColor = selectedColor;
            e.target.parentElement.innerHTML = classesName;
        }
    }

    //ODTWARZANIE PLANU ZAJĘĆ PO ZMIANIE NA INNE OKNO (LOCALSTORAGE)
    useEffect(async() => {
        if(localStorage.getItem('state')){
            arr = (JSON.parse(localStorage.getItem('state')))
            if(document.getElementById('sh'))
                if(arr.boxColor.length > 0){
                    for(let i = 0; i < arr.boxColor.length; i++){
                        const whichOne = document.getElementById(arr.timeID[i])
                        whichOne.style.background = arr.boxColor[i]
                        whichOne.innerHTML = arr.name[i]
                    }
                }else{
                    alert('Ustaw plan!')
                }
        }
    }, [])

    //RESET PLANU ZAJĘĆ
    function deleteTasks(){
        ResetUserSchedule()
        const tasks = document.querySelectorAll('.task');
        localStorage.removeItem('state')
        tasks.forEach(e => {
            e.innerHTML = '';
            e.style.backgroundColor = 'white';
        })
    }

    //ZAPISYWANIE STANU PLANU DO LOCALSTORAGE NA KAŻDEJ ZMIANIE W PLANIE
    //POZWALA TO NA PÓŹNIEJSZE ZAPISANIE STANU PLANU DO BAZY DANYCH
    function test(e){
        if(!localStorage.getItem('state')){
            let stateArr = {
                name: [],
                boxColor: [],
                timeID: []
            }

            let v = JSON.stringify(stateArr)
            setSchedule(v)
            localStorage.setItem('state', v)
        }
        
        if(e.target.classList.contains('task') && active === true){
            e.target.style.backgroundColor = selectedColor;
            e.target.innerHTML = classesName;
            let stateArr = JSON.parse(localStorage.getItem('state'))
            stateArr.timeID.push(e.target.id)
            stateArr.name.push(classesName)
            stateArr.boxColor.push(selectedColor)
            let v = JSON.stringify(stateArr)
            localStorage.setItem('state', v)
            setSchedule(v)
        }
    }

    return (
        <div>
            <h2>Plan Zajęć</h2>
            <div className="container">
            <h3>Wybierz przedmiot</h3>
                <div className="task__container" onClick={(e) => selectTask(e)}>
                    {
                        userSubjects.map((e, i) => (
                            <div className="task__name" id={e} key={i} style={{ backgroundColor: colorList[i] }}>{e}</div>
                        ))
                    }
                </div>
                <h3>Dodaj przedmiot do rozpiski</h3>
                <h2>{zapisano}</h2>
                <div className="scheduleMain" onClick={(e) => test(e)}>
                <div className="schedule__container" id="sh" onClick={(e) => setTask(e)}>
                    <div className="days__container">
                        <span className="corner"></span>
                        <div className="day">Poniedziałek</div>
                        <div className="day">Wtorek</div>
                        <div className="day">Środa</div>
                        <div className="day">Czwartek</div>
                        <div className="day">Piątek</div>
                    </div>

                    <div className="part__day">
                        <span className="time">8am 10am</span>
                        <div className="task" id="mn8-10"></div>
                        <div className="task" id="tu8-10"></div>
                        <div className="task" id="we8-10"></div>
                        <div className="task" id="th8-10"></div>
                        <div className="task" id="fr8-10"></div>
                    </div>
                    <div className="part__day">
                        <span className="time">10am 12pm</span>
                        <div className="task" id="mn10-12"></div>
                        <div className="task" id="tu10-12"></div>
                        <div className="task" id="we10-12"></div>
                        <div className="task" id="th10-12"></div>
                        <div className="task" id="fr10-12"></div>
                    </div>
                    <div className="part__day">
                        <span className="time">12pm 2pm</span>
                        <div className="task" id="mn12-2"></div>
                        <div className="task" id="tu12-2"></div>
                        <div className="task" id="we12-2"></div>
                        <div className="task" id="th12-2"></div>
                        <div className="task" id="fr12-2"></div>
                    </div>
                    <div className="part__day">
                        <span className="time">2pm 4pm</span>
                        <div className="task" id="mn2-4"></div>
                        <div className="task" id="tu2-4"></div>
                        <div className="task" id="we2-4"></div>
                        <div className="task" id="th2-4"></div>
                        <div className="task" id="fr2-4"></div>
                    </div>
                    <div className="part__day">
                        <span className="time">4pm 6pm</span>
                        <div className="task" id="mn4-6"></div>
                        <div className="task" id="tu4-6"></div>
                        <div className="task" id="we4-6"></div>
                        <div className="task" id="th4-6"></div>
                        <div className="task" id="fr4-6"></div>
                    </div>
                    <div className="part__day">
                        <span className="time">6pm 8pm</span>
                        <div className="task" id="mn6-8"></div>
                        <div className="task" id="tu6-8"></div>
                        <div className="task" id="we6-8"></div>
                        <div className="task" id="th6-8"></div>
                        <div className="task" id="fr6-8"></div>
                    </div>
                    <div className="part__day">
                        <span className="time">8pm 10pm</span>
                        <div className="task" id="mn8-10pm"></div>
                        <div className="task" id="tu8-10pm"></div>
                        <div className="task" id="we8-10pm"></div>
                        <div className="task" id="th8-10pm"></div>
                        <div className="task" id="fr8-10pm"></div>
                    </div>
                </div>
                </div>
                <button className="resetSchedule" onClick={() => deleteTasks()}>Zresetuj plan</button>
                <button className="resetSchedule" onClick={saveUserSchedule}>Zapisz plan</button>
            </div>
        </div>
    );
}
 
export default PlanZajec;
