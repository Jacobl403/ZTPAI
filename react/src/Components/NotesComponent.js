import { useState, useEffect } from 'react';
import produce from 'immer';
import axios from 'axios'

let token = null
    if(localStorage.getItem('token')){
        token = localStorage.getItem('token')
    }

const SaveUserNotes = async (sub, tex) => {
    const data = await fetch ('http://localhost:8080/notatki/dodajnotatke', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json', 
                'Authorization' : 'Bearer ' + token 
            },
            body: JSON.stringify({
                subject: sub,
                text: tex,
                subjects: null
            })
        })
        const resp = await data.json();
        
}

const DeleteUserNotes = async (sub, tex) => {
    const data = await fetch ('http://localhost:8080/notatki/usunnotatke', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type' : 'application/json', 
                'Authorization' : 'Bearer ' + token 
            },
            body: JSON.stringify({
                subject: sub,
                text: tex,
                subjects: null
            })
        })
        const resp = await data.json();
        
}

function array_move(arr, old_index, new_index) {
    if (new_index >= arr.length) {
        var k = new_index - arr.length + 1;
        while (k--) {
            arr.push(undefined);
        }
    }
    arr.splice(new_index, 0, arr.splice(old_index, 1)[0]);
    return arr;
}
const delay = ms => new Promise(res => setTimeout(res, ms));
const handleDelete = (e, x) => {
    const data = JSON.parse(localStorage.getItem(`notesFor${x}`))
    
    for(let i = 0; i < data.length; i++){
        if(data[i] === e){
            array_move(data, i, data.length-1)
            data.pop()
            
            if(data.length === 0){
                DeleteUserNotes(x, e)
                localStorage.removeItem(`notesFor${x}`)
            }else{
                let fixed = JSON.stringify(data)
                DeleteUserNotes(x, e)
                localStorage.setItem(`notesFor${x}`, fixed)
            }
        }
    }

   window.location.reload()
}

const NotesComponent = (props) => {
    const initialData = [{ text: '' }];
    const [data, setData] = useState(initialData);
    const [subject, setSubject] = useState(props.subject)
    const [note, setNote] = useState(['Brak notatek'])

    const GetNotes = async (sub) => {
        const response = await axios.get("http://localhost:8080/notatki", {
            headers: { 'Authorization' : 'Bearer ' + token }
        })
        
        let notarr = []
        for(let i = 0; i < response.data.subjects.length; i++){
            if(sub === response.data.subjects[i].subjectName){
                for(let k = 0; k < response.data.subjects[i].userNotes.length; k++){
                    notarr.push(response.data.subjects[i].userNotes[k].noteText)
                }
                
                let fix = localStorage.getItem(`notesFor${response.data.subjects[i].subjectName}`)
                localStorage.setItem(`notesFor${response.data.subjects[i].subjectName}`, JSON.stringify(notarr))
               
            }
        }        
    }

    const addNoteClick = () => {
        if(typeof window !== 'undefined'){
        const text = document.querySelector(`#${subject}`).value.trim();
        SaveUserNotes(subject, text)
        if(text) {

        let arr = []
        arr.push(text)

        document.querySelector(`#${subject}`).value = '';

        if(typeof window !== 'undefined'){
            localStorage.setItem(`notesFor${subject}`, JSON.stringify(arr));
            
        }
        
        setNote(arr)
        
        }}
    }

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const getData = localStorage.getItem(`notesFor${subject}`);
      if (getData !== '' && getData !== null) {
        setNote(JSON.parse(getData))
        return setData(JSON.parse(getData));
      }
      return setData([]);
    }
  }, []);

  useEffect(() => {
      GetNotes(subject)
  }, [])



    return (
        <div>
            <div>
                <div className="noteCon">
                    <div className="addNote"><input className = "noteinput" id={subject}  style={{ width: '45%' }} type="text" placeholder="Wprowad?? notatk??" />
                    <button onClick={() => addNoteClick()}>Dodaj notatk??</button></div>
                    {note.map((e, i) => {
                        return e !== 'kkkkkkkkkx' ? 
                    <div className="divForDelete" key={i}><button className="deleteButton" key={i} onClick={() => handleDelete(e, subject)}>X</button>
                    <div className="note" key={i+10}>{e}</div>
                    </div> :
                    null;
                    })}
                </div>
            </div>
        </div>
    );
}
export default NotesComponent;
