import './App.css';
import Sidebar from './Components/Sidebar';
import { useState, useEffect } from 'react';
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";
import Aktualnosci from './Routes/Aktualnosci';
import EdycjaProfilu from './Routes/EdycjaProfilu';
import PlanZajec from './Routes/PlanZajec';
import Notatki from './Routes/Notatki';
import Materialy from './Routes/Materialy';
import StronaLogowania from './Routes/StronaLogowania';
import Rejestracja from './Routes/Rejestracja';

function App() {
  const [state, setState] = useState(true)
  const [logg, setLogg] = useState(0)
  const [value, setVal] = useState()
  let storageValue = localStorage.getItem('token')
  useEffect(() => {
    if(localStorage.getItem('token')){
      setLogg(1)
    }else{
      setLogg(0)
    }
  }, [storageValue])
  
  useEffect(() => {
    if(localStorage.getItem('token')){
      setState(true)
      console.log(state)
    }else{
      setState(false)
      console.log(state)
    }
  })

  return (
    <BrowserRouter>
    <div className="App">        
        <Sidebar stan = {logg}/>
        <div className="content">
          <Switch>
            <Route exact path="/">
              <StronaLogowania passValue = {val => setVal(val)}/> 
            </Route>
            <Route exact path="/rejestracja">
              <Rejestracja/>
            </Route>
            <Route exact path="/aktualnosci" render={() => ( state ? <Aktualnosci/> : <Redirect to='/'/>)}/>
            <Route exact path="/edycjaprofilu" render={() => ( state ? <EdycjaProfilu/> : <Redirect to='/'/>)}/>
            <Route exact path="/planzajec" render={() => ( state ? <PlanZajec/> : <Redirect to='/'/>)}/>
            <Route exact path="/notatki" render={() => ( state ? <Notatki/> : <Redirect to='/'/>)}/>
            
          </Switch>
        </div>
    </div>
    </BrowserRouter>
  );
}

export default App;
