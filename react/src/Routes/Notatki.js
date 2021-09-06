import "../css/Notatki.css"
import NotesComponent from '../Components/NotesComponent'
import { useState, useEffect } from 'react'
import axios from 'axios'
import produce from 'immer';
const Notatki = () => {
  const [userSubjects, setUserSubjects] = useState([])
  const [pobrano, setPobrano] = useState(false)
  const initial = [{ text: 'kkkkkkkkkx' }];
  const [datatext, setDataText] = useState(initial);

  let token = null
  if(localStorage.getItem('token')){
      token = localStorage.getItem('token')
  }

  const GetUserSubs = async () => {
      const response = await axios.get("http://25.41.160.138:8080/edycjaprofilu", {
        headers: { 'Authorization' : 'Bearer ' + token }
      })
      setUserSubjects(response.data.subjects)
      setPobrano(true)
  }

  useEffect(() => {
      if(!pobrano){
        GetUserSubs()
        GetUserNotes()
      }
  }, [userSubjects])

  const GetUserNotes = async () => {
      const response = await axios.get("http://25.41.160.138:8080/notatki", {
        headers: { 'Authorization' : 'Bearer ' + token }
      })

      for(let i = 0; i < response.data.subjects.length; i++){
        let arr = []
        
        for(let j = 0; j < response.data.subjects[i].userNotes.length; j++){
          arr.push(response.data.subjects[i].userNotes[j].noteText)
        }
        if(!(localStorage.getItem(`notesFor${response.data.subjects[i].subjectName}`))){
          
          for(let j = 0; j < response.data.subjects[i].userNotes.length; j++){
            let text=arr[j];
            console.log(text)
            const nextState = produce(datatext, draftState => {
              draftState.push({text});
          });
           let fixed = JSON.stringify(nextState)
            localStorage.setItem(`notesFor${response.data.subjects[i].subjectName}`, fixed);
          }
          
        }
      }
  }

  return(
    <div className="mainNotatki">
      <h2>Notatki</h2>
      <div className="subMainNotatki">
      {
        userSubjects.map((e, i) => (
          <div className="subNotatki" key={i}>
            <div className="subName" key={i+12}>{e}</div>
            <NotesComponent subject={e} key={i+11}/>
          </div>
        ))
      }
      </div>
    </div>
  );    
}
 
export default Notatki;
